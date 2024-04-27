import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.io.IOException;
import java.io.FileInputStream;
public class RecordRetriever {
    private final static int TOTALNUMBEROFFILES = 99;
    private final static int TOTALNUMBEROFBYTESPERFILE = 4000;
    public void buildHashBasedJoin() throws IOException {
        Hashtable <Integer, ArrayList<String>> datasetATable = new Hashtable<>();
        int hashTableCapacity = 50;
        FileInputStream fileInputStream;
        int randomVValue;
        ByteBuffer bytes = ByteBuffer.allocate(40);

        for (int i = 0; i < hashTableCapacity; i++) {
            datasetATable.put(i, new ArrayList<>());
        }

        for (int i = 0; i < TOTALNUMBEROFFILES; i++) {

            fileInputStream = new FileInputStream("Project3Dataset-A/A" + (i + 1) + ".txt");

            for (int j = 0; j < TOTALNUMBEROFBYTESPERFILE; j+=40) {
                fileInputStream.getChannel().read(bytes, j);
                randomVValue = Integer.parseInt(new String(Arrays.copyOfRange(bytes.array(), 33, 37)));
                if(datasetATable.containsKey(randomVValue % hashTableCapacity)) {

                }
            }
        }

    }
    public void buildBlockLevelNestedLoopJoin() {

    }
}
