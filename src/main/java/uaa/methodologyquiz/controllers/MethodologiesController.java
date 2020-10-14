package uaa.methodologyquiz.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import uaa.methodologyquiz.MainApp;
import uaa.methodologyquiz.classes.*;
import uaa.methodologyquiz.enums.*;

/**
 * FXML Controller class
 *
 * @author root
 */
public class MethodologiesController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML
    private GridPane gridPane;

    private final ArrayList<Methodology> methodologies;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setArrayMethodologies();

    }

    public MethodologiesController(ArrayList<Methodology> methodologies) {
        this.methodologies = methodologies;
    }

    @FXML
    private void backToIndex() throws IOException {
        MainApp.changeScene(FxmlEnum.INDEX);
    }

    private void setArrayMethodologies() {
        int column = 0;
        int row = 1;

        for (int i = 0; i < this.methodologies.size(); i++) {

            Button questionButton = new Button("Metodologia"
                    + this.methodologies.get(i).getName());

            questionButton.getStyleClass().add("my-special-button");
            if (column == 3) {
                column = 0;
                row++;
            }

            gridPane.add(questionButton, column++, row); //(child,column,row)
            //set grid width
            gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
            gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
            gridPane.setMaxWidth(Region.USE_PREF_SIZE);

            //set grid height
            gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
            gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
            gridPane.setMaxHeight(Region.USE_PREF_SIZE);

            gridPane.setMargin(questionButton, new Insets(10));
        }
    }
}
