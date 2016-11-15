package com.suboch.task4.composite;

import com.suboch.task4.exception.NotSupportedOperationException;

/**
 *
 */
public interface IComponent {
    Object getChild();

    int getChildAmount();

    ComponentType getType();

    void addComponent(IComponent newComponent) throws NotSupportedOperationException;

    void removeComponent(IComponent component) throws NotSupportedOperationException;
}
