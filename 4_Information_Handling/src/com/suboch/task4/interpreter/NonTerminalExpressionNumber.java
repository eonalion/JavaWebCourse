package com.suboch.task4.interpreter;

/**
 *
 */
public class NonTerminalExpressionNumber implements IMathExpression {
    private double number;

    public NonTerminalExpressionNumber(double number) {
        this.number = number;
    }

    @Override
    public void interpret(Context c) {
        c.pushValue(number);
    }
}
