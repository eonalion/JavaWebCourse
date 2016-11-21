package com.suboch.task5.flower;

import com.suboch.task5.exception.InvalidValueException;

/**
 *
 */
public class VisualParameters {
    private String stemColor;
    private String leafColor;
    private int size;

    public VisualParameters() {}

    public VisualParameters(String stemColor, String leafColor, int size) {
        this.stemColor = stemColor;
        this.leafColor = leafColor;
        this.size = size;
    }

    public String getStemColor() {
        return stemColor;
    }

    public String getLeafColor() {
        return leafColor;
    }

    public int getSize() {
        return size;
    }

    public void setStemColor(String stemColor) {
        this.stemColor = stemColor;
    }

    public void setLeafColor(String leafColor) {
        this.leafColor = leafColor;
    }

    public void setSize(int size) throws InvalidValueException {
        if(size>0) {
            this.size = size;
        } else throw new InvalidValueException("Size cannot be negative");
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\tStem color: " + stemColor + "\n")
                .append("\tLeaf color: " + leafColor + "\n")
                .append("\tFlower size: " + size + "\n");
        return s.toString();
    }
}
