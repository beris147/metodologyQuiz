package uaa.methodologyquiz.datagen;

import java.util.*;
import org.json.*;
import uaa.methodologyquiz.classes.*;
import static uaa.methodologyquiz.datagen.GenFunctions.binarySearchJSONArray;
import static uaa.methodologyquiz.datagen.GenFunctions.getJSONFromFile;
import uaa.methodologyquiz.enums.*;

/**
 *
 * @author root
 */
public class MethodologyGenerator {
    public static ArrayList<Methodology> generateAllMethodologies() {
        ArrayList<Methodology> methodologies = new ArrayList<>();
        for (MethodologiesEnum methodologyCode : MethodologiesEnum.values()) {
            Methodology methodology = generateMethodology(methodologyCode);
            methodologies.add(methodology);
        }
        return methodologies;
    }
    
    public static ArrayList<Methodology> generateMethodologies(
        ArrayList<MethodologiesEnum> methodologiesCodes
    ) {
        ArrayList<Methodology> methodologies = new ArrayList<>();
        methodologiesCodes
            .stream()
            .map(
                (MethodologiesEnum methodologyCode) -> {
                    return generateMethodology(methodologyCode);
                }
            ).forEachOrdered(
                (Methodology methodology) -> {
                    methodologies.add(methodology);
                }
            );
        return methodologies;
    }
    
    public static Methodology generateMethodology(
        MethodologiesEnum methodologyCode
    ) {
        JSONArray methodologiesJSON = getJSONFromFile(
            DataFilesEnum.METHODOLOGIES
        ).getJSONArray("methodologies");
        JSONObject methodologyJSON = binarySearchJSONArray(
            methodologiesJSON, 
            methodologyCode.name()
        ).getJSONObject(methodologyCode.name());
        return new Methodology(methodologyJSON, methodologyCode);
    }
}
