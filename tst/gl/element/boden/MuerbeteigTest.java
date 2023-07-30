package gl.element.boden;

import org.junit.jupiter.api.Test;
import vertrag.Allergen;

import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class MuerbeteigTest {
    @Test
    void muerbeteig_getName(){
        Muerbeteig muerbeteig = new Muerbeteig();
        assertEquals("Muerbeteig", muerbeteig.getName());
    }

    @Test
    void muerbeteig_getHaltbarkeit(){
        Muerbeteig muerbeteig = new Muerbeteig();
        assertEquals(Duration.ofHours(12), muerbeteig.getHaltbarkeit());
    }

    @Test
    void muerbeteig_getAllergene(){
        Muerbeteig muerbeteig = new Muerbeteig();
        assertEquals(new HashSet<>(Collections.singletonList(Allergen.Gluten)), muerbeteig.getAllergene());
    }

    @Test
    void muerbeteig_getNaehrwert(){
        Muerbeteig muerbeteig = new Muerbeteig();
        assertEquals(600, muerbeteig.getNaehrwert());
    }
}