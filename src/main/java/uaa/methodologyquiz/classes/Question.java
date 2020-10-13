package uaa.methodologyquiz.classes;

import java.util.*;
import org.json.*;

/**
 *
 * @author root
 */
public class Question {
    
    private String title;
    private String sentence;
    private ArrayList<Option> options;
    private Option answer;

    public Question() {
        this.answer = null;
    }
    
    public Question(JSONObject json) {
        this.title = json.getString("title");
        this.sentence = json.getString("sentence");
        this.options = new ArrayList<>();
        JSONArray optionsJSONArray = json.getJSONArray("options");
        for(int i=0; i<optionsJSONArray.length(); i++){
            JSONObject optionJSON = optionsJSONArray.getJSONObject(i);
            Option option = new Option(optionJSON);
            options.add(option);
        }
        this.answer = null;
    }

    public Question(String title, String sentence, ArrayList<Option> options) {
        this.title = title;
        this.sentence = sentence;
        this.options = options;
        this.answer = null;
    }

    public String getTitle() {
        return title;
    }

    public String getSentence() {
        return sentence;
    }

    public ArrayList<Option> getOptions() {
        return options;
    }

    public Option getAnswer() {
        return answer;
    }

    public void setAnswer(Option answer) {
        this.answer = answer;
    }
    
}
