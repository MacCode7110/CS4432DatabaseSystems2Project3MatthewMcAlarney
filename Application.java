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
            if (userInput.startsWith("SELECT A.Col1, A.Col2, B.Col1, B.Col2 FROM A, B WHERE ")) {
                //Section 2 hash-based join
                rV.buildHashBasedJoin(Integer.parseInt(userInput.substring(56, userInput.indexOf(" ", 56))),
                        Integer.parseInt(userInput.substring(userInput.indexOf("=") + 4)));
            }
            if (userInput.startsWith("SELECT count(*) FROM A, B WHERE ")) {
                //Section 3 block-level nested-loop join
                rV.buildBlockLevelNestedLoopJoin(Integer.parseInt(userInput.substring(34, userInput.indexOf(" ", 34))),
                        Integer.parseInt(userInput.substring(userInput.indexOf(">") + 4)));
            }
        }
    }
}
