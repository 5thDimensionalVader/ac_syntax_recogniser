public class Token {
//    The formal token definition for the AC language
    /*
    * floatdcl --> "f"
    * intdcl --> "i"
    * print --> "p"
    * id --> [a-e] | [g-h] | [j-o] | [q-z]
    * assign --> "="
    * plus --> "+"
    * minus --> "-"
    * inum --> [0-9]+
    * fnum --> [0-9]+.[0-9]+
    * blank --> " "+
    * */
    public static final int FLOATDCL_TOKEN = 0;
    public static final int INTDCL_TOKEN = 1;
    public static final int PRINT_TOKEN = 2;
    public static final int ID_TOKEN = 3;
    public static final int assign_operator = 4;
    public static final int plus_operator = 5;
    public static final int minus_operator = 6;
    public static final int number = 7;
    public static final int blank = 8;

    public static String[] TokenString = {"f", "i", "p", "id", "=", "+", "-", "number", "blank"};

//    write a function to convert token to string
    public static String toString(int k){
        if (k < 0 || k > blank){
            return "";
        }
        return TokenString[k];
    }
}
