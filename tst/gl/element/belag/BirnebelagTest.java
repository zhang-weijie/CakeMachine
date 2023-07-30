package gl.element.belag;

import gl.element.boden.Muerbeteig;
import org.junit.jupiter.api.Test;
import vertrag.Allergen;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class BirnebelagTest {
    @Test
    void birnebelag_getName(){
        Birnebelag birnebelag = new Birnebelag(new Muerbeteig());
        assertEquals("Muerbeteig+Birne", birnebelag.getName());
    }

    @Test
    void birnebelag_getHaltbarkeit(){
        Birnebelag birnebelag = new Birnebelag(new Muerbeteig());
        assertEquals(Duration.ofHours(3), birnebelag.getHaltbarkeit());
    }

    @Test
    void birnebelag_getAllergene(){
        Birnebelag birnebelag = new Birnebelag(new Muerbeteig());
        assertEquals(new HashSet<>(Arrays.asList(Allergen.Haselnuss, Allergen.Gluten)), birnebelag.getAllergene());
    }

    @Test
    void birnebelag_getNaehrwert(){
        Birnebelag birnebelag = new Birnebelag(new Muerbeteig());
        assertEquals(690, birnebelag.getNaehrwert());
    }

    @Test
    void birnebelag_getElement(){
        Birnebelag birnebelag = new Birnebelag(new Muerbeteig());
        assertEquals("Muerbeteig", birnebelag.getElement().getName());
    }
}