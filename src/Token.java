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
    *
    * */

    public static final int floatdcl_token = 0;
    public static final int intdcl_token = 1;
    public static final int print_token = 2;
    public static final int id_token = 3;
    public static final int assignop = 4;
    public static final int plusop = 5;
    public static final int minusop = 6;
    public static final int inum = 7;
    public static final int fnum = 8;

    public static String[] TokenArray = {"floatdcl", "intdcl", "print", "id", "assign", "plus", "minus", "inum", "fnum"};

//    write a function to convert token to string
    public static String toString(int k){
        if (k < 0 || k > fnum){
            return "";
        }
        return TokenArray[k];
    }
}
