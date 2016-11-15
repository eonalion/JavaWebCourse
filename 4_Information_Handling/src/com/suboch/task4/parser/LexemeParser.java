package com.suboch.task4.parser;

import com.suboch.task4.composite.ComponentType;
import com.suboch.task4.composite.LeafComponent;
import com.suboch.task4.composite.LexemeComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 */
public class LexemeParser implements ILexemeParser {
    private static Logger logger = LogManager.getLogger(LexemeParser.class);
    private static final String LEFT_PUNCTUATION_REGEXP = "['].+";
    private static final String RIGHT_PUNCTUATION_REGEXP = ".+[,.':;!?]";

    @Override
    public void parse(LexemeComponent lexeme, String s) {
        Pattern patternLeft = Pattern.compile(LEFT_PUNCTUATION_REGEXP);
        Pattern patternRight = Pattern.compile(RIGHT_PUNCTUATION_REGEXP);

        //Add left punctuation symbol
        int leftPunctIndex = 0;
        if (patternLeft.matcher(s).matches()) {
            lexeme.addComponent(new LeafComponent("" + s.charAt(leftPunctIndex), ComponentType.PUNCTUATION));
            s = s.substring(1);
        }

        int rightPunctIndex = s.length() - 1;
        ArrayList<Character> rightPunctSymbols = new ArrayList<>();
        while (patternRight.matcher(s).matches()) {
            rightPunctSymbols.add(s.charAt(rightPunctIndex));
            s = s.substring(0, rightPunctIndex);
            rightPunctIndex--;
        }

        lexeme.addComponent(new LeafComponent(s, ComponentType.WORD));
        rightPunctSymbols.forEach(p -> lexeme.addComponent(new LeafComponent(p + "", ComponentType.PUNCTUATION)));
    }
}
