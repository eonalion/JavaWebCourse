package com.suboch.task4.interpreter;

import com.suboch.task4.exception.MathExpressionTransformerException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class MathExpressionTransformer {
    // Associativity constants for operators.
    private static final int LEFT_ASSOC = 0;
    private static final int RIGHT_ASSOC = 1;

    // Supported operators.
    private static final Map<String, int[]> OPERATORS = new HashMap<>();

    static {
        OPERATORS.put(MathExpressionConstants.ADD_OPERATION, new int[]{0, LEFT_ASSOC});
        OPERATORS.put(MathExpressionConstants.SUB_OPERATION, new int[]{0, LEFT_ASSOC});
        OPERATORS.put(MathExpressionConstants.MUL_OPERATION, new int[]{5, LEFT_ASSOC});
        OPERATORS.put(MathExpressionConstants.DIV_OPERATION, new int[]{5, LEFT_ASSOC});
    }

    private static final String SPACE_REGEXP = " ";
    private static final String BLANK_REGEXP = "\\s+";
    private static final String EXPRESSION_WITH_INCREMENT_DECREMENT_REGEXP = "([+-]{2}\\d+|\\d+[+-]{2})";
    private static final String NUMBER_REGEXP = "\\d+";
    private static final String SIGN_REGEXP = "[+-]{2}";
    private static final String EXPRESSION_PART_REGEXP = "\\d+|[+-/*()]";
    private static final String UNARY_PREFIX_OPERATION_REGEXP = "^[+-](?=\\d+)|(?<=\\()[+-](?=\\d+)";
    private static final String UNARY_POSTFIX_OPERATION_REGEXP = "(?<=\\d+)[+-]\\Z|(?<=\\d+)[+-](?=\\))";

    public static List<String> convertToPolishNotation(String expression) {
        expression = addSpaceDelimiters(expression);
        String[] input = expression.split(SPACE_REGEXP);
        String[] output = infixToRPN(input);

        return Arrays.asList(output);
    }

    //Replace increment and decrement operations for (n+1) and (n-1) and calculate expression in brackets.
    public static String formatExpression(String fullExpression) throws MathExpressionTransformerException {
        String singleExpression;
        String replaceValue = "";
        String number;
        String sign;

        Matcher incDecExprMatcher = Pattern.compile(EXPRESSION_WITH_INCREMENT_DECREMENT_REGEXP).matcher(fullExpression);

        try {
            while (incDecExprMatcher.find()) {
                singleExpression = incDecExprMatcher.group();
                sign = extractSign(singleExpression);
                number = extractNumber(singleExpression);
                replaceValue = calculateValue(number, sign);
            }
        } catch (IllegalStateException | NumberFormatException e) {
            throw new MathExpressionTransformerException(e);
        }

        fullExpression = fullExpression.replaceFirst(EXPRESSION_WITH_INCREMENT_DECREMENT_REGEXP, replaceValue);
        fullExpression = MathExpressionTransformer.addNulls(fullExpression);

        return fullExpression;
    }

    private static String extractNumber(String expression) throws IllegalStateException {
        Matcher numberMatcher = Pattern.compile(NUMBER_REGEXP).matcher(expression);
        numberMatcher.find();
        return numberMatcher.group();
    }

    private static String extractSign(String expression) throws IllegalStateException {
        Matcher signMatcher = Pattern.compile(SIGN_REGEXP).matcher(expression);
        signMatcher.find();
        return signMatcher.group();
    }

    private static String calculateValue(String numberString, String sign) throws NumberFormatException, MathExpressionTransformerException {
        int number = Integer.parseInt(numberString);

        switch (sign) {
            case MathExpressionConstants.INC_OPERATION:
                number++;
                break;
            case MathExpressionConstants.DEC_OPERATION:
                number--;
                break;
            default:
                throw new MathExpressionTransformerException();
        }

        return String.valueOf(number);
    }

    //Divide math expression with space delimiters;
    private static String addSpaceDelimiters(String s) {
        //FIXME: correct the regexp
        Matcher partMatcher = Pattern.compile(EXPRESSION_PART_REGEXP).matcher(s);
        String part;

        while (partMatcher.find()) {
            part = partMatcher.group();
            s = s.replace(part, part + MathExpressionConstants.SPACE_DELIMITER);
        }

        s = s.replaceAll(BLANK_REGEXP, MathExpressionConstants.SPACE_DELIMITER).trim();
        return s;
    }

    //Replace unary operators to binary by attaching 0 value.
    private static String addNulls(String expression) {
        Matcher prefixUnaryOperationMatcher = Pattern.compile(UNARY_PREFIX_OPERATION_REGEXP).matcher(expression);
        Matcher postfixUnaryOperationMatcher = Pattern.compile(UNARY_POSTFIX_OPERATION_REGEXP).matcher(expression);

        String part;
        if (prefixUnaryOperationMatcher.find()) {
            part = prefixUnaryOperationMatcher.group();
            expression = expression.replaceAll(UNARY_PREFIX_OPERATION_REGEXP, MathExpressionConstants.NULL_SYMBOL + part);
        } else if (postfixUnaryOperationMatcher.find()) {
            part = postfixUnaryOperationMatcher.group();
            expression = expression.replaceAll(UNARY_POSTFIX_OPERATION_REGEXP, part + MathExpressionConstants.NULL_SYMBOL);
        }

        return expression;
    }

    //Additional methods for reverse polish notation.
    private static boolean isOperator(String token) {
        return OPERATORS.containsKey(token);
    }

    private static boolean isAssociative(String token, int type) {
        if (!isOperator(token)) {
            throw new IllegalArgumentException("Invalid token: " + token);
        }
        if (OPERATORS.get(token)[1] == type) {
            return true;
        }
        return false;
    }

    private static int cmpPrecedence(String token1, String token2) {
        if (!isOperator(token1) || !isOperator(token2)) {
            throw new IllegalArgumentException("Invalid tokens: " + token1
                    + " " + token2);
        }
        return OPERATORS.get(token1)[0] - OPERATORS.get(token2)[0];
    }

    private static String[] infixToRPN(String[] inputTokens) {
        ArrayList<String> out = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        for (String token : inputTokens) {
            if (isOperator(token)) {
                while (!stack.empty() && isOperator(stack.peek())) {
                    if ((isAssociative(token, LEFT_ASSOC) && cmpPrecedence(
                            token, stack.peek()) <= 0)
                            || (isAssociative(token, RIGHT_ASSOC) && cmpPrecedence(
                            token, stack.peek()) < 0)) {
                        out.add(stack.pop());
                        continue;
                    }
                    break;
                }
                stack.push(token);
            } else if (MathExpressionConstants.LEFT_BRACKET.equals(token)) {
                stack.push(token);
            } else if (MathExpressionConstants.RIGHT_BRACKET.equals(token)) {
                while (!stack.empty() && !MathExpressionConstants.LEFT_BRACKET.equals(stack.peek())) {
                    out.add(stack.pop());
                }
                stack.pop();
            } else {
                out.add(token);
            }
        }
        while (!stack.empty()) {
            out.add(stack.pop());
        }
        String[] output = new String[out.size()];
        return out.toArray(output);
    }
}
