package uaa.methodologyquiz.datagen;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import uaa.methodologyquiz.enums.DataFilesEnum;
import uaa.methodologyquiz.enums.MethodologiesEnum;

/**
 *
 * @author root
 */
public class GenFunctions {
    public static JSONObject getJSONFromFile(DataFilesEnum dataFile) {
        BufferedReader questionsReader = dataFile.fileReader();
        String questionsJSONString = "";
        try {
            String line = questionsReader.readLine();
            while(line != null) {
                questionsJSONString += line;
                line = questionsReader.readLine();
            }
        } catch (IOException ex) {
            Logger logger = Logger.getLogger(QuestionGenerator.class.getName());
            logger.log(Level.SEVERE, null, ex);
        }
        return new JSONObject(questionsJSONString);
    }
    
    public static JSONObject binarySearchJSONArray(
        JSONArray jsonArray, String value
    ){
        int l = -1, r = jsonArray.length();
        while(r-l > 1) {
            int m = (l+r)/2;
            JSONObject candidate = jsonArray.getJSONObject(m);
            if(candidate.has(value)){
                return candidate;
            }
            String key = candidate.keys().next();
            if(key.compareTo(value) < 0) {
                l = m;
            } else {
                r = m;
            }
        }
        return null;
    }
    
    public static ArrayList<String> JSONArrayToStringsList(
        JSONArray jsonArray,
        String key
    ) {
        ArrayList<String> strings = new ArrayList<>();
        for(int i=0; i<jsonArray.length(); i++) {
            String newString = jsonArray.getJSONObject(i).getString(key);
            strings.add(newString);
        }
        return strings;
    }
    
    public static ArrayList<MethodologiesEnum> JSONArrayToMethodologiesEnum(
        JSONArray methodologiesJSONArray
    ) {
        ArrayList<MethodologiesEnum> methodologies = new ArrayList<>();
        for(int i=0; i<methodologiesJSONArray.length(); i++) {
            String methodString = methodologiesJSONArray.getString(i);
            methodologies.add(MethodologiesEnum.valueOf(methodString));
        }
        return methodologies;
    }
}
