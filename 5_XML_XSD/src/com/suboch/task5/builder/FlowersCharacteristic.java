package com.suboch.task5.builder;

/**
 *
 */
public enum FlowersCharacteristic {
    FLOWERS("flowers"),
    FLOWER("flower"),
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
    EMPTY_TAG("");

    private String value;

    FlowersCharacteristic(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
