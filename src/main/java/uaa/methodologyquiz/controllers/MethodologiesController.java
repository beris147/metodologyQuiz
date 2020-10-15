package uaa.methodologyquiz.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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
    private VBox methodologiesBox;
    @FXML
    private ScrollPane scrollPane;

    private final ArrayList<Methodology> methodologies;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.setArrayMethodologies();
    }

    public MethodologiesController(ArrayList<Methodology> methodologies) {
        this.methodologies = methodologies;
    }

    @FXML
    private void backToIndex() throws IOException {
        MainApp.changeScene(FxmlEnum.INDEX);
    }
    
    private void setArrayMethodologies() {
        HBox rowBox = new HBox();
        this.scrollPane.setFitToWidth(true);
        for(int i=0, col=0; i<this.methodologies.size(); i++, col++) {
            if (col == 3) {
                col = 0;
                addRowToVBox(this.methodologiesBox, rowBox);
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
            questionButton.getStyleClass().add("methodologies-button");
            questionButton.wrapTextProperty().setValue(true);
            HBox.setMargin(questionButton, new Insets(10));
            rowBox.getChildren().add(questionButton);
        }
        if (!rowBox.getChildren().isEmpty()) {
            addRowToVBox(this.methodologiesBox, rowBox);
        }
    }
    
    private static void addRowToVBox(VBox box, HBox row) {
        HBox auxBox = new HBox();
        auxBox.setAlignment(Pos.CENTER);
        auxBox.setMinHeight(200);
        auxBox.getChildren().addAll(row.getChildren());
        box.getChildren().add(auxBox);
        row.getChildren().clear();
    }
}
