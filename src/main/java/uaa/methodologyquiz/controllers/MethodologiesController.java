package uaa.methodologyquiz.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import uaa.methodologyquiz.MainApp;
import uaa.methodologyquiz.classes.*;
import uaa.methodologyquiz.enums.*;
import static uaa.methodologyquiz.functions.MethodologiesFunctions.openMethodologyInfoDialog;

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
        for (
            int i = 0, column = 0, row = 1;
            i < this.methodologies.size();
            i++, column++
        ) {
            if (column == 3) {
                column = 0;
                row++;
            }
            Methodology methodology = this.methodologies.get(i);
            Button questionButton = new Button(methodology.getName());
            questionButton.setOnAction(
                (ActionEvent e) -> {
                    try {
                        openMethodologyInfoDialog(methodology);
                    } catch (IOException ex) {
                        Logger.getLogger(
                            MethodologiesController.class.getName()
                        ).log(Level.SEVERE, null, ex);
                    }
                }
            );
            questionButton.getStyleClass().add("my-special-button");
            gridPane.add(questionButton, column, row);
            gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
            gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
            gridPane.setMaxWidth(Region.USE_PREF_SIZE);
            gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
            gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
            gridPane.setMaxHeight(Region.USE_PREF_SIZE);
            GridPane.setMargin(questionButton, new Insets(10));
        }
    }
}
