package uaa.methodologyquiz.classes;

import java.util.*;
import org.json.JSONObject;
import static uaa.methodologyquiz.datagen.GenFunctions.JSONArrayToMethodologiesEnum;
import static uaa.methodologyquiz.datagen.MethodologyGenerator.generateMethodologies;
import uaa.methodologyquiz.enums.MethodologiesEnum;

/**
 *
 * @author root
 */
public class Option {
    
    private String sentence; 
    private ArrayList<Methodology> methodologies;

    public Option() {
    }
    
    public Option(JSONObject json) {
        this.sentence = json.getString("sentence");
        ArrayList<MethodologiesEnum> methodologiesEnum = 
            JSONArrayToMethodologiesEnum(json.getJSONArray("methodologies"));
        this.methodologies = generateMethodologies(methodologiesEnum);
    }

    public Option(String sentence, ArrayList<Methodology> methodologies) {
        this.sentence = sentence;
        this.methodologies = methodologies;
    }

    public String getSentence() {
        return sentence;
    }

    public ArrayList<Methodology> getMethodologies() {
        return methodologies;
    }
    
    
}
