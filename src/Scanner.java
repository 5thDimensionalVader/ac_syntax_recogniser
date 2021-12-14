/*
 * Scanner to read an input (file input) for the Syntax Recogniser
 * */

//import Buffer and ArrayList classes

import java.io.BufferedReader;
import java.util.ArrayList;

public class Scanner {
    //    define the instance variables for the Scanner class
    public int token;
    private char character = ' ';
    private ArrayList<String> record = new ArrayList<>();
    private String word = "";
    private int number = 0;
    private Buffer buffer;

    //    define a Scanner method
    public Scanner(BufferedReader fileInput) {
        buffer = new Buffer(fileInput);
        token = Token.FLOATDCL_TOKEN;
    }

    public int getToken() {
        while (Character.isWhitespace(character)){
            character = buffer.get();
        }
        if (Character.isLetter(character)){
            word ="";
            while(Character.isLetter(character)){
                word += Character.toString(character);
                character = buffer.get();
            }

            switch (word){
                case "f":
                    character = buffer.get();
                    token = Token.FLOATDCL_TOKEN;
                    break;
                case "i":
                    character = buffer.get();
                    token = Token.INTDCL_TOKEN;
                    break;
                case "p":
                    character = buffer.get();
                    token = Token.PRINT_TOKEN;
                    break;
                default:
                    character = buffer.get();
                    token = Token.ID_TOKEN;
                    break;
            }
        } else if (Character.isDigit(character)){
            number = getNumber();
            token = Token.number;
        } else {
            switch (character){
                case '+':
                    character = buffer.get();
                    token = Token.plus_operator;
                    break;
                case '-':
                    character = buffer.get();
                    token = Token.minus_operator;
                    break;
                case '=':
                    character = buffer.get();
                    token = Token.assign_operator;
                    break;
                default:
                    error("Illegal character " + character + " on line: ");
                    break;
            }
        }
        record.add(Token.TokenString[token]);
//        System.out.println(" The token type is " + Token.TokenString[token]);
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

    private int getNumber(){
        int r = 0;
        do {
            r = r * 10 + Character.digit(character,10);
            character = buffer.get();
        } while (Character.isDigit(character));
        return r;
    }

}

// define a class for Buffer,
class Buffer {
    private String line = "";
    public int column = 0;
    public int line_position = 0;
    private BufferedReader input;

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
