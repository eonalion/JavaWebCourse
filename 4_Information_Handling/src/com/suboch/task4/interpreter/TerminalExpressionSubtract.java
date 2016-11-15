package com.suboch.task4.interpreter;

/**
 *
 */
public class TerminalExpressionSubtract implements IMathExpression {
    @Override
    public void interpret(Context c) {
        double secondValue = c.popValue();
        double firstValue = c.popValue();
        c.pushValue(firstValue - secondValue);
    }
}
