package com.suboch.task5.entity;

import com.suboch.task5.exception.InvalidValueException;

/**
 *
 */
public class GrowingTips {
    private boolean heliphilous;
    private int temperature;
    private int water;

    public boolean isHeliphilous() {
        return heliphilous;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getWater() {
        return water;
    }

    public void setHeliphilous(boolean heliphilous) {
        this.heliphilous = heliphilous;
    }

    public void setTemperature(int temperature) throws InvalidValueException {
        if(temperature>=-40 && temperature<=50) {
            this.temperature = temperature;
        } else throw new InvalidValueException("Invalid temperature");
    }

    public void setWater(int water) {
        this.water = water;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\tHeliphious: " + heliphilous + "\n")
                .append("\tTemperature: " + temperature + "C" + "\n")
                .append("\tWater: " + water + "\n");
        return s.toString();
    }
}
