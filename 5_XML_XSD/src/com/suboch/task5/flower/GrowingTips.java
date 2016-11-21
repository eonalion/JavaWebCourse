package com.suboch.task5.flower;

import com.suboch.task5.exception.InvalidValueException;

/**
 *
 */
public class GrowingTips {
    private boolean heliphilous;
    private int temperature;
    private int water;

    public GrowingTips(){
    }

    public GrowingTips(boolean heliphilous, int temperature, int water) {
        this.heliphilous = heliphilous;
        this.temperature = temperature;
        this.water = water;
    }

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
        } else throw new InvalidValueException("Invalid temperature format");
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
