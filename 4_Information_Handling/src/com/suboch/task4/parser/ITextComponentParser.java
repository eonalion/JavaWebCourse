package com.suboch.task4.parser;

import com.suboch.task4.composite.ComponentType;
import com.suboch.task4.composite.IComponent;

/**
 *
 */
public interface ITextComponentParser {
    void parse(IComponent component, String text, ComponentType type);
}
