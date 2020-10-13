package uaa.methodologyquiz.datagen;

import java.util.*;
import org.json.*;
import uaa.methodologyquiz.classes.*;
import static uaa.methodologyquiz.datagen.GenFunctions.getJSONFromFile;
import uaa.methodologyquiz.enums.DataFilesEnum;

/**
 *
 * @author root
 */
public class QuestionGenerator {
    public static ArrayList<Question> generateAllQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        JSONArray questionsJSONArray = 
            getJSONFromFile(DataFilesEnum.QUESTIONS).getJSONArray("questions");
        for(int i=0;i<questionsJSONArray.length();i++){
            JSONObject questionJSON = questionsJSONArray.getJSONObject(i);
            Question question = new Question(questionJSON);
            questions.add(question);
        }
        return questions;
    }
}
