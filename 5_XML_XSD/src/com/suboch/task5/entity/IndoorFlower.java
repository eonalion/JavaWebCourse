package com.suboch.task5.entity;

/**
 *
 */
public class IndoorFlower extends Flower {
    private boolean flowering;

    public boolean isFlowering() {
        return flowering;
    }

    public void setFlowering(boolean flowering) {
        this.flowering = flowering;
    }

    @Override
    public String toString() {
        return super.toString()
                + "Flowering: "
                + flowering;
    }
}
