package com.suboch.task5.entity;

/**
 *
 */
public class VisualParameters {
    private String stemColor;
    private String leafColor;
    private int size;

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

    public void setSize(int size) {
        this.size = size;
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
