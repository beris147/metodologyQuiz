package uaa.methodologyquiz.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import uaa.methodologyquiz.MainApp;
import uaa.methodologyquiz.enums.FxmlEnum;

/**
 * FXML Controller class
 *
 * @author root
 */
public class MethodologiesController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void backToIndex() throws IOException {
        MainApp.changeScene(FxmlEnum.INDEX);
    }
}
