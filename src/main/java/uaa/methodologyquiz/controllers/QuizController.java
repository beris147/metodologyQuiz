package uaa.methodologyquiz.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;
import uaa.methodologyquiz.MainApp;
import uaa.methodologyquiz.enums.FxmlEnum;

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

    private int numberQuestion;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ObservableList<String> items = FXCollections.observableArrayList(
                "Codificar", "Cascada", "Scrum", "Prototipado");
        listViewMethodologies.setItems(items);
        setQuestion(1);
    }

    @FXML
    private void backToIndex() throws IOException {
        MainApp.changeScene(FxmlEnum.INDEX);
    }

    @FXML
    void handleButtonAction(ActionEvent event) {
        System.out.println("Hola");

        switch (((Control) event.getSource()).getId()) {
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
        numberQuestion = question;
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
        if (numberQuestion > 1) {
            numberQuestion--;
            setQuestion(numberQuestion);
        }

    }

    @FXML
    void nextQuestion(ActionEvent event) {
        if (numberQuestion < 10) {
            numberQuestion++;
            setQuestion(numberQuestion);
        }
    }
    
}
