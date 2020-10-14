package uaa.methodologyquiz.classes;

import java.util.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author root
 */
public class Dialog {
    public static boolean showConfirmation(String action) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación requerida");
        alert.setHeaderText(action);
        alert.setContentText("¿Desea proseguir?");
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }
    
    public static void showError(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error en la solicitud");
        alert.setContentText(error);
        alert.showAndWait();
    }
    
    public static void floatingWindow(
        String title,
        String header,
        Node pane,
        AlertType type
    ) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.getDialogPane().setContent(pane);
        alert.showAndWait();
    }
}
