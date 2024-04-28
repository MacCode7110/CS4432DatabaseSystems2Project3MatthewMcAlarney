import java.nio.ByteBuffer;
import java.util.*;
import java.io.IOException;
import java.io.FileInputStream;
public class RecordRetriever {
    private final static int TOTALNUMBEROFFILESPERDATASET = 99;
    private final static int TOTALNUMBEROFBYTESPERFILE = 4000;

    public void buildHashBasedJoin() throws IOException {
        long startTime = System.currentTimeMillis();
        long endTime;
        Hashtable<Integer, ArrayList<String>> datasetATable = new Hashtable<>();
        int hashTableCapacity = 50;
        FileInputStream fileInputStream = null;
        ByteBuffer bytes = ByteBuffer.allocate(40);
        int randomVValue;
        int hashValue;
        ArrayList<String> bucketRecordList;
        StringBuilder qualifyingRecords = new StringBuilder();

        //Initialize the hash table with buckets 0-50.
        for (int i = 0; i < hashTableCapacity; i++) {
            datasetATable.put(i, new ArrayList<>());
        }

        //Fill the buckets of the hash table with records from dataset A.
        //Records are assigned to buckets using equation hashValue = randomVValue % hashtableCapacity
        for (int i = 0; i < TOTALNUMBEROFFILESPERDATASET; i++) {

            try {
                fileInputStream = new FileInputStream("Project3Dataset-A/A" + (i + 1) + ".txt");
            } catch (IOException e) {
                System.out.println("File " + (i + 1) + " could not be opened from Project3Dataset-A.");
            }

            for (int j = 0; j < TOTALNUMBEROFBYTESPERFILE; j += 40) {
                assert fileInputStream != null;
                fileInputStream.getChannel().read(bytes, j);
                randomVValue = Integer.parseInt(new String(Arrays.copyOfRange(bytes.array(), 33, 37)));
                hashValue = randomVValue % hashTableCapacity;

                if (datasetATable.containsKey(hashValue)) {
                    bucketRecordList = datasetATable.get(hashValue);
                    bucketRecordList.add(new String(bytes.array()));
                    datasetATable.put(hashValue, bucketRecordList);
                }

                bytes.clear();
            }
        }

        //Iterative over datasetB and build the qualifying records StringBuilder based on the randomVValue equality condition.
        for (int i = 0; i < TOTALNUMBEROFFILESPERDATASET; i++) {

            try {
                fileInputStream = new FileInputStream("Project3Dataset-B/B" + (i + 1) + ".txt");
            } catch (IOException e) {
                System.out.println("File " + (i + 1) + " could not be opened from Project3Dataset-B.");
            }

            for (int j = 0; j < TOTALNUMBEROFBYTESPERFILE; j += 40) {

                fileInputStream.getChannel().read(bytes, j);
                randomVValue = Integer.parseInt(new String(Arrays.copyOfRange(bytes.array(), 33, 37)));
                hashValue = randomVValue % hashTableCapacity;
                bucketRecordList = datasetATable.get(hashValue);

                //Linear search for the current randomV value over the selected bucket.
                for (String s : bucketRecordList) {
                    if (Integer.parseInt(s.substring(33, 37)) == randomVValue) {
                        qualifyingRecords.append(s, 0, 10).append(",").
                                append(s, 12, 19).append(",").
                                append(new String(bytes.array()), 0, 10).append(",").append(new String(bytes.array()), 12, 19).
                                append(" , ");
                    }
                }

                bytes.clear();
            }

            fileInputStream.close();
            endTime = System.currentTimeMillis();
            System.out.println("Qualifying records:" + "\n"
                    + qualifyingRecords  + "\n" +
                            "Time taken to execute building the hash-based join: " + (endTime - startTime) + " milliseconds");
        }
    }
    public void buildBlockLevelNestedLoopJoin () throws IOException {
        long startTime = System.currentTimeMillis();
        long endTime;
        FileInputStream fileInputStream = null;
        ByteBuffer bytes = ByteBuffer.allocate(40);
        int randomVValue;
        int qualifyingRecordCount = 0;
        String [] fileRecords = new String[100]; //Array used to hold all records in a single file in memory.

        //Iterate over each file in datasetA
        for (int i = 0; i < TOTALNUMBEROFFILESPERDATASET; i++) {

            try {
                fileInputStream = new FileInputStream("Project3Dataset-A/A" + (i + 1) + ".txt");
            } catch (IOException e) {
                System.out.println("File " + (i + 1) + " could not be opened from Project3Dataset-A.");
            }

            //Put all records in the selected file in datasetA into memory.
            //fileRecords variable is reused between all files in datasetA and only stores the contents of one file for a given iteration of checking the join condition.
            for (int j = 0; j < TOTALNUMBEROFBYTESPERFILE; j += 40) {
                assert fileInputStream != null;
                fileInputStream.getChannel().read(bytes, j);
                fileRecords[j/40] = new String(bytes.array());
                bytes.clear();
            }

            for (int k = 0; k < TOTALNUMBEROFFILESPERDATASET; k++) {

                try {
                    fileInputStream = new FileInputStream("Project3Dataset-B/B" + (i + 1) + ".txt");
                } catch (IOException e) {
                    System.out.println("File " + (i + 1) + " could not be opened from Project3Dataset-B.");
                }

                for (int l = 0; l < TOTALNUMBEROFBYTESPERFILE; l+=40) {
                    fileInputStream.getChannel().read(bytes, l);
                    randomVValue = Integer.parseInt(new String(Arrays.copyOfRange(bytes.array(), 33, 37)));

                    for (String s : fileRecords) {
                        if (Integer.parseInt(s.substring(33, 37)) > randomVValue) {
                            qualifyingRecordCount++;
                        }
                    }

                    bytes.clear();
                }
            }
        }

        fileInputStream.close();
        endTime = System.currentTimeMillis();
        System.out.println("Time taken to execute building the hash-based join: " + (endTime - startTime) + " milliseconds" + "\n"
                + "Total count of qualifying records: " + qualifyingRecordCount);
    }
}
