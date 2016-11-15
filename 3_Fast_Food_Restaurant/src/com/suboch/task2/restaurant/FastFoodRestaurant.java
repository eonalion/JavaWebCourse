package com.suboch.task2.restaurant;

import com.suboch.task2.exception.SingletonException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */

public class FastFoodRestaurant {
    private static final int MAX_AVAILABLE = 3;
    private static Semaphore semaphore = new Semaphore(MAX_AVAILABLE, true);
    private static List<FastFoodRestaurant> instances = new ArrayList<>(MAX_AVAILABLE);
    private ReentrantLock lock;

    private String name;
    private int id;
    private List<CashDesk> cashDeskList;
    private List<Visitor> visitors;

    //initialization
    public FastFoodRestaurant(String name, int id) {
        this.name = name;
        this.id = id;
        this.cashDeskList = new ArrayList<>();
        this.visitors = new ArrayList<>();
        this.lock = new ReentrantLock();
    }

    public static FastFoodRestaurant createRestaurant(String name, int index) throws SingletonException {
        if (semaphore.tryAcquire()) {
            FastFoodRestaurant temp = new FastFoodRestaurant(name, index);
            instances.add(temp);
            return temp;
        }
        throw new SingletonException("The limit of instances was exceeded");
    }

    public static FastFoodRestaurant getRestaurantInstance(int id) throws SingletonException {
        for(FastFoodRestaurant fastFoodRestaurant: instances){
            if(fastFoodRestaurant.getRestaurantId()==id)
                return fastFoodRestaurant;
        }
        throw new SingletonException("Attempting to get instance by nonexistent id");
    }

    //getters
    public String getName() {
        return name;
    }

    public int getRestaurantId() {
        return id;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public List<CashDesk> getCashDeskList() {
        return cashDeskList;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    //action
    public void addVisitor(Visitor visitor) {
        visitors.add(visitor);
    }

    public void removeVisitor(Visitor visitor){
        visitors.remove(visitor);
    }

    public void createNewCashDesk(int id, int time, FastFoodRestaurantMediator mediator) {
        cashDeskList.add(new CashDesk(id, time, mediator));
    }

    public void open() {
        cashDeskList.forEach(it -> it.start());
        visitors.forEach(it -> it.start());
    }
}
