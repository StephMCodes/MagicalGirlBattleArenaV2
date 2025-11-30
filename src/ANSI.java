public class ANSI {

    //here we hold const strings with ANSI codes to have a more colourful application!

    //ESC[ octal is \033
    //then you add the ANSI code for your text edit

    //to use: concatenate to your string with +

    public static final String DEFAULT = "\033[0m";
    public static final String MAGENTA = "\033[35m";
    public static final String RED = "\033[31m";
    public static final String BLUE = "\033[34m";
    public static final String YELLOW = "\033[33m";

}
