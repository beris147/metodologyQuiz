package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import uaa.methodologyquiz.enums.FxmlEnum;

/**
 *
 * @author root
 */
public class FXMLEnumTest {

    @Test
    public void checkIfFXMLFileExists() {
        for (FxmlEnum file : FxmlEnum.values()) {
            assertEquals(true, file.exists());
        }
    }
}
