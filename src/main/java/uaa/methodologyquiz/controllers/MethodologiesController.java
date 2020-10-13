package uaa.methodologyquiz.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.fxml.*;
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
     * @param url
     * @param rb
     */
    
    private final ArrayList<Methodology> methodologies;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public MethodologiesController(ArrayList<Methodology> methodologies) {
        this.methodologies = methodologies;
    }
    
    @FXML
    private void backToIndex() throws IOException {
        MainApp.changeScene(FxmlEnum.INDEX);
    }
}
