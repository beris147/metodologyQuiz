package uaa.methodologyquiz.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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
    private ListView<String> listViewMethodologies;
    @FXML
    private VBox optionsBox, questionButtonsBox;
    @FXML
    private Label questionTitle, questionSentence;

    private int questionNumber;
    private final ArrayList<Question> questions;
    private final ArrayList<Methodology> methodologies;
    
    public QuizController(
        ArrayList<Question> questions,
        ArrayList<Methodology> methodologies
    ) {
        this.questions = questions;
        this.methodologies = methodologies;
    }
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.listViewMethodologies.setItems(
            MethodologiesFunctions.getMethodologiesNames(this.methodologies)
        );
        for(int i=0;i<this.questions.size();i++) {
            Button questionButton = new Button("Pregunta " + (i + 1));
            onActionSetQuestion(questionButton, i);
            questionButton.getStyleClass().add("question-button");
            questionButtonsBox.getChildren().add(questionButton);
        }
        this.setQuestion(0);
    }
    
    private void onActionSetQuestion(Button button, int questionNum) {
        button.setOnAction(
            (ActionEvent e) -> this.setQuestion(questionNum)
        );
    }

    @FXML
    private void backToIndex() throws IOException {
        MainApp.changeScene(FxmlEnum.INDEX);
    }
    
    private void setQuestion(int questionIndex) {
        Question currentQuestion = this.questions.get(questionIndex);
        this.questionSentence.setText(currentQuestion.getSentence());
        this.questionTitle.setText(currentQuestion.getTitle());
        this.optionsBox.getChildren().clear();
        currentQuestion
            .getOptions()
            .stream()
            .forEachOrdered(
                (Option option) -> {
                    RadioButton radioOption = 
                            new RadioButton(option.getSentence());
                    this.optionsBox.getChildren().add(radioOption);
                }
            );
    }
    
    @FXML
    void backQuestion(ActionEvent event) {
        if (this.questionNumber > 0) {
            this.setQuestion(--questionNumber);
        }
    }
    
    @FXML
    void nextQuestion(ActionEvent event) {
        if (questionNumber < this.questions.size() - 1) {
            this.setQuestion(++questionNumber);
        }
    } 
    
}
