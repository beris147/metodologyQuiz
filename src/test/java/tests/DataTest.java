package tests;

import org.json.JSONArray;
import org.json.JSONObject;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static uaa.methodologyquiz.datagen.GenFunctions.getJSONFromFile;
import uaa.methodologyquiz.enums.DataFilesEnum;

/**
 *
 * @author root
 */
public class DataTest {
    @Test
    public void checkIfMethodologiesJSONIsSorted() {
        JSONArray methodologiesJSON = getJSONFromFile(
            DataFilesEnum.METHODOLOGIES
        ).getJSONArray("methodologies");
        JSONObject auxJSON = methodologiesJSON.getJSONObject(0);
        String smallerKey = auxJSON.keys().next();
        for(int i=1;i<methodologiesJSON.length();i++){
            JSONObject methodology = methodologiesJSON.getJSONObject(i);
            String biggerKey = methodology.keys().next();
            assertTrue(
                "JSON array is not sorted for the methodologies " 
                + smallerKey + " and " + biggerKey, 
                smallerKey.compareTo(biggerKey) < 0
            );
            smallerKey = biggerKey;
        }
    }
}
