import data.Token;
import exceptions.ParseException;
import node.Node;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Parser {
    Lexer lexer;
    String[] correctTypes = {"int", "double", "short", "char", "bool", "long"};

    private boolean checkName() {
        return Pattern.matches("^\\**[a-zA-Z]\\w*$", lexer.curId());
    }

    public Node parse(String str) throws ParseException {
        return parse(new Lexer(new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8))));
    }

    private Node parse(Lexer lexer) throws ParseException {
        this.lexer = lexer;
        this.lexer.nextToken();
        return V();
    }

    Node V() throws ParseException {
        Node res;
        switch (lexer.curToken()) {
            case NAME -> {
                res = new Node("V");
                res.ch.add(T());
                res.ch.add(N());
            }
            case EOF -> res = new Node("$");
            default -> throw new ParseException("Invalid Token", lexer.curPos());
        }
        return res;
    }

    boolean checkType() {
        return Arrays.stream(correctTypes).anyMatch(x -> x.equals(lexer.curId()));
    }

    Node T() throws ParseException {
        Node res;
        if (lexer.curToken() == Token.NAME) {
            res = new Node("T");
            if (!checkType()) throw new ParseException("Invalid name of type", lexer.curPos());
            res.ch.add(new Node(lexer.curId()));
            lexer.nextToken();
        } else {
            throw new ParseException("Invalid Token", lexer.curPos());
        }
        return res;
    }

    Node N() throws ParseException {
        Node res;
        if (lexer.curToken() == Token.NAME) {
            res = new Node("N");
            if (!checkName()) throw new ParseException("Invalid name of variable", lexer.curPos());
            res.ch.add(new Node(lexer.curId()));
            lexer.nextToken();
            switch (lexer.curToken()) {
                case COMMA -> {
                    res.ch.add(new Node(","));
                    lexer.nextToken();
                    res.ch.add(N());
                }
                case EOL -> {
                    res.ch.add(new Node(";"));
                    lexer.nextToken();
                    res.ch.add(V());
                }
                default -> throw new ParseException("Invalid Token", lexer.curPos());
            }
        } else {
            throw new ParseException("Invalid Token", lexer.curPos());
        }
        return res;
    }
}
