Matthew McAlarney

Student ID: 893999246

Section 1 - Code Compilation and Execution
    There are two ways that this application can be compiled and run:
        1. Through command line:
            a. Download the project
            b. Open a terminal and navigate to the working directory of this project; the working directory is titled "CS4432-Project1-MatthewMcAlarney"
            c. Compile the application through typing the following command: javac Application.java
            d. Run the application using the following command (the "3" corresponds to the BufferPool size used in the test case file as part of initialization): java Application 3
            e. From here, all commands in the test case file can be entered through command line in the order that they are presented. For each command that is entered, the application outputs a response message.
        2. Through IntelliJ:
            a. Download the project and open it in IntelliJ.
            b. Open a terminal in IntelliJ and make sure that you cd into the working directory of this project; the working directory is titled "CS4432-Project1-MatthewMcAlarney"
            c. In IntelliJ, go to Run -> Edit Configurations
            d. In Edit Configurations:
                I. Make sure that the Name field lists Application.
                II. Under Build and Run, make sure that Application is listed and 3 is entered as the argument to the main method in the Application class.
                III. Click OK to confirm the above configurations.
            e. Go to Run, and under the dropdown menu, click "Run 'Application'"
            f. A console session titled "Application" will pop up under the "Run" tab in IntelliJ, and the application will output the first message, "The program is ready for the next command."
            g. From here, all commands in the test case file can be entered in the console session in the order that they are presented. For each command that is entered, the application outputs a response message.

Section 2 - Test Results



Section 3 - Additional Design Decisions
    In the Application class:
        1. I included the option to exit the program. When the user types "Exit" into the terminal or console session, the message "Program Exited." will be displayed and the program will terminate.
    In the RecordRetriever class:
        1.