package gl.element.boden;

import gl.element.boden.Hefeteig;
import org.junit.jupiter.api.Test;
import vertrag.Allergen;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class HefeteigTest {
    @Test
    void hefeteig_getName(){
        Hefeteig hefeteig = new Hefeteig();
        assertEquals("Hefeteig", hefeteig.getName());
    }

    @Test
    void hefeteig_getHaltbarkeit(){
        Hefeteig hefeteig = new Hefeteig();
        assertEquals(Duration.ofHours(10), hefeteig.getHaltbarkeit());
    }

    @Test
    void hefeteig_getAllergene(){
        Hefeteig hefeteig = new Hefeteig();
        assertEquals(new HashSet<>(Collections.singletonList(Allergen.Gluten)), hefeteig.getAllergene());
    }

    @Test
    void hefeteig_getNaehrwert(){
        Hefeteig hefeteig = new Hefeteig();
        assertEquals(500, hefeteig.getNaehrwert());
    }
}