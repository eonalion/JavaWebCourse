package com.suboch.task2.restaurant;

import com.suboch.task2.exception.RestaurantServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class Visitor extends Thread {
    private String name;
    private VisitorLogic logic;
    private VisitorState state;

    private int waitingTime;
    private int orderItemsAmount;
    
    private CashDesk cashDesk;
    private FastFoodRestaurantMediator mediator;
    private static Logger logger = LogManager.getLogger(Visitor.class);

    //initialization
    public Visitor(String name, int waitingTime, int orderItemsAmount, FastFoodRestaurantMediator mediator) {
        this.name = name;
        this.orderItemsAmount = orderItemsAmount;
        this.logic = new VisitorLogic();
        this.state = VisitorState.NONE;
        this.waitingTime = waitingTime;
        this.mediator = mediator;
    }

    //getters and setters
    public String getVisitorName() {
        return name;
    }

    public int getOrderItemsAmount() {
        return orderItemsAmount;
    }

    public void setState(VisitorState state) {
        this.state = state;
    }

    //states
    public boolean isInServingProcess() {
        return state.equals(VisitorState.IS_IN_SERVING_PROCESS);
    }

    public boolean isWaiting() {
        return state.equals(VisitorState.IS_WAITING);
    }

    public boolean isServed() {
        return state.equals(VisitorState.IS_SERVED);
    }

    public void run() {
        final int DECISION_GENERATOR_SLEEP_TIME = 100;
        int timeSpentInQueue = 0;
        CashDesk priorityCashDesk;

        logger.log(Level.DEBUG, "Visitor " + name + " enters restaurant " + mediator.getRestaurantName());

        mediator.lockRestaurant();
        try {
            this.cashDesk = logic.chooseCashDesk();
            this.cashDesk.addVisitor(this);
            this.setState(VisitorState.IS_WAITING);
        } finally {
            mediator.unlockRestaurant();
        }

        try {
            while (isWaiting()) {
                if (logic.isWaitingTooLong(timeSpentInQueue)) {
                    return;
                }
                timeSpentInQueue += DECISION_GENERATOR_SLEEP_TIME;

                switch (logic.makeDecision()) {
                    case CHANGE_QUEUE:
                        priorityCashDesk = logic.changeCashDesk(this.cashDesk);
                        if (priorityCashDesk.getCashDeskID() != this.cashDesk.getCashDeskID()) {
                            this.cashDesk.removeVisitor(this);
                            this.cashDesk = priorityCashDesk;
                            this.cashDesk.addVisitor(this);
                        }
                        break;
                    case SWAP_PLACES:
                        logic.tryToSwapPlaces();
                        break;
                    case NONE:
                        break;
                    default:
                        throw new RestaurantServiceException("Decision was generated with errors");
                }

                TimeUnit.MILLISECONDS.sleep(DECISION_GENERATOR_SLEEP_TIME);
            }

            while (isInServingProcess()) {
                TimeUnit.MILLISECONDS.sleep(this.cashDesk.getServingTime() * this.orderItemsAmount + 5);
            }

        } catch (InterruptedException | RestaurantServiceException e) {
            logger.log(Level.ERROR, "Error in visitor "+getVisitorName()+" action process", e);
        }

        if (isServed()) {
            logger.log(Level.DEBUG, "Visitor " + this.getVisitorName() + " leaves restaurant " + mediator.getRestaurantName());
        }
    }

    private class VisitorLogic {
        Random random = new Random();

        private VisitorDecision makeDecision() {
            final int PROBABILITY = 20;
            int rand = random.nextInt(PROBABILITY) + 1;
            switch (rand) {
                case 1:
                    return VisitorDecision.SWAP_PLACES;
                case 2:
                    return VisitorDecision.CHANGE_QUEUE;
            }
            return VisitorDecision.NONE;
        }

        private CashDesk findPriorityCashDesk(CashDesk cashDesk, int difference) {
            CashDesk priorityCashDesk = cashDesk;
            for (CashDesk currCashDesk : mediator.getCashDeskList()) {
                if (currCashDesk.getQueue().size() < priorityCashDesk.getQueue().size() - difference) {
                    priorityCashDesk = currCashDesk;
                }
            }
            return priorityCashDesk;
        }

        private CashDesk chooseCashDesk() {
            final int FIRSTCASHDESK = 0;
            final int DIFFERENCE = 0;
            CashDesk chosen = findPriorityCashDesk(mediator.getCashDeskList().get(FIRSTCASHDESK), DIFFERENCE);
            logger.log(Level.DEBUG, "Visitor " + name + " chose cash desk " + chosen.getCashDeskID());
            return chosen;
        }

        private CashDesk changeCashDesk(CashDesk cashDesk) {
            Visitor.this.cashDesk.getLock().lock();

            CashDesk changed = null;
            try {
                changed = findPriorityCashDesk(cashDesk, 1);
                if (cashDesk.getCashDeskID() != changed.getCashDeskID()) {
                    logger.log(Level.DEBUG, "Visitor " + name + " changed cash desk " + cashDesk.getCashDeskID() + " to " + changed.getCashDeskID());
                }
            } finally {
                Visitor.this.cashDesk.getLock().unlock();
            }
            return changed;
        }

        private boolean tryToSwapPlaces() {
            boolean swap = false;
            CashDesk currentCashDesk = Visitor.this.cashDesk;
            Visitor currentVisitor = Visitor.this;
            Visitor otherVisitor = null;

            currentCashDesk.getLock().lock();
            List<Visitor> currentQueue = new ArrayList<>();
            List<Visitor> otherQueue;
            try {
                currentQueue = new ArrayList<>(currentVisitor.cashDesk.getQueue());
                int position = currentQueue.indexOf(currentVisitor);

                for (CashDesk otherCashDesk : mediator.getCashDeskList()) {
                    if (otherCashDesk.getCashDeskID() != currentCashDesk.getCashDeskID() && position < otherCashDesk.getQueue().size()) {
                        otherQueue = new ArrayList<>(otherCashDesk.getQueue());
                        otherCashDesk.getLock().lock();
                        try {
                            otherVisitor = otherQueue.get(position);
                            otherQueue.set(position, currentVisitor);
                            currentQueue.set(position, otherVisitor);
                        } finally {
                            otherCashDesk.getLock().unlock();
                            otherCashDesk.setQueue(new ArrayDeque<>(otherQueue));
                        }
                        swap = true;
                        break;
                    }
                }
            } finally {
                currentCashDesk.getLock().unlock();
                if (swap) {
                    currentCashDesk.setQueue(new ArrayDeque<>(currentQueue));
                    currentVisitor.cashDesk = otherVisitor.cashDesk;
                    otherVisitor.cashDesk = currentCashDesk;

                    logger.log(Level.DEBUG, "Visitor " + Visitor.this.getVisitorName() + " swap places with visitor " + otherVisitor.getVisitorName() + " and is now in cash desk " + Visitor.this.cashDesk.getCashDeskID());
                    logger.log(Level.DEBUG, "Visitor " + otherVisitor.getVisitorName() + " swap places with visitor " + Visitor.this.getVisitorName() + " and is now in cash desk " + otherVisitor.cashDesk.getCashDeskID());
                }
            }

            return swap;
        }

        private boolean isWaitingTooLong(int timeInQueue) {
            if (timeInQueue >= waitingTime) {
                Visitor.this.cashDesk.removeVisitor(Visitor.this);
                mediator.removeVisitor(Visitor.this);
                logger.log(Level.DEBUG, "Visitor " + name + " is tired to wait and leaves restaurant " + mediator.getRestaurantName());
                return true;
            }
            return false;
        }
    }
}
