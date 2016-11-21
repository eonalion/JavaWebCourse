package com.suboch.task5.entity;

/**
 *
 */
public class OutdoorFlower extends Flower {
    private FlowerLifetime lifetime;

    public FlowerLifetime getLifetime() {
        return lifetime;
    }

    public void setLifetime(FlowerLifetime lifetime) {
        this.lifetime = lifetime;
    }

    @Override
    public String toString() {
        return super.toString()
                + "Lifetime: "+ lifetime.toString().toLowerCase();
    }
}
