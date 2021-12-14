// import the necessary packages
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class Main {
    public static void main (String ... args) throws IOException{
        Parser parser = new Parser(new Scanner( new BufferedReader(new FileReader("src/ac_file"))));
//        Parser parser = new Parser(new Scanner( new BufferedReader(new FileReader("src/ac_test_prog"))));
        parser.run();
//        System.out.println("Syntax analysis performed successfully and no errors were found.");
    }
}
