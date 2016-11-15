package com.suboch.task0.factory;

import com.suboch.task0.exception.DegenerateTriangleException;
import com.suboch.task0.shape.Point;
import com.suboch.task0.shape.Triangle;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TriangleFactory {
    private static final Logger logger = LogManager.getLogger(Logger.class.getName());
    private final static File inputFile = new File("file/coordinates.txt");

    public TriangleFactory() {
    }

    public List<Triangle> getTriangleList() {
        List<Triangle> triangles = new ArrayList<>();

        //Read data from file
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String strLine;

            while ((strLine = br.readLine()) != null) {
                String vertexArr[] = strLine.split(" ");
                Point a = new Point(Double.parseDouble(vertexArr[0]), Double.parseDouble(vertexArr[1]));
                Point b = new Point(Double.parseDouble(vertexArr[2]), Double.parseDouble(vertexArr[3]));
                Point c = new Point(Double.parseDouble(vertexArr[4]), Double.parseDouble(vertexArr[5]));
                triangles.add(new Triangle(a, b, c));
            }
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
        } catch (DegenerateTriangleException e) {
            logger.log(Level.ERROR, "Wrong input data", e);
        }

        return triangles;
    }
}
