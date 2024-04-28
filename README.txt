Matthew McAlarney

Student ID: 893999246

Section 1 - Code Compilation and Execution
    There are two ways that this application can be compiled and run:
        1. Through command line:
            a. Download the project
            b. Open a terminal and navigate to the working directory of this project; the working directory is titled "CS4432DatabaseSystems2Project3MatthewMcAlarney"
            c. Compile the application through typing the following command: javac Application.java
            d. Run the application using the following command: java Application
            e. From here, the hash-based join and block-level nested-loop join query commands can be entered. For each command that is entered, the application outputs response message(s).
        2. Through IntelliJ:
            a. Download the project and open it in IntelliJ.
            b. Open a terminal in IntelliJ and make sure that you cd into the working directory of this project; the working directory is titled "CS4432DatabaseSystems2Project3MatthewMcAlarney"
            c. In IntelliJ, go to Run -> Edit Configurations
            d. In Edit Configurations:
                I. Make sure that the Name field lists Application.
                II. Under Build and Run, make sure that Application is listed.
                III. Click OK to confirm the configurations as they are.
            e. Go to Run, and under the dropdown menu, click "Run 'Application'"
            f. A console session titled "Application" will pop up under the "Run" tab in IntelliJ, and the application will output the first message, "Program is ready and waiting for user command."
            g. From here, the hash-based join and block-level nested-loop join query commands can be entered. For each command that is entered, the application outputs response message(s).

Section 2 - Test Results

    Both the hash-based and block-level nested-loop joins successfully work:

    Below are the input commands and output messages in terminal that demonstrate the successful functionality of both join queries:

    Block-level nested-loop join:

    Program is ready and waiting for user command.
    SELECT count(*) FROM A, B WHERE A.RandomV > B.RandomV
    Time taken to execute building the block-level nested-loop join: 3418 milliseconds
    Total count of qualifying records: 48183340
    Program is ready and waiting for user command.

    Hash-based join:
    (Additional note: The number of qualifying records printed as a result of calling the hash-based join is 195956, which should be the correct record count for this query.)

    (The three dots represent many more records that are printed; earlier terminal output gets cut off after printing 195956 records.)
    ...
    B99-Rec098,Name098 , A99-Rec030,Name030,B99-Rec098,Name098 , A05-Rec067,Name067,B99-Rec099,Name099 , A07-Rec009,Name009,B99-Rec099,Name099 , A10-Rec090,Name090,B99-Rec099,Name099 , A13-Rec013,Name013,B99-Rec099,Name099 , A18-Rec100,Name100,B99-Rec099,Name099 , A30-Rec020,Name020,B99-Rec099,Name099 , A33-Rec027,Name027,B99-Rec099,Name099 , A33-Rec080,Name080,B99-Rec099,Name099 , A43-Rec051,Name051,B99-Rec099,Name099 , A47-Rec020,Name020,B99-Rec099,Name099 , A59-Rec095,Name095,B99-Rec099,Name099 , A71-Rec056,Name056,B99-Rec099,Name099 , A78-Rec098,Name098,B99-Rec099,Name099 , A79-Rec036,Name036,B99-Rec099,Name099 , A82-Rec007,Name007,B99-Rec099,Name099 , A83-Rec098,Name098,B99-Rec099,Name099 , A84-Rec013,Name013,B99-Rec099,Name099 , A86-Rec056,Name056,B99-Rec099,Name099 , A89-Rec070,Name070,B99-Rec099,Name099 , A91-Rec028,Name028,B99-Rec099,Name099 , A96-Rec031,Name031,B99-Rec099,Name099 , A02-Rec019,Name019,B99-Rec100,Name100 , A05-Rec021,Name021,B99-Rec100,Name100 , A25-Rec030,Name030,B99-Rec100,Name100 , A38-Rec065,Name065,B99-Rec100,Name100 , A42-Rec071,Name071,B99-Rec100,Name100 , A44-Rec062,Name062,B99-Rec100,Name100 , A44-Rec091,Name091,B99-Rec100,Name100 , A64-Rec025,Name025,B99-Rec100,Name100 , A64-Rec100,Name100,B99-Rec100,Name100 , A82-Rec080,Name080,B99-Rec100,Name100 , A90-Rec027,Name027,B99-Rec100,Name100 , A90-Rec041,Name041,B99-Rec100,Name100 , A95-Rec049,Name049,B99-Rec100,Name100 ,
    Time taken to execute building the hash-based join: 2443 milliseconds
    Program is ready and waiting for user command.

Section 3 - Additional Design Decisions
    In the Application class:
        1. I included the option to exit the program. When the user types "Exit" into the terminal or console session, the message "Program Exited." will be displayed and the program will terminate.
    In the RecordRetriever class:
        1. For the hash-based join, I decided to keep the hash table of records unsorted and used a linear search to look for the desired randomV value in the current iteration.
        2. I included the following class variables that store information about the project datasets Project3Dataset-A and Project3Dataset-B:
            a. private final static int TOTALNUMBEROFFILESPERDATASET = 99;
            b. private final static int TOTALNUMBEROFBYTESPERFILE = 4000;
            c. private final static int TOTALNUMBEROFRECORDSPERFILE = 100;