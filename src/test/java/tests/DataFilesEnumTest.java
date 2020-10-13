package tests;

import java.io.*;
import java.util.logging.*;
import org.json.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import uaa.methodologyquiz.datagen.*;
import uaa.methodologyquiz.enums.*;

/**
 *
 * @author root
 */
public class DataFilesEnumTest {
    @Test
    public void checkIfDataFileExists() {
        for (DataFilesEnum file : DataFilesEnum.values()) {
            assertEquals(true, file.exists());
        } 
    }
    
    @Test
    public void checkIfAllDataAreJSONStrings() {
        for (DataFilesEnum file : DataFilesEnum.values()) {
            BufferedReader questionsReader = file.fileReader();
            String maybeJSON = "";
            try {
                String line = questionsReader.readLine();
                while(line != null) {
                    maybeJSON += line;
                    line = questionsReader.readLine();
                }
            } catch (IOException ex) {
                Logger logger = Logger.getLogger(QuestionGenerator.class.getName());
                logger.log(Level.SEVERE, null, ex);
            }
            assertEquals(true, checkIfStringIsValidJSON(maybeJSON));
        } 
    }
    
    private static boolean checkIfStringIsValidJSON(String string) {
        try {
            JSONObject jsonObject = new JSONObject(string);
        } catch (JSONException ex) {
            return false;
        }
        return true;
    }
}
