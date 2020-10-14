package uaa.methodologyquiz.functions;

import java.util.*;
import javafx.collections.*;
import uaa.methodologyquiz.classes.*;

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
}
