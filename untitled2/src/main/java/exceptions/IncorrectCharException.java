package exceptions;

public class IncorrectCharException extends ParseException {
    public IncorrectCharException(char ch, int pos) {
        super("Invalid character " + ch + " on position number " + pos);
    }
}
