import data.Token;
import exceptions.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class LexerTest {

    Lexer lexer;

    private void initLexer(String str) throws ParseException {
        lexer = new Lexer(new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    public void baseTestName() throws ParseException {
        initLexer("*lol");
        lexer.nextToken();
        Assertions.assertEquals(lexer.curToken(), Token.NAME);
    }

    @Test
    public void baseTestComma() throws ParseException {
        initLexer(",");
        lexer.nextToken();
        Assertions.assertEquals(lexer.curToken(), Token.COMMA);
    }

    @Test
    public void baseTestEOL() throws ParseException {
        initLexer(";");
        lexer.nextToken();
        Assertions.assertEquals(lexer.curToken(), Token.EOL);
    }

    @Test
    public void baseTestEOF() throws ParseException {
        initLexer("");
        lexer.nextToken();
        Assertions.assertEquals(lexer.curToken(), Token.EOF);
    }

    @Test
    public void fullTest() throws ParseException {
        initLexer("int sdla, sjdlakj, jdslakj; \n double sldjals;");
        check(lexer);
    }

    @Test
    public void ExtraWhiteSpacesTest() throws ParseException {
        initLexer("int sdla          , sjdlakj   , \n\n\tjdslakj     ; \n double    sldjals       ;");
        check(lexer);
    }

    private void check(Lexer lexer) throws ParseException {
        lexer.nextToken();
        Assertions.assertEquals(lexer.curToken(), Token.NAME);
        lexer.nextToken();
        Assertions.assertEquals(lexer.curToken(), Token.NAME);
        lexer.nextToken();
        Assertions.assertEquals(lexer.curToken(), Token.COMMA);
        lexer.nextToken();
        Assertions.assertEquals(lexer.curToken(), Token.NAME);
        lexer.nextToken();
        Assertions.assertEquals(lexer.curToken(), Token.COMMA);
        lexer.nextToken();
        Assertions.assertEquals(lexer.curToken(), Token.NAME);
        lexer.nextToken();
        Assertions.assertEquals(lexer.curToken(), Token.EOL);
        lexer.nextToken();
        Assertions.assertEquals(lexer.curToken(), Token.NAME);
        lexer.nextToken();
        Assertions.assertEquals(lexer.curToken(), Token.NAME);
        lexer.nextToken();
        Assertions.assertEquals(lexer.curToken(), Token.EOL);
        lexer.nextToken();
        Assertions.assertEquals(lexer.curToken(), Token.EOF);
    }
}
