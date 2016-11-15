package com.suboch.task2.demo;

import com.suboch.task2.exception.SingletonException;
import com.suboch.task2.restaurant.FastFoodRestaurantMediator;
import com.suboch.task2.restaurant.FastFoodRestaurant;
import com.suboch.task2.restaurant.Visitor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Stream;

/**
 *
 */
public class Demo {
    private static Logger logger = LogManager.getLogger(Demo.class);

    public static void main(String[] args) {
        FastFoodRestaurantMediator mediator = new FastFoodRestaurantMediator();
        FastFoodRestaurant fastFoodRestaurant = null;

        try {
            fastFoodRestaurant = FastFoodRestaurant.createRestaurant("M", 1);
            mediator.registerRestaurant(fastFoodRestaurant);
        } catch (SingletonException e) {
            logger.log(Level.ERROR, e);
        }

        if (fastFoodRestaurant != null) {
            fastFoodRestaurant.createNewCashDesk(1, 1000, mediator);
            fastFoodRestaurant.createNewCashDesk(2, 500, mediator);
            fastFoodRestaurant.createNewCashDesk(3, 400, mediator);
            fastFoodRestaurant.createNewCashDesk(4, 600, mediator);
            fastFoodRestaurant.createNewCashDesk(5, 500, mediator);
            fastFoodRestaurant.createNewCashDesk(6, 390, mediator);
            Stream.of(new Visitor("Masha", 500, 2, mediator),
                    new Visitor("Nickolai", 100, 6, mediator),
                    new Visitor("Lena", 200000, 1, mediator),
                    new Visitor("Vladimir", 300000, 3, mediator),
                    new Visitor("Alisa", 300000, 10, mediator),
                    new Visitor("Kelly", 300000, 3, mediator),
                    new Visitor("Paul", 2000, 9, mediator),
                    new Visitor("Natasha", 200000, 6, mediator),
                    new Visitor("Rebeca", 300000, 2, mediator),
                    new Visitor("Gena", 300000, 7, mediator),
                    new Visitor("Edik", 4000, 2, mediator))
                    .forEach(mediator::newVisitor);
            fastFoodRestaurant.open();
        }
    }
}
