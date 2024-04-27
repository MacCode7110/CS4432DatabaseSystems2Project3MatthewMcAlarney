import java.io.IOException;
import java.util.Scanner;

/**
 * The driver class of this Java application
 */
public class Application {
    /**
     * Start of the execution of the Java program; parses and handles user input, which includes the hash-based join, and block-level nested-loop join.
     * @param args the list of command line arguments passed to main
     * @throws IOException
     */
    public static void main(String [] args) throws IOException {
        RecordRetriever rV = new RecordRetriever();
        System.out.println("Program is ready and waiting for user command.");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();
            //Include exit command to escape the program.
            if (userInput.equalsIgnoreCase("EXIT")) {
                System.out.println("Program Exited.");
                break;
            }
            if (userInput.equals("SELECT A.Col1, A.Col2, B.Col1, B.Col2 FROM A, B WHERE A.RandomV = B.RandomV")) {
                //Section 2 hash-based join
                rV.buildHashBasedJoin();
                System.out.println("Program is ready and waiting for user command.");
            }
            if (userInput.equals("SELECT count(*) FROM A, B WHERE A.RandomV > B.RandomV")) {
                //Section 3 block-level nested-loop join
                rV.buildBlockLevelNestedLoopJoin();
                System.out.println("Program is ready and waiting for user command.");
            }
        }
    }
}
