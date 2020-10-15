package uaa.methodologyquiz.functions;

import java.io.IOException;
import java.util.*;
import javafx.collections.*;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uaa.methodologyquiz.classes.*;
import uaa.methodologyquiz.controllers.MethodologyInfoController;
import uaa.methodologyquiz.enums.*;

/**
 *
 * @author root
 */
public class MethodologiesFunctions {
    public static ObservableList<String> getMethodologiesNames(
        ArrayList<Methodology> methodologies
    ) {
        ObservableList<String> list = FXCollections.observableArrayList();
        methodologies.forEach((Methodology method) -> {
            list.add(method.getName());
            
        });
        return list;
    }
    
    /* @FXML
    private void onOpenDialog(ActionEvent event) throws IOException {
        openMethodologyInfoDialog(MethodologiesEnum.AM.methodology());
    } */
    
    public static void openMethodologyInfoDialog(
        Methodology methodology
    ) throws IOException {
        MethodologyInfoController controller = 
                new MethodologyInfoController(methodology);
        Scene scene = new Scene(
            FxmlEnum.METHODOLOGYINFO.root(controller), 
            600, 
            400
        );
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
