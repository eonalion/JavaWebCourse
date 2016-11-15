package com.suboch.task0.demo;

import com.suboch.task0.factory.TriangleFactory;
import com.suboch.task0.shape.Triangle;
import com.suboch.task0.util.TrianglePropertyCalculator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 *
 */
public class Demo {
    private static final Logger logger = LogManager.getLogger(Logger.class.getName());

    public static void main(String[] args) {
        logger.log(Level.INFO, "Method entered");

        TriangleFactory triangleFactory = new TriangleFactory();
        List<Triangle> triangleList = triangleFactory.getTriangleList();

        //Show triangle list
        Triangle tr;
        TrianglePropertyCalculator propertyCalculator = new TrianglePropertyCalculator();
        for (int i = 0; i < triangleList.size(); i++) {
            tr = triangleList.get(i);
            logger.log(Level.DEBUG, tr.toString() + "\nperimeter: " + propertyCalculator.calculatePerimeter(tr) + "\narea: " + propertyCalculator.calculateArea(tr));
        }

        logger.log(Level.INFO, "Method left");
    }
}
