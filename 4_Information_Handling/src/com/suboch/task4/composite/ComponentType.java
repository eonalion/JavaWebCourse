package com.suboch.task4.composite;

/**
 *
 */
public enum ComponentType {
    TEXT,
    PARAGRAPH,
    SENTENCE,
    LEXEME,
    WORD,
    PUNCTUATION;

    private String regExp;
    private String rightDelimiter = "";
    private String leftDelimiter = "";

    static {
        PARAGRAPH.regExp = "(?:\\t)|(?:[^\\^]\\t)(?=\\$)?";
        SENTENCE.regExp = "(?<=\\.)\\s(?=[A-Z])";
        LEXEME.regExp = "\\s";
        PARAGRAPH.leftDelimiter = "\t";
        PARAGRAPH.rightDelimiter = "\n";
        LEXEME.rightDelimiter = " ";
    }

    public String getRegExp() {
        return regExp;
    }

    public String getRightDelimiter() {
        return rightDelimiter;
    }

    public String getLeftDelimiter() {
        return leftDelimiter;
    }
}
