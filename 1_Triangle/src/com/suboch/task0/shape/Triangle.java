package com.suboch.task0.shape;

import com.suboch.task0.exception.DegenerateTriangleException;
import com.suboch.task0.util.TriangleTypeChecker;

/**
 *
 */
public class Triangle {
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) throws DegenerateTriangleException {
        this.a = a;
        this.b = b;
        this.c = c;
        if (TriangleTypeChecker.isDegenerateTriangle(this)) {
            throw new DegenerateTriangleException(this.toString() + " is degenerate");
        }
    }

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) throws DegenerateTriangleException {
        this.a = new Point(x1, y1);
        this.b = new Point(x2, y2);
        this.c = new Point(x3, y3);
        if (TriangleTypeChecker.isDegenerateTriangle(this)) {
            throw new DegenerateTriangleException(this.toString() + " is degenerate");
        }
    }

    //Calculate side length
    public double calculateAB() {
        return Math.hypot(a.getX() - b.getX(), a.getY() - b.getY());
    }

    public double calculateAC() {
        return Math.hypot(a.getX() - c.getX(), a.getY() - c.getY());
    }

    public double calculateBC() {
        return Math.hypot(b.getX() - c.getX(), b.getY() - c.getY());
    }

    //Setters
    public void setA(double x, double y) throws DegenerateTriangleException {
        this.a = new Point(x, y);
        if (TriangleTypeChecker.isDegenerateTriangle(this)) {
            throw new DegenerateTriangleException(this.toString());
        }
    }

    public void setB(double x, double y) throws DegenerateTriangleException {
        this.b = new Point(x, y);
        if (TriangleTypeChecker.isDegenerateTriangle(this)) {
            throw new DegenerateTriangleException(this.toString());
        }
    }

    public void setC(double x, double y) throws DegenerateTriangleException {
        this.c = new Point(x, y);
        if (TriangleTypeChecker.isDegenerateTriangle(this)) {
            throw new DegenerateTriangleException(this.toString());
        }
    }

    //Getters
    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getC() {
        return c;
    }

    @Override
    public String toString() {
        return "Triangle{"
                + a.toString() + ", "
                + b.toString() + ", "
                + c.toString() +
                '}';
    }
}
