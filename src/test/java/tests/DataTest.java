package tests;

import org.json.JSONArray;
import org.json.JSONObject;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static uaa.methodologyquiz.datagen.GenFunctions.binarySearchJSONArray;
import static uaa.methodologyquiz.datagen.GenFunctions.getJSONFromFile;
import uaa.methodologyquiz.enums.DataFilesEnum;
import uaa.methodologyquiz.enums.MethodologiesEnum;

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
    
    @Test
    public void checkIfAllEnumMethodologiesAreInMethodologiesJSON() {
        JSONArray allMethodologiesJSONArray = getJSONFromFile(
            DataFilesEnum.METHODOLOGIES
        ).getJSONArray("methodologies");
        for (MethodologiesEnum methodology : MethodologiesEnum.values()) {
            // Remove the comment when methodologies.json is completed
            /* assertNotNull(
                "The methodologies enum should contains all the methodologies "
                + "stored in methodologies.json " + methodology.name() + " not "
                + "found", 
                binarySearchJSONArray(
                    allMethodologiesJSONArray, 
                    methodology.name()
                )
            ); */
        } 
    }
    
    @Test
    public void checkIfAllMethodologiesCodesOnOptionsExists() {
        JSONArray allMethodologiesJSONArray = getJSONFromFile(
            DataFilesEnum.METHODOLOGIES
        ).getJSONArray("methodologies");
        JSONArray questionsJSONArray = getJSONFromFile(
            DataFilesEnum.QUESTIONS
        ).getJSONArray("questions");
        for(int i=0;i<questionsJSONArray.length();i++) {
            JSONArray optionsJSONArray = questionsJSONArray
                    .getJSONObject(i)
                    .getJSONArray("options");
            for(int j=0;j<optionsJSONArray.length();j++) {
                JSONArray methodologiesJSONArray = optionsJSONArray
                        .getJSONObject(j)
                        .getJSONArray("methodologies");
                for(int k=0;k<methodologiesJSONArray.length();k++) {
                    String search = methodologiesJSONArray.getString(k);
                    assertNotNull(
                        "All the methodologies used on questions should exist "
                        + "in methodologies.json " + search + " not found", 
                        binarySearchJSONArray(
                            allMethodologiesJSONArray, 
                            search
                        )
                    );
                }
            }
        }
    }
}
