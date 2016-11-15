package com.suboch.task4.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MathExpressionInterpreterClient {
    private ArrayList<IMathExpression> listExpression = new ArrayList<>();

    private TerminalExpressionAdd add = new TerminalExpressionAdd();
    private TerminalExpressionDivide divide = new TerminalExpressionDivide();
    private TerminalExpressionMultiply multiply = new TerminalExpressionMultiply();
    private TerminalExpressionSubtract subtract = new TerminalExpressionSubtract();

    public MathExpressionInterpreterClient(List<String> expression) {
        parse(expression);
    }

    private void parse(List<String> expressions) {
        for (String lexeme : expressions) {
            switch (lexeme) {
                case MathExpressionConstants.ADD_OPERATION:
                    listExpression.add(add);
                    break;
                case MathExpressionConstants.SUB_OPERATION:
                    listExpression.add(subtract);
                    break;
                case MathExpressionConstants.MUL_OPERATION:
                    listExpression.add(multiply);
                    break;
                case MathExpressionConstants.DIV_OPERATION:
                    listExpression.add(divide);
                    break;
                default:
                    listExpression.add(new NonTerminalExpressionNumber(Double.parseDouble(lexeme)));
            }
        }
    }

    public double calculate() {
        Context context = new Context();
        for (IMathExpression terminal : listExpression) {
            terminal.interpret(context);
        }
        return context.popValue();
    }
}
