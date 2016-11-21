package com.suboch.task5.builder;

/**
 *
 */
public enum FlowersCharacteristic {
    FLOWERS("flowers"),
    FLOWER("flower"),
    INDOOR_FLOWER("indoor-flower"),
    OUTDOOR_FLOWER("outdoor-flower"),
    NAME("name"),
    SOIL("soil"),
    ORIGIN("origin"),
    VISUAL_PARAMETERS("visual-parameters"),
    STEM_COLOR("stem-color"),
    LEAF_COLOR("leaf-color"),
    SIZE("size"),
    GROWING_TIPS("growing-tips"),
    HELIPHILOUS("heliphilous"),
    TEMPERATURE("temperature"),
    WATER("water"),
    MULTIPLYING("multiplying"),
    FLOWERING("flowering"),
    LIFETIME("lifetime"),
    EMPTY_TAG("");

    private String value;

    FlowersCharacteristic(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
