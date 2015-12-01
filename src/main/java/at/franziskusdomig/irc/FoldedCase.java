package at.franziskusdomig.irc;

public class FoldedCase {

    public static String toLowerCase(String str) {
        return str.replace("{", "[")
                  .replace("}", "]")
                  .replace("|", "\\")
                  .replace("^", "~")
                  .toLowerCase();
    }
}
