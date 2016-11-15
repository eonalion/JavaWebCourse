package com.suboch.task0.util;

import com.suboch.task0.shape.Triangle;

import java.util.Arrays;

/**
 *
 */
public class TriangleTypeChecker {
    public static boolean isDegenerateTriangle(Triangle triangle) {
        if (triangle.getA().getX() == triangle.getB().getX() && triangle.getB().getX() == triangle.getC().getX()) {
            return true;
        }
        if (triangle.getA().getY() == triangle.getB().getY() && triangle.getB().getY() == triangle.getC().getY()) {
            return true;
        }

        return false;
    }

    public static boolean isRightTriangle(Triangle triangle) {
        double sidesArray[] = {triangle.calculateBC(), triangle.calculateAB(), triangle.calculateAC()};
        Arrays.sort(sidesArray);
        return Double.compare(Math.pow(sidesArray[2], 2), Math.pow(sidesArray[0], 2) + Math.pow(sidesArray[1], 2)) == 0;
    }
}
