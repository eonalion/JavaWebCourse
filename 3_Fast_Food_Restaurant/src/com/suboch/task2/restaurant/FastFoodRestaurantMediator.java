package com.suboch.task2.restaurant;

import java.util.List;

/**
 *
 */
public class FastFoodRestaurantMediator {
    private FastFoodRestaurant restaurant;

    //operations
    public void registerRestaurant(FastFoodRestaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void lockRestaurant() {
        restaurant.getLock().lock();
    }

    public void unlockRestaurant() {
        restaurant.getLock().unlock();
    }

    public void newVisitor(Visitor visitor){
        restaurant.addVisitor(visitor);
    }

    public void removeVisitor(Visitor visitor){
        restaurant.removeVisitor(visitor);
    }

    //getters
    public String getRestaurantName(){
        return restaurant.getName();
    }

    public List<Visitor> getVisitorList(){
        return restaurant.getVisitors();
    }

    public List<CashDesk> getCashDeskList(){
        return restaurant.getCashDeskList();
    }
}
