package exceptions;

public class ParseException extends Exception {

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, int pos) {
        super(message + " at position number " + pos);
    }
}
