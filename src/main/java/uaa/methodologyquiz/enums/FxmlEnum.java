package uaa.methodologyquiz.enums;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Callable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import uaa.methodologyquiz.MainApp;
import uaa.methodologyquiz.controllers.*;
import static uaa.methodologyquiz.datagen.MethodologyGenerator.generateAllMethodologies;
import static uaa.methodologyquiz.datagen.QuestionGenerator.generateAllQuestions;

/**
 *
 * @author root
 */
public enum FxmlEnum {
    INDEX("Index"),
    METHODOLOGIES("Methodologies"),
    QUIZ("Quiz");

    private final String name;

    FxmlEnum(String name) {
        this.name = name;
    }

    public String fileName() {
        return this.name;
    }
    
    public boolean exists() {
        URL location = this.location();
        return location != null;
    }
    
    public URL location() {
        URL fileLocation = getClass()
                .getResource("/fxml/" + this.name + ".fxml");
        return fileLocation; 
    }
    
    private FXMLLoader loader(URL fileLocation) {
        FXMLLoader loader = new FXMLLoader(fileLocation);
        Map<Class, Callable<?>> initializer = new HashMap<Class, Callable<?>>(){
            {
                put(
                    QuizController.class, 
                    () -> new QuizController(
                        generateAllQuestions(), 
                        // generateAllMethodologies()
                        MainApp.MethodologiesToShow
                    )
                );
                put(
                    MethodologiesController.class, 
                    () -> new MethodologiesController(
                        MainApp.MethodologiesToShow
                    )
                );
            }
        };
        
        loader.setControllerFactory((Class<?> param) -> {
            Callable<?> callable = initializer.get(param);
            try {
                if (callable == null) {
                    return param.newInstance();
                } else {
                    return callable.call();
                }
            } catch (Exception ex) {
                throw new IllegalStateException(ex);
            }
        });
        return loader;
    }

    public Parent root() throws IOException {
        URL fileLocation = this.location();
        FXMLLoader loader = this.loader(fileLocation);
        Parent root = loader.load();
        return root;
    }
}
