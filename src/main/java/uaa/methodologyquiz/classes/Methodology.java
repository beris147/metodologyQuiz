package uaa.methodologyquiz.classes;

import java.util.*;
import org.json.JSONObject;
import uaa.methodologyquiz.enums.*;
import static uaa.methodologyquiz.datagen.GenFunctions.JSONArrayToStringsList;

/**
 *
 * @author root
 */
public class Methodology {
    
    private String name;
    private String description;
    private MethodologiesEnum code;
    private ArrayList<String> features;
    private ArrayList<String> advantages;
    private ArrayList<String> disadvantages;
    private ArrayList<String> usages;

    public Methodology() {
    }
    
    public Methodology(JSONObject json, MethodologiesEnum code) {
        this.name = json.getString("name");
        this.description = json.getString("description");
        this.features = JSONArrayToStringsList(
            json.getJSONArray("features"), 
            "sentence"
        );
        this.advantages = JSONArrayToStringsList(
            json.getJSONArray("advantages"), 
            "sentence"
        );
        this.disadvantages = JSONArrayToStringsList(
            json.getJSONArray("disadvantages"), 
            "sentence"
        );
        this.usages = JSONArrayToStringsList(
            json.getJSONArray("usages"), 
            "sentence"
        );
        this.code = code;
    }
    
    public Methodology(
        String name, 
        String description, 
        ArrayList<String> features,
        ArrayList<String> advantages, 
        ArrayList<String> disadvantages, 
        ArrayList<String> usages
    ) {
        this.name = name;
        this.description = description;
        this.features = features;
        this.advantages = advantages;
        this.disadvantages = disadvantages;
        this.usages = usages;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getFeatures() {
        return features;
    }

    public ArrayList<String> getAdvantages() {
        return advantages;
    }

    public ArrayList<String> getDisadvantages() {
        return disadvantages;
    }

    public ArrayList<String> getUsages() {
        return usages;
    }

    public MethodologiesEnum getCode() {
        return code;
    }
}
