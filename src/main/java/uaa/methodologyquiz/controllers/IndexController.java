package uaa.methodologyquiz.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import uaa.methodologyquiz.MainApp;
import static uaa.methodologyquiz.datagen.MethodologyGenerator.generateMethodologies;
import uaa.methodologyquiz.enums.FxmlEnum;
import uaa.methodologyquiz.enums.MethodologiesEnum;

/**
 * FXML Controller class
 *
 * @author root
 */
public class IndexController implements Initializable {
    
    public IndexController() {
        // MainApp.MethodologiesToShow = generateAllMethodologies();
        ArrayList<MethodologiesEnum> currentMethodologies = new ArrayList<MethodologiesEnum>() {
            {
                add(MethodologiesEnum.AM);
                add(MethodologiesEnum.ASD);
                add(MethodologiesEnum.AUP);
                add(MethodologiesEnum.CAF);
                add(MethodologiesEnum.DAD);
                add(MethodologiesEnum.ESP);
                add(MethodologiesEnum.XP);
                add(MethodologiesEnum.ZAVE);
            }
        };
        MainApp.MethodologiesToShow = generateMethodologies(currentMethodologies);
    }

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
    private void startQuiz() throws IOException {
        MainApp.changeScene(FxmlEnum.QUIZ);
    }
    
    @FXML
    private void seeMethodologies() throws IOException {
        MainApp.changeScene(FxmlEnum.METHODOLOGIES);
    }
    
    
}
