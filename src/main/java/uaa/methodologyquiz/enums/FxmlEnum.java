package uaa.methodologyquiz.enums;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

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

    public Parent root() throws IOException {
        URL fileLocation = this.location();
        Parent root = FXMLLoader.load(fileLocation);
        return root;
    }
}
