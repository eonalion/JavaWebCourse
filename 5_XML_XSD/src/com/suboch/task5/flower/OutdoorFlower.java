package com.suboch.task5.flower;

/**
 *
 */
public class OutdoorFlower extends Flower {
    private FlowerLifetime lifetime;

    public OutdoorFlower(){}

    public OutdoorFlower(FlowerLifetime lifetime) {
        this.lifetime = lifetime;
    }

    public OutdoorFlower(String name, String origin, SoilType soilType, VisualParameters visualParameters, GrowingTips growingTips, MultiplyingType multiplying, FlowerLifetime lifetime) {
        super(name, origin, soilType, visualParameters, growingTips, multiplying);
        this.lifetime = lifetime;
    }

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
