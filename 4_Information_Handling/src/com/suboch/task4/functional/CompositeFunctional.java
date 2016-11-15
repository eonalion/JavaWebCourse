package com.suboch.task4.functional;

import com.suboch.task4.composite.ComponentType;
import com.suboch.task4.composite.IComponent;
import com.suboch.task4.composite.TextComponent;
import com.suboch.task4.exception.NotSupportedOperationException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class CompositeFunctional {
    private static Logger logger = LogManager.getLogger(CompositeFunctional.class);

    private List<IComponent> componentList = new ArrayList<>();
    private IComponent parentComponent;
    private Comparator<IComponent> byLexemeAmount;
    private Comparator<IComponent> byABC;

    public CompositeFunctional() {
        comparatorsInit();
    }

    private void comparatorsInit() {
        byLexemeAmount = (c1, c2) -> c1.getChildAmount() - c2.getChildAmount();
        byABC = (c1, c2) -> c1.getChild().toString().toLowerCase().charAt(0) - c2.getChild().toString().toLowerCase().charAt(0);
    }

    public List<IComponent> sortSentencesByLexemeAmount(IComponent component) {
        componentList.clear();
        collectComponents(component, ComponentType.SENTENCE);
        return componentList.stream()
                .sorted(byLexemeAmount)
                .collect(Collectors.toList());
    }

    public List<IComponent> sortWordsByABC(IComponent component) {
        componentList.clear();
        collectComponents(component, ComponentType.WORD);
        return componentList.stream()
                .filter(p -> p.getType().ordinal() < ComponentType.PUNCTUATION.ordinal())
                .sorted(byABC)
                .collect(Collectors.toList());
    }

    public IComponent deleteLexemesOfGivenFormat(IComponent component, int length, char first) {
        IComponent text = new TextComponent(component);
        parentComponent = text;
        text = deleteLexemes(text, length, first);
        return text;
    }

    private IComponent deleteLexemes(IComponent component, int length, char first) {
        if (component.getType().ordinal() < ComponentType.WORD.ordinal()) {
            List<IComponent> children = (List<IComponent>) component.getChild();
            parentComponent = component;
            children.forEach(c -> deleteLexemes(c, length, first));
        }

        if (component.toString().length() == length && component.toString().charAt(0) == first) {
            try {
                parentComponent.removeComponent(component);
            } catch (NotSupportedOperationException e) {
                logger.log(Level.ERROR, e);
            }
            logger.log(Level.DEBUG, component.toString());
        }

        return component;
    }

    private void collectComponents(IComponent component, ComponentType type) {
        if (component.getType().ordinal() < type.ordinal()) {
            List<IComponent> children = (List<IComponent>) component.getChild();
            children.forEach(c -> collectComponents(c, type));
        } else {
            componentList.add(component);
        }
    }
}
