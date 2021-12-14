/*
 * Scanner to read an input (file input) for the Syntax Recogniser
 * */

//import Buffer and ArrayList classes

import java.io.BufferedReader;
import java.util.ArrayList;
import static java.lang.Integer.parseInt;

public class Scanner {
    //    define the instance variables for the Scanner class
    public int token;
    private String character = "";
    private final ArrayList<String> record = new ArrayList<>();
    private String word = "";
    private int number = 0;
    private final Buffer buffer;

    //    define a Scanner method
    public Scanner(BufferedReader fileInput) {
        buffer = new Buffer(fileInput);
        token = Token.floatdcl_token;
    }

//    boolean function to check for alphabet
    public static boolean isAlpha(String s) {
        return s != null && s.matches("^[a-zA-Z]*$");
    }
//    boolean function to check for integer
    public static boolean isIntegralDigit(String i){
        return i != null && i.matches("[+-]?[0-9]+");
    }
//    boolean function to check for float
    public static boolean isFloatDigit(String f){
        return f != null && f.matches("[+-]?\\d+(\\.\\d+)?([Ee][+-]?\\d+)?");
    }

    public int getToken() {
        while (character.contains(" ")){
            character = String.valueOf(buffer.get());
//            basically do nothing when it is a whitespace or empty string
        }
        if (isAlpha(character)){
            word = "";
            while(isAlpha(character)){
                word = word.concat(character);
                character = String.valueOf(buffer.get());
            }

            switch (word){
                case "f":
                    character = String.valueOf(buffer.get());
                    token = Token.floatdcl_token;
                    break;
                case "i":
                    character = String.valueOf(buffer.get());
                    token = Token.intdcl_token;
                    break;
                case "p":
                    character = String.valueOf(buffer.get());
                    token = Token.print_token;
                    break;
                default:
                    character = String.valueOf(buffer.get());
                    token = Token.id_token;
                    break;
            }
        } else if (isIntegralDigit(character)){
            token = Token.inum;
        } else if(isFloatDigit(character)) {
            token = Token.fnum;
        } else {
            switch (character){
                case "+":
                    character = String.valueOf(buffer.get());
                    token = Token.plusop;
                    break;
                case "-":
                    character = String.valueOf(buffer.get());
                    token = Token.minusop;
                    break;
                case "=":
                    character = String.valueOf(buffer.get());
                    token = Token.assignop;
                    break;
                default:
                    error("Illegal character " + character + " on line: ");
                    break;
            }
        }
        record.add(Token.TokenArray[token]);
        System.out.println(" The token type is " + Token.TokenArray[token]);
        return token;
    }

    public void match(int value) {
        if (token != value) {
            error("Invalid token " + Token.toString(token) + " -- expecting " + Token.toString(value) + " on line ");
            System.exit(1);
        }
        token = getToken();
    }

    public void error(String e_msg) {
        System.err.println(e_msg + this.buffer.line_position + " at column "+this.buffer.column);
        System.out.println(record);
        System.exit(1);
    }

}

// define a class for Buffer,
class Buffer {
    private String line = "";
    public int column = 0;
    public int line_position = 0;
    private final BufferedReader input;

    public Buffer(BufferedReader input){this.input = input;} //Buffer

    public char get() {
        column++;
        if (column >= line.length()) {
            try {
                line = input.readLine();
            } catch (Exception e) {
                System.err.println("Invalid read operation on line: " );
                System.exit(1);
            } // try
            if (line == null) {
                System.out.println("Syntax analysis performed successfully and no errors were found");
                System.exit(0);
            }
            column = 0;
            line_position++;
            System.out.println(line);
            line = line + "\n";
        }
        return line.charAt(column);
    }
}
