import exceptions.ParseException;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws ParseException {
        Parser parser = new Parser();
        try {
            Drawer.draw(parser.parse("int a, *b, ***c, d;"), "res");
            Drawer.draw(parser.parse("int lsdjdksljdl, dsakdlk, dsakjdl, *****jsdlkajd;" +
                    "double dsadjl, *KDKSHKJ_____dkkd;"), "res1");
            Drawer.draw(parser.parse("int u90;"), "res2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
