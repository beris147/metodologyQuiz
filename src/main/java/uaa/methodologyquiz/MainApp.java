package uaa.methodologyquiz;

import java.io.IOException;
import java.util.Arrays;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uaa.methodologyquiz.enums.FxmlEnum;

public class MainApp extends Application {
    
    private static Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        MainApp.stage = stage;
        Scene scene = MainApp.getNewScene(FxmlEnum.INDEX);
        MainApp.addStylesheetsToScene(scene, "/styles/index.css");
        MainApp.showStage("QUIZ", scene);
    }

    private static Scene getNewScene(FxmlEnum fxml) throws IOException {
        Parent root = fxml.root();
        Scene scene = new Scene(root);
        return scene;
    }
    
    private static void addStylesheetsToScene(Scene scene, String ...styles) {
        scene.getStylesheets().addAll(Arrays.asList(styles));
    }
    
    private static void showStage(String title, Scene scene) {
        MainApp.stage.setTitle(title);
        MainApp.stage.setScene(scene);
        MainApp.stage.show();
    }
    
    public static void changeScene(FxmlEnum fxml) throws IOException{
       Parent root = fxml.root();
       MainApp.stage.getScene().setRoot(root);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
