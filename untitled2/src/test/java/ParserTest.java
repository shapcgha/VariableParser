import exceptions.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    Parser parser;

    @BeforeEach
    public void intParser() {
        parser = new Parser();
    }

    private void parse(String str) throws ParseException {
        parser.parse(str);
    }

    @Test
    public void emptyTest() throws ParseException {
        parse("");
    }

    @Test
    public void oneVariableTest() throws ParseException {
        parse("int a;");
    }

    @Test
    public void multiplyVariableTest() throws ParseException {
        parse("int a, *b, c90, **********d90_sdjla;");
    }

    @Test
    public void multiplyTypeTest() throws ParseException {
        parse("int a, b, c, d; double a, b, c, d;");
    }

    @Test()
    public void incorrectTypeTest() throws ParseException {
        assertThrows(ParseException.class, () ->
                parse("integer a, b, c"));
    }

    @Test()
    public void incorrectVariableTest() throws ParseException {
        assertThrows(ParseException.class, () ->
                parse("int a*ds;"));
    }

    @Test()
    public void commaOrEOLMissTest() throws ParseException {
        assertThrows(ParseException.class, () ->
                parse("int a ds;"));
        assertThrows(ParseException.class, () ->
                parse("int a, ds"));
    }
}
