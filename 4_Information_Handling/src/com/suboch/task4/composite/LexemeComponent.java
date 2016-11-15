package com.suboch.task4.composite;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class LexemeComponent implements IComponent {
    private List<IComponent> components = new LinkedList<>();

    @Override
    public ComponentType getType() {
        return ComponentType.LEXEME;
    }

    @Override
    public List<IComponent> getChild() {
        return components;
    }

    @Override
    public int getChildAmount() {
        return components.size();
    }

    @Override
    public void addComponent(IComponent newComponent) {
        components.add(newComponent);
    }

    @Override
    public void removeComponent(IComponent component) {
        components.remove(component);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        components.forEach(c -> s.append(c.toString()));
        s.append(ComponentType.LEXEME.getRightDelimiter());
        return s.toString();
    }
}
