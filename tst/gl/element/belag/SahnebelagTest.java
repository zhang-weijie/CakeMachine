package gl.element.belag;

import gl.element.boden.Muerbeteig;
import org.junit.jupiter.api.Test;
import vertrag.Allergen;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class SahnebelagTest {
    @Test
    void sahnebelag_getName(){
        Sahnebelag sahnebelag = new Sahnebelag(new Muerbeteig());
        assertEquals("Muerbeteig+Sahne", sahnebelag.getName());
    }

    @Test
    void sahnebelag_getHaltbarkeit(){
        Sahnebelag sahnebelag = new Sahnebelag(new Muerbeteig());
        assertEquals(Duration.ofHours(6), sahnebelag.getHaltbarkeit());
    }

    @Test
    void sahnebelag_getAllergene(){
        Sahnebelag sahnebelag = new Sahnebelag(new Muerbeteig());
        assertEquals(new HashSet<>(Arrays.asList(Allergen.Haselnuss, Allergen.Gluten, Allergen.Sesamsamen)), sahnebelag.getAllergene());
    }

    @Test
    void sahnebelag_getNaehrwert(){
        Sahnebelag sahnebelag = new Sahnebelag(new Muerbeteig());
        assertEquals(800, sahnebelag.getNaehrwert());
    }

    @Test
    void sahnebelag_getElement(){
        Sahnebelag sahnebelag = new Sahnebelag(new Muerbeteig());
        assertEquals("Muerbeteig", sahnebelag.getElement().getName());
    }
}