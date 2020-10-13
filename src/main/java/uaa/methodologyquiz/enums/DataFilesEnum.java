package uaa.methodologyquiz.enums;

import java.io.*;

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
        InputStream in = this.stream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return reader;
    }
}
