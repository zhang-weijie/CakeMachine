package gl.element.belag;

import gl.element.boden.Muerbeteig;
import org.junit.jupiter.api.Test;
import vertrag.Allergen;

import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ApfelbelagTest {
    @Test
    void apfelbelag_getName(){
        Apfelbelag apfelbelag = new Apfelbelag(new Muerbeteig());
        assertEquals("Muerbeteig+Apfel", apfelbelag.getName());
    }

    @Test
    void apfelbelag_getHaltbarkeit(){
        Apfelbelag apfelbelag = new Apfelbelag(new Muerbeteig());
        assertEquals(Duration.ofHours(4), apfelbelag.getHaltbarkeit());
    }

    @Test
    void apfelbelag_getAllergene(){
        Apfelbelag apfelbelag = new Apfelbelag(new Muerbeteig());
        assertEquals(new HashSet<>(Collections.singleton(Allergen.Gluten)), apfelbelag.getAllergene());
    }

    @Test
    void apfelbelag_getNaehrwert(){
        Apfelbelag apfelbelag = new Apfelbelag(new Muerbeteig());
        assertEquals(700, apfelbelag.getNaehrwert());
    }

    @Test
    void apfelbelag_getElement(){
        Apfelbelag apfelbelag = new Apfelbelag(new Muerbeteig());
        assertEquals("Muerbeteig", apfelbelag.getElement().getName());
    }
}