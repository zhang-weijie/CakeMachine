package gl.element.belag;

import gl.element.boden.Muerbeteig;
import org.junit.jupiter.api.Test;
import vertrag.Allergen;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class PuddingbelagTest {
    @Test
    void puddingbelag_getName(){
        Puddingbelag puddingbelag = new Puddingbelag(new Muerbeteig());
        assertEquals("Muerbeteig+Pudding", puddingbelag.getName());
    }

    @Test
    void puddingbelag_getHaltbarkeit(){
        Puddingbelag puddingbelag = new Puddingbelag(new Muerbeteig());
        assertEquals(Duration.ofHours(5), puddingbelag.getHaltbarkeit());
    }

    @Test
    void puddingbelag_getAllergene(){
        Puddingbelag puddingbelag = new Puddingbelag(new Muerbeteig());
        assertEquals(new HashSet<>(Arrays.asList(Allergen.Haselnuss, Allergen.Gluten, Allergen.Erdnuss)), puddingbelag.getAllergene());
    }

    @Test
    void puddingbelag_getNaehrwert(){
        Puddingbelag puddingbelag = new Puddingbelag(new Muerbeteig());
        assertEquals(750, puddingbelag.getNaehrwert());
    }

    @Test
    void puddingbelag_getElement(){
        Puddingbelag puddingbelag = new Puddingbelag(new Muerbeteig());
        assertEquals("Muerbeteig", puddingbelag.getElement().getName());
    }

}