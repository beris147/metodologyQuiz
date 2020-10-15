package uaa.methodologyquiz.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import uaa.methodologyquiz.classes.Methodology;

/**
 * FXML Controller class
 *
 * @author root
 */
public class MethodologyInfoController implements Initializable {
    
    @FXML
    private Label nameLabel;
    @FXML
    private TextArea 
        descriptionText, 
        featuresText, 
        advantagesText, 
        disadvantagesText, 
        usagesText;
    
    private final Methodology methodology;
    
    public MethodologyInfoController(Methodology methodology) {
        this.methodology = methodology;
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.descriptionText.setText(this.methodology.getDescription());
        setListToTextArea(this.methodology.getAdvantages(), this.advantagesText);
        setListToTextArea(this.methodology.getDisadvantages(), this.disadvantagesText);
        setListToTextArea(this.methodology.getFeatures(), this.featuresText);
        setListToTextArea(this.methodology.getUsages(), this.usagesText);
        this.nameLabel.setText(this.methodology.getName());
    }
    
    private static void setListToTextArea(
        ArrayList<String> list, TextArea area
    ) {
        String listString = "";
        listString = list
                .stream()
                .map((string) -> "-" + string + "\n")
                .reduce(listString, String::concat);
        area.setText(listString);
    }
    
    @FXML
    private void closeWindow(ActionEvent event) {
        Node  source = (Node)  event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
}
