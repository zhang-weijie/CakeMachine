package gl.element.belag;

import gl.element.boden.Muerbeteig;
import org.junit.jupiter.api.Test;
import vertrag.Allergen;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class KirschebelagTest {
    @Test
    void kirschebelag_getName(){
        Kirschebelag kirschebelag = new Kirschebelag(new Muerbeteig());
        assertEquals("Muerbeteig+Kirsche", kirschebelag.getName());
    }

    @Test
    void kirschebelag_getHaltbarkeit(){
        Kirschebelag kirschebelag = new Kirschebelag(new Muerbeteig());
        assertEquals(Duration.ofHours(2), kirschebelag.getHaltbarkeit());
    }

    @Test
    void kirschebelag_getAllergene(){
        Kirschebelag kirschebelag = new Kirschebelag(new Muerbeteig());
        assertEquals(new HashSet<>(Arrays.asList(Allergen.Erdnuss, Allergen.Gluten)), kirschebelag.getAllergene());
    }

    @Test
    void kirschebelag_getNaehrwert(){
        Kirschebelag kirschebelag = new Kirschebelag(new Muerbeteig());
        assertEquals(680, kirschebelag.getNaehrwert());
    }

    @Test
    void kirschebelag_getElement(){
        Kirschebelag kirschebelag = new Kirschebelag(new Muerbeteig());
        assertEquals("Muerbeteig", kirschebelag.getElement().getName());
    }
}