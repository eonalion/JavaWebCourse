package com.suboch.task4.parser;

import com.suboch.task4.composite.ComponentType;
import com.suboch.task4.composite.IComponent;
import com.suboch.task4.composite.LexemeComponent;
import com.suboch.task4.composite.TextComponent;
import com.suboch.task4.exception.MathExpressionTransformerException;
import com.suboch.task4.exception.NotSupportedOperationException;
import com.suboch.task4.interpreter.MathExpressionInterpreter;
import com.suboch.task4.interpreter.MathExpressionTransformer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 *
 */
public class TextComponentParser implements ITextComponentParser {
    private static Logger logger = LogManager.getLogger(TextComponentParser.class);
    private LexemeParser lexemeParser = new LexemeParser();
    private MathExpressionInterpreter interpreter = new MathExpressionInterpreter();

    private static final String MATH_EXPRESSION_REGEXP = "\\d+";

    @Override
    public void parse(IComponent component, String text, ComponentType type) {
        String[] parts = text.split(type.getRegExp());
        int nextPartTypeIndex = type.ordinal() + 1;

        if (type.ordinal() < ComponentType.LEXEME.ordinal()) {
            Arrays.stream(parts).filter(s -> !s.isEmpty()).forEach(part -> {
                TextComponent temp = new TextComponent(ComponentType.values()[type.ordinal()]);
                try {
                    component.addComponent(temp);
                } catch (NotSupportedOperationException e) {
                    logger.error(e);
                }
                parse(temp, part, ComponentType.values()[nextPartTypeIndex]);
            });
        } else {
            Pattern mathExprPattern = Pattern.compile(MATH_EXPRESSION_REGEXP);

            Arrays.stream(parts).forEach(part -> {
                LexemeComponent lexemeComponent = new LexemeComponent();
                if (mathExprPattern.matcher(part).find()) {
                    try {
                        part = MathExpressionTransformer.formatExpression(part);
                    } catch (MathExpressionTransformerException e) {
                        logger.log(Level.ERROR, e);
                    }

                    double result = interpreter.interpret(MathExpressionTransformer.convertToPolishNotation(part));
                    try {
                        component.addComponent(lexemeComponent);
                    } catch (NotSupportedOperationException e) {
                        logger.error(e);
                    }
                    lexemeParser.parse(lexemeComponent, String.valueOf(result));
                } else {
                    try {
                        component.addComponent(lexemeComponent);
                    } catch (NotSupportedOperationException e) {
                        logger.error(e);
                    }
                    lexemeParser.parse(lexemeComponent, part);
                }
            });
        }
    }
}
