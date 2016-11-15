package com.suboch.task4.interpreter;

import java.util.List;

/**
 *
 */
public class MathExpressionInterpreter {
    public double interpret(List<String> expression) {
        MathExpressionInterpreterClient interpreter = new MathExpressionInterpreterClient(expression);
        return interpreter.calculate();
    }
}
