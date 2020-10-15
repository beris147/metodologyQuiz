package uaa.methodologyquiz.enums;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author root
 */
public enum DataFilesEnum {
    METHODOLOGIES("methodologies.json"),
    QUESTIONS("questions.json");
    
    private final String name;

    DataFilesEnum(String name) {
        this.name = name;
    }

    public String fileName() {
        return this.name;
    }
    
    public boolean exists() {
        InputStream stream = this.stream();
        return stream != null;
    }
    
     public InputStream stream() {
        InputStream in = getClass().getResourceAsStream("/data/"+this.name);
        return in; 
    }
    
    public BufferedReader fileReader() {
        InputStreamReader inStreamReader = new InputStreamReader(
            this.stream(), 
            StandardCharsets.UTF_8
        );
        BufferedReader reader = new BufferedReader(inStreamReader);
        return reader;
    }
}
