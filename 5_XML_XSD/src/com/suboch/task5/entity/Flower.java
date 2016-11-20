package com.suboch.task5.entity;

import com.suboch.task5.exception.InvalidValueException;

/**
 *
 */
public class Flower {
    private String name;
    private String origin;
    private SoilType soilType;
    private VisualParameters visualParameters;
    private GrowingTips growingTips;
    private MultiplyingType multiplying;

    public Flower() {
        visualParameters = new VisualParameters();
        growingTips = new GrowingTips();
    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public SoilType getSoilType() {
        return soilType;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public MultiplyingType getMultiplying() {
        return multiplying;
    }

    public void setName(String name) throws InvalidValueException {
        if (name.length() > 0 && name.length() <= 100) {
            this.name = name;
        } else throw new InvalidValueException("Invalid flower name");
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setSoilType(SoilType soilType) {
        this.soilType = soilType;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    public void setGrowingTips(GrowingTips growingTips) {
        this.growingTips = growingTips;
    }

    public void setMultiplying(MultiplyingType multiplying) {
        this.multiplying = multiplying;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Name: " + name + "\n")
                .append("Origin: " + origin + "\n")
                .append("Soil type: " + soilType.toString().toLowerCase() + "\n")
                .append("Visual parameters:\n" + visualParameters)
                .append("Growing tips:\n" + growingTips)
                .append("Multiplying: " + multiplying.toString().toLowerCase());
        return s.toString();
    }
}
