package test.com.suboch.task4.parser;

import com.suboch.task4.composite.ComponentType;
import com.suboch.task4.composite.LeafComponent;
import com.suboch.task4.composite.LexemeComponent;
import com.suboch.task4.composite.TextComponent;
import com.suboch.task4.exception.MathExpressionTransformerException;
import com.suboch.task4.interpreter.MathExpressionInterpreter;
import com.suboch.task4.interpreter.MathExpressionTransformer;
import com.suboch.task4.parser.ILexemeParser;
import com.suboch.task4.parser.ITextComponentParser;
import com.suboch.task4.parser.LexemeParser;
import com.suboch.task4.parser.TextComponentParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class TextParsersTest {
    private ILexemeParser lexemeParser = new LexemeParser();
    private ITextComponentParser textComponentParser = new TextComponentParser();

    private LexemeComponent lexemeExpected = new LexemeComponent();
    private LexemeComponent lexemeActual = new LexemeComponent();
    private TextComponent sentenceExpected = new TextComponent(ComponentType.SENTENCE);
    private TextComponent sentenceActual = new TextComponent(ComponentType.SENTENCE);
    private LexemeComponent l1 = new LexemeComponent();
    private LexemeComponent l2 = new LexemeComponent();
    private LexemeComponent l3 = new LexemeComponent();

    private LexemeComponent mathExpressionLexeme = new LexemeComponent();

    @Test
    public void testLexemeParser() {
        lexemeExpected.addComponent(new LeafComponent("Cold", ComponentType.WORD));
        lexemeExpected.addComponent(new LeafComponent(".", ComponentType.WORD));

        lexemeParser.parse(lexemeActual, "Cold.");
        String lexemeExpectedString = lexemeExpected.toString();
        String lexemeActualString = lexemeActual.toString();
        Assert.assertEquals(lexemeExpectedString, lexemeActualString);
    }

    @Test
    public void testTextComponentParser() {
        lexemeParser.parse(l1, "Just");
        lexemeParser.parse(l2, "say");
        lexemeParser.parse(l3, "\"Hey!\"");
        sentenceExpected.addComponent(l1);
        sentenceExpected.addComponent(l2);
        sentenceExpected.addComponent(l3);

        textComponentParser.parse(sentenceActual, "Just say \"Hey!\"", ComponentType.LEXEME);
        String sentenceExpectedString = sentenceExpected.toString();
        String sentenceActualString = sentenceActual.toString();
        Assert.assertEquals(sentenceExpectedString, sentenceActualString);
    }

    @Test
    public void testMathExpressionParsing1() {
        String expression = "(2-4)/3+3";
        MathExpressionInterpreter interpreter = new MathExpressionInterpreter();
        int actualResult;
        int expectedResult = (2 - 4) / 2 + 3;
        actualResult = (int)interpreter.interpret(MathExpressionTransformer.convertToPolishNotation(expression));
        Assert.assertEquals(expectedResult, actualResult);
    }
}
