package com.suboch.task5.flower;

/**
 *
 */
public class IndoorFlower extends Flower {
    private boolean flowering;

    public IndoorFlower() {}

    public IndoorFlower(boolean flowering) {
        this.flowering = flowering;
    }

    public IndoorFlower(String name, String origin, SoilType soilType, VisualParameters visualParameters, GrowingTips growingTips, MultiplyingType multiplying, boolean flowering) {
        super(name, origin, soilType, visualParameters, growingTips, multiplying);
        this.flowering = flowering;
    }

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
