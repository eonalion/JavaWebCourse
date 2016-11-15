package com.suboch.task4.composite;


import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class TextComponent implements IComponent {

    private List<IComponent> components = new LinkedList<>();
    private ComponentType type;

    public TextComponent(ComponentType type) {
        this.type = type;
    }

    public TextComponent(IComponent component) {
        this.components = (List<IComponent>) component.getChild();
        this.type = component.getType();
    }

    @Override
    public ComponentType getType() {
        return type;
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
        s.append(type.getLeftDelimiter());
        components.forEach(part -> s.append(part.toString()));
        s.append(type.getRightDelimiter());
        return s.toString();
    }
}
