package com.suboch.task4.demo;

import com.suboch.task4.composite.ComponentType;
import com.suboch.task4.composite.IComponent;
import com.suboch.task4.composite.TextComponent;
import com.suboch.task4.functional.CompositeFunctional;
import com.suboch.task4.parser.TextComponentParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Demo {
    private static Logger logger = LogManager.getLogger(Demo.class);
    private static String filePath = "data";
    private static String fileName = "data.txt";

    public static void main(String[] args) {
        String textString = "";
        try {
            textString = new String(Files.readAllBytes(Paths.get(filePath, fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Create text object.
        TextComponent text = new TextComponent(ComponentType.TEXT);
        TextComponentParser textComponentParser = new TextComponentParser();
        textComponentParser.parse(text, textString, ComponentType.PARAGRAPH);

        //Collect text from text component object.
        logger.log(Level.DEBUG, text.toString());

        //Create functional object.
        CompositeFunctional functional = new CompositeFunctional();

        //Show sentences sorted by lexeme amount ascend.
        List<IComponent> sortedSentenceList = functional.sortSentencesByLexemeAmount(text);
        logComponentList("\nSentences sorted by lexeme amount:\n", sortedSentenceList);

        //Show words sorted by alphabet.
        List<IComponent> sortedWordsList = functional.sortWordsByABC(text);
        logWordList("\nWords sorted by alphabet:\n", sortedWordsList);

        //Delete all lexemes starts with given letter of the given length.
        TextComponent textCopy = (TextComponent) functional.deleteLexemesOfGivenFormat(text, 2, 'o');
        logger.log(Level.DEBUG, textCopy.toString());
    }

    public static void logComponentList(String message, List<IComponent> list) {
        logger.log(Level.DEBUG, message);
        list.forEach(p -> logger.log(Level.DEBUG, p));
    }

    public static List<IComponent> logWordList(String message, List<IComponent> list) {
        logger.log(Level.DEBUG, message);

        char c1 = list.get(0).toString().toLowerCase().charAt(0);
        char c2;
        String temp;
        List<String> result = new ArrayList<>();
        temp = list.get(0) + " ";
        for (int i = 1; i < list.size(); i++) {
            c2 = list.get(i).toString().toLowerCase().charAt(0);
            if (c1 == c2) {
                temp += list.get(i) + " ";
            } else {
                result.add(temp);
                temp = "";
                c1 = c2;
            }
        }

        //Log list by the rule in task.
        result.stream().filter(p -> !p.isEmpty()).forEach(p -> logger.log(Level.DEBUG, p));
        return list;
    }
}
