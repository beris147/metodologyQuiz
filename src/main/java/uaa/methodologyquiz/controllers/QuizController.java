package uaa.methodologyquiz.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import uaa.methodologyquiz.MainApp;
import uaa.methodologyquiz.classes.*;
import uaa.methodologyquiz.classes.Dialog;
import uaa.methodologyquiz.enums.*;
import uaa.methodologyquiz.functions.*;

/**
 * FXML Controller class
 *
 * @author root
 */
public class QuizController implements Initializable {
    
    @FXML
    private Button btnNextQuestion, btnPrevQuestion, btnFinishQuiz;
    @FXML
    private ListView<String> listViewMethodologies;
    @FXML
    private VBox optionsBox, questionButtonsBox;
    @FXML
    private Label questionTitle, questionSentence;

    private int questionNumber;
    private final ArrayList<Question> questions;
    private final ArrayList<Methodology> methodologies;
    private final HashMap<MethodologiesEnum, Integer> answersMap;
    
    private static final int WINNERS = 3;
    
    public QuizController(
        ArrayList<Question> questions,
        ArrayList<Methodology> methodologies
    ) {
        this.questions = questions;
        this.methodologies = methodologies;
        this.answersMap = new HashMap<>();
        this.questionNumber = 0;
    }
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.btnFinishQuiz.setDisable(true);
        this.listViewMethodologies.setItems(
            MethodologiesFunctions.getMethodologiesNames(this.methodologies)
        );
        for(int i=0;i<this.questions.size();i++) {
            Button questionButton = new Button("Pregunta " + (i + 1));
            onActionSetQuestion(questionButton, i);
            questionButton
                .getStyleClass()
                .addAll("question-button", "unanswered-question");
            questionButtonsBox.getChildren().add(questionButton);
        }
        this.setQuestion(0);
    }
    
    private static XYChart.Series getDataSeriesForMap (
        HashMap<MethodologiesEnum, Integer> map,
        int skip,
        int limit
    ) {
        XYChart.Series dataSeries = new XYChart.Series();
        sortMap(map)
            .keySet()
            .stream()
            .skip(skip)
            .limit(limit)
            .map(MethodologiesEnum::methodology)
            .forEachOrdered(
                (Methodology methodology) -> {
                    dataSeries
                        .getData()
                        .add(new XYChart.Data(
                            methodology.getName(), 
                            map.get(methodology.getCode())
                        ));
                }
            );
        return dataSeries;
    }
    
    private BarChart getResultsChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Metodologias");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Puntos");
       
        BarChart barChart = new BarChart(xAxis, yAxis);
        
        XYChart.Series winnersSeries = getDataSeriesForMap(
            this.answersMap, 
            0,
            WINNERS
        );
        XYChart.Series losersSeries = getDataSeriesForMap(
            this.answersMap, 
            WINNERS, 
            this.answersMap.size()
        );
        winnersSeries.setName("Recomendaciones");
        losersSeries.setName("Otras");
        
        barChart.getData().addAll(winnersSeries, losersSeries);
        return barChart;
    }
    
    private void updatePoints() {
        this.answersMap.clear();
        this.questions
            .stream()
            .filter((Question question) -> question.getAnswer() != null)
            .forEach(
                (Question question) -> {
                    question
                        .getAnswer()
                        .getMethodologies()
                        .stream()
                        .map((Methodology methodology) -> methodology.getCode())
                        .forEach(
                            (MethodologiesEnum code) -> {
                                this.answersMap.put(
                                    code,
                                    this.answersMap.getOrDefault(code, 0) + 1
                                );
                            }
                        );
                }
            );
        long answeredQuestions = this.questions
            .stream()
            .filter((Question question) -> question.getAnswer() != null)
            .count();
        this.btnFinishQuiz.setDisable(!(answeredQuestions == questions.size()));
    }
    
    private void updateMethodologiesList() {
        this.listViewMethodologies.getItems().clear();
        sortMap(this.answersMap)
            .keySet()
            .stream()
            .forEachOrdered(
                (MethodologiesEnum methodologyCode) -> {
                    this.listViewMethodologies
                        .getItems()
                        .add(
                            methodologyCode 
                            + " Total: " 
                            + this.answersMap.get(methodologyCode)
                        );
                }
            );
    }
    
    private static LinkedHashMap<MethodologiesEnum, Integer> sortMap(
        HashMap<MethodologiesEnum, Integer> answersMap
    ){
        return answersMap
            .entrySet()
            .stream()
            .sorted(Entry.comparingByValue(Comparator.reverseOrder()))
            .collect(
                Collectors.toMap(
                    (Entry<MethodologiesEnum, Integer> entry) -> entry.getKey(), 
                    (Entry<MethodologiesEnum, Integer> entry) -> entry.getValue(),
                    (Integer e1, Integer e2) -> e1, 
                    LinkedHashMap::new
                )
            );
    }
    
    private void onActionSetQuestion(Button button, int questionNum) {
        button.setOnAction(
            (ActionEvent e) -> this.setQuestion(questionNum)
        );
    }
    
    private void setQuestion(int questionIndex) {
        this.questionNumber = questionIndex;
        Question currentQuestion = this.questions.get(questionIndex);
        this.questionSentence.setText(currentQuestion.getSentence());
        this.questionTitle.setText(currentQuestion.getTitle());
        this.optionsBox.getChildren().clear();
        ToggleGroup group = new ToggleGroup();
        currentQuestion
            .getOptions()
            .stream() 
            .forEachOrdered(
                (Option option) -> {
                    RadioButton radioOption = 
                            new RadioButton(option.getSentence());
                    radioOption.setToggleGroup(group);
                    if(currentQuestion.getAnswer() == option){
                        radioOption.setSelected(true);
                    }
                    radioOption.setOnAction(
                        (ActionEvent e) -> {
                            currentQuestion.setAnswer(option);
                            this.updatePoints();
                            this.updateMethodologiesList();
                            this.changeButtonStyleClass(
                                (Button) questionButtonsBox
                                    .getChildren()
                                    .get(questionIndex)
                            );
                        }
                    );
                    this.optionsBox.getChildren().add(radioOption);
                }
            );
        this.disableEnableNavigationButtons();
    }
    
    private void disableEnableNavigationButtons() {
        this.btnNextQuestion.setDisable(
            this.questionNumber == this.questions.size() - 1
        );
        this.btnPrevQuestion.setDisable(this.questionNumber == 0);
    }
    
    
    private void changeButtonStyleClass(Button button) {
        button.getStyleClass().remove("unanswered-question");
        button.getStyleClass().add("answered-question");
    }
    
    @FXML
    private void backToIndex() throws IOException {
        if(Dialog.showConfirmation("Ir al inicio, se perderá el progreso")) {
            MainApp.changeScene(FxmlEnum.INDEX);
        }
    }
    
    @FXML
    private void showTopMethodologies() throws IOException {
        if(Dialog.showConfirmation("Terminar questionario")) {
            if(this.answersMap.isEmpty()){
                Dialog.showError("Contestar al menos una pregunta");
            } else {
                Dialog.floatingWindow(
                    "Resultados", 
                    "Resultados del test", 
                    this.getResultsChart(), 
                    Alert.AlertType.INFORMATION
                );
                MainApp.MethodologiesToShow.clear();
                sortMap(this.answersMap)
                    .keySet()
                    .stream()
                    .limit(3)
                    .map(MethodologiesEnum::methodology)
                    .forEachOrdered(MainApp.MethodologiesToShow::add);
                MainApp.changeScene(FxmlEnum.METHODOLOGIES);
            }
        }
    }
    
    @FXML
    private void prevQuestion(ActionEvent event) {
        if (this.questionNumber > 0) {
            this.setQuestion(--questionNumber);
        }
    }
    
    @FXML
    private void nextQuestion(ActionEvent event) {
        if (questionNumber < this.questions.size() - 1) {
            this.setQuestion(++questionNumber);
        }
    } 
    
}
