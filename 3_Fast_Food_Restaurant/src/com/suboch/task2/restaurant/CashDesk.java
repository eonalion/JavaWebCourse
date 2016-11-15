package com.suboch.task2.restaurant;

import com.suboch.task2.exception.RestaurantServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class CashDesk extends Thread {
    private int id;
    private int servingTime;
    private Queue<Visitor> queue;

    private ReentrantLock lock;
    private FastFoodRestaurantMediator mediator;
    private static Logger logger = LogManager.getLogger(CashDesk.class);

    //initialization
    public CashDesk(int id, int time, FastFoodRestaurantMediator mediator) {
        this.id = id;
        this.lock = new ReentrantLock();
        this.queue = new ArrayDeque<>();
        this.servingTime = time;
        this.mediator = mediator;
    }

    //getters and setters
    public int getCashDeskID() {
        return id;
    }

    public int getServingTime() {
        return servingTime;
    }

    public Queue<Visitor> getQueue() {
        return queue;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public void setQueue(Queue<Visitor> queue) {
        this.queue = queue;
    }

    //action
    public void removeVisitor(Visitor visitor) {
        queue.remove(visitor);
    }

    public void addVisitor(Visitor visitor) {
        queue.add(visitor);
    }

    private void serveVisitor(Visitor visitor) throws RestaurantServiceException {
        logger.log(Level.DEBUG, "Serving visitor " + visitor.getVisitorName() + " in cash desk " + getCashDeskID());
        visitor.setState(VisitorState.IS_IN_SERVING_PROCESS);

        try {
            TimeUnit.MILLISECONDS.sleep(servingTime * visitor.getOrderItemsAmount());
        } catch (InterruptedException e) {
            throw new RestaurantServiceException(e);
        }

        visitor.setState(VisitorState.IS_SERVED);
        logger.log(Level.DEBUG, visitor.getVisitorName() + " is served");
        queue.remove(visitor);
        mediator.removeVisitor(visitor);
    }

    public void run() {
        boolean restaurantClosed = false;
        final int SLEEP_TIME = 100;

        while (!restaurantClosed) {
            try {
                Visitor visitor = null;

                lock.lock();
                try {
                    if (queue.size() > 0) {
                        visitor = queue.peek();
                    }
                } finally {
                    lock.unlock();
                }

                if (visitor != null) {
                    serveVisitor(visitor);
                } else {
                        TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);

                    if (mediator.getVisitorList().isEmpty()) {
                        restaurantClosed = true;
                    }
                }
            } catch (InterruptedException | RestaurantServiceException e) {
                logger.log(Level.ERROR, "Error in cash desk " + getCashDeskID() + " serving process", e);
            }
        }
    }
}
