package com.suboch.task0.util;

import com.suboch.task0.shape.Triangle;

/**
 *
 */
public class TrianglePropertyCalculator {
    public double calculatePerimeter(Triangle triangle) {
        return triangle.calculateAB() + triangle.calculateBC() + triangle.calculateAC();
    }

    public double calculateArea(Triangle triangle) {
        double semiP = (triangle.calculateAB() + triangle.calculateBC() + triangle.calculateAC()) / 2;
        return Math.sqrt(semiP * (semiP - triangle.calculateAB()) * (semiP - triangle.calculateAC()) * (semiP - triangle.calculateBC()));
    }
}
