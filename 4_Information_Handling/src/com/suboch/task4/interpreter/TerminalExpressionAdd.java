package com.suboch.task4.interpreter;

/**
 *
 */
public class TerminalExpressionAdd implements IMathExpression {
    @Override
    public void interpret(Context c) {
        double firstValue = c.popValue();
        double secondValue = c.popValue();
        c.pushValue(firstValue + secondValue);
    }
}
