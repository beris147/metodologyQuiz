package uaa.methodologyquiz.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import uaa.methodologyquiz.MainApp;
import uaa.methodologyquiz.classes.*;
import uaa.methodologyquiz.enums.*;
import uaa.methodologyquiz.functions.*;

/**
 * FXML Controller class
 *
 * @author root
 */
public class QuizController implements Initializable {

   
    @FXML
    private Button btnQ1;

    @FXML
    private Button btnQ2;

    @FXML
    private Button btnQ3;

    @FXML
    private Button btnQ4;

    @FXML
    private Button btnQ5;

    @FXML
    private Button btnQ6;

    @FXML
    private Button btnQ7;

    @FXML
    private Button btnQ8;

    @FXML
    private Button btnQ9;

    @FXML
    private Button btnQ10;

    @FXML
    private Label labelTest;

    @FXML
    private RadioButton b1;

    @FXML
    private RadioButton b2;

    @FXML
    private RadioButton b3;

    @FXML
    private RadioButton b4;

    @FXML
    private ListView<String> listViewMethodologies;

    @FXML
    private Text labelQuestion;

    private int questionNumber;
    
    private final ArrayList<Question> questions;
    private final ArrayList<Methodology> methodologies;
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> methodologiesNames = MethodologiesFunctions
                .getMethodologiesNames(this.methodologies);
        listViewMethodologies.setItems(methodologiesNames);
        setQuestion(1);
        System.out.println(questions.size());
    }
    
    public QuizController(
        ArrayList<Question> questions,
        ArrayList<Methodology> methodologies
    ) {
        this.questions = questions;
        this.methodologies = methodologies;
    }

    @FXML
    private void backToIndex() throws IOException {
        MainApp.changeScene(FxmlEnum.INDEX);
    }
    

    @FXML
    void handleButtonAction(ActionEvent event) {
        Control source = (Control) event.getSource();
        String buttonId = source.getId();
        switch (buttonId) {
            case "btnQ1":
                setQuestion(1);
                break;
            case "btnQ2":
                setQuestion(2);
                break;
            case "btnQ3":
                setQuestion(3);
                break;
            case "btnQ4":
                setQuestion(4);
                break;
            case "btnQ5":
                setQuestion(5);
                break;
            case "btnQ6":
                setQuestion(6);
                break;
            case "btnQ7":
                setQuestion(7);
                break;
            case "btnQ8":
                setQuestion(8);
                break;
            case "btnQ9":
                setQuestion(9);
                break;
            case "btnQ10":
                setQuestion(10);
                break;
        }

    }

    private void setQuestion(int question) {
        questionNumber = question;
        labelQuestion.setText("Pregunta " + question);
        b1.setText("Opcion 1,pregunta" + question);
        b2.setText("Opcion 2,pregunta" + question);
        b3.setText("Opcion 3,pregunta" + question);
        b4.setText("Opcion 4,pregunta" + question);
    }

    @FXML
    private void groupAction(ActionEvent event) {
        System.out.println("Radio button ");
    }

    @FXML
    void backQuestion(ActionEvent event) {
        if (questionNumber > 1) {
            questionNumber--;
            setQuestion(questionNumber);
        }

    }

    @FXML
    void nextQuestion(ActionEvent event) {
        if (questionNumber < 10) {
            questionNumber++;
            setQuestion(questionNumber);
        }
    }
    
}
