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
        int randomVValue;
        int hashValue;
        ByteBuffer bytes = ByteBuffer.allocate(40);
        ArrayList<String> bucketRecordList;
        int outputRecordCount = 0;
        StringBuilder qualifyingRecords = new StringBuilder();

        for (int i = 0; i < hashTableCapacity; i++) {
            datasetATable.put(i, new ArrayList<>());
        }

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

                for (String s : bucketRecordList) {
                    if (Integer.parseInt(s.substring(33, 37)) == randomVValue) {
                        qualifyingRecords.append(s, 0, 10).append(",").
                                append(s, 12, 19).append(",").
                                append(new String(bytes.array()), 0, 10).append(",").append(new String(bytes.array()), 12, 19).
                                append(" , ");
                        outputRecordCount += 1;
                    }
                }

                bytes.clear();
            }

            fileInputStream.close();
            endTime = System.currentTimeMillis();
            System.out.println("Qualifying records:" + "\n"
                    + qualifyingRecords  + "\n" +
                            "Time taken to execute building the hash-based join: " + (endTime - startTime) + " milliseconds" + "\n"
                            + "Total number of qualifying records: " + outputRecordCount);
        }
    }
    public void buildBlockLevelNestedLoopJoin () {

    }
}
