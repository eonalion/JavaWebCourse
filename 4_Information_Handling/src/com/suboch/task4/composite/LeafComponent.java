package com.suboch.task4.composite;

import com.suboch.task4.exception.NotSupportedOperationException;

/**
 *
 */
public class LeafComponent implements IComponent {
    private String component;
    private ComponentType type;

    public LeafComponent(String component, ComponentType type) {
        this.component = component;
        this.type = type;
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public String getChild() {
        return component;
    }

    @Override
    public int getChildAmount()  {
        return 1;
    }

    @Override
    public void addComponent(IComponent newComponent) throws NotSupportedOperationException {
        throw new NotSupportedOperationException();
    }

    @Override
    public void removeComponent(IComponent component) throws NotSupportedOperationException {
        throw new NotSupportedOperationException();
    }

    @Override
    public String toString() {
        return component;
    }
}
