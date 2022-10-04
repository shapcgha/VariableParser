import data.Token;
import exceptions.IncorrectCharException;
import exceptions.ParseException;

import java.io.IOException;
import java.io.InputStream;

public class Lexer {
    InputStream is;
    private char curChar;
    private int curPos;
    private String curId;
    private Token curToken;

    public Lexer(InputStream is) throws ParseException {
        this.is = is;
        curPos = 0;
        nextChar();
    }

    private void nextChar() throws ParseException {
        curPos++;
        try {
            curChar = (char) is.read();
        } catch (IOException e) {
            throw new ParseException("Unable to read character");
        }
    }

    public void nextToken() throws ParseException {
        StringBuilder str = new StringBuilder();
        boolean id = false;
        while (Character.isWhitespace(curChar)) {
            nextChar();
        }

        while (Character.isLetterOrDigit(curChar) || '*' == curChar || '_' == curChar) {
            str.append(curChar);
            nextChar();
            id = true;
        }

        if (id) {
            curToken = Token.NAME;
            curId = str.toString();
            return;
        }

        switch (curChar) {
            case ',' -> curToken = Token.COMMA;
            case ';' -> curToken = Token.EOL;
            case (char) -1 -> curToken = Token.EOF;
            default -> throw new IncorrectCharException(curChar, curPos);
        }
        nextChar();
    }

    public Token curToken() {
        return curToken;
    }

    public int curPos() {
        return curPos;
    }

    public String curId() { return curId; }
}
