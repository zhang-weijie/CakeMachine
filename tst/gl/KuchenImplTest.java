package gl;

import gl.element.Element;
import gl.element.boden.Muerbeteig;
import jdk.nashorn.internal.ir.WhileNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import vertrag.Allergen;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KuchenImplTest {
    Automat automat;

    @BeforeEach
    void setUp() {
        automat = new Automat(3);
        automat.addHersteller("A");
    }

    @Test
    void getName() {
        KuchenImpl kuchen = new KuchenImpl(new Muerbeteig(), automat.getHersteller("A"));
        assertEquals("Muerbeteig", kuchen.getName());
    }

    @Test
    void getHaltbarkeit_beforeInsertion() {
        KuchenImpl kuchen = new KuchenImpl(new Muerbeteig(), automat.getHersteller("A"));
        assertEquals(Duration.ofHours(12), kuchen.getHaltbarkeit());
    }

    @Test
    void getHaltbarkeit_afterInsertion() {
        KuchenImpl kuchen = new KuchenImpl(new Muerbeteig(), automat.getHersteller("A"));
        KuchenImpl spyKuchen = spy(kuchen);
        automat.insertKuchen(spyKuchen);
        spyKuchen.getHaltbarkeit();
        verify(spyKuchen, times(3)).getInsertionsdatum();
    }

    @Test
    void getAllergene() {
        Muerbeteig muerbeteig = new Muerbeteig();
        KuchenImpl kuchen = new KuchenImpl(muerbeteig, automat.getHersteller("A"));
        assertEquals(muerbeteig.getAllergene(), kuchen.getAllergene());
    }

    @Test
    void getNaehrwert() {
        Muerbeteig muerbeteig = new Muerbeteig();
        KuchenImpl kuchen = new KuchenImpl(muerbeteig, automat.getHersteller("A"));
        assertEquals(muerbeteig.getNaehrwert(), kuchen.getNaehrwert());
    }

    @Test
    void getPreis() {
        Muerbeteig muerbeteig = new Muerbeteig();
        KuchenImpl kuchen = new KuchenImpl(muerbeteig, automat.getHersteller("A"));
        assertEquals(muerbeteig.getPreis(), kuchen.getPreis());
    }

    @Test
    void setFachnummer() {
        KuchenImpl kuchen = new KuchenImpl(new Muerbeteig(), automat.getHersteller("A"));
        KuchenImpl spyKuchen = spy(kuchen);
        automat.insertKuchen(spyKuchen);
        verify(spyKuchen).setFachnummer(1);
    }

    @Test
    void setInsertionsdatum() {
        KuchenImpl kuchen = new KuchenImpl(new Muerbeteig(), automat.getHersteller("A"));
        KuchenImpl spyKuchen = spy(kuchen);
        automat.insertKuchen(spyKuchen);
        verify(spyKuchen).setInsertionsdatum(any(Instant.class));
    }

    @Test
    void setInspektionsdatum() {
        KuchenImpl kuchen = new KuchenImpl(new Muerbeteig(), automat.getHersteller("A"));
        KuchenImpl spyKuchen = spy(kuchen);
        automat.insertKuchen(spyKuchen);
        automat.updateKuchen(spyKuchen.getFachnummer());
        verify(spyKuchen).setInspektionsdatum(any(Date.class));
    }

    @Test
    void getHersteller() {
        HerstellerImpl hersteller = automat.getHersteller("A");
        KuchenImpl kuchen = new KuchenImpl(new Muerbeteig(), hersteller);
        assertEquals(hersteller, kuchen.getHersteller());
    }

    @Test
    void getFachnummer_beforeInsert() {
        KuchenImpl kuchen = new KuchenImpl(new Muerbeteig(), automat.getHersteller("A"));
        assertEquals(0, kuchen.getFachnummer());
    }

    @Test
    void getFachnummer_afterInsert() {
        KuchenImpl kuchen = new KuchenImpl(new Muerbeteig(), automat.getHersteller("A"));
        automat.insertKuchen(kuchen);
        assertEquals(1, kuchen.getFachnummer());
    }

    @Test
    void getInsertionsdatum_beforeInsert() {
        KuchenImpl kuchen = new KuchenImpl(new Muerbeteig(), automat.getHersteller("A"));
        assertNull(kuchen.getInsertionsdatum());
    }

    @Test
    void getInsertionsdatum_afterInsert(){
        KuchenImpl kuchen = new KuchenImpl(new Muerbeteig(), automat.getHersteller("A"));
        automat.insertKuchen(kuchen);
        assertNotNull(kuchen.getInsertionsdatum());
    }

    @Test
    void getInspektionsdatum_beforeUpdate() {
        KuchenImpl kuchen = new KuchenImpl(new Muerbeteig(), automat.getHersteller("A"));
        assertNull(kuchen.getInspektionsdatum());
    }

    @Test
    void getInspektionsdatum_afterUpdate() {
        KuchenImpl kuchen = new KuchenImpl(new Muerbeteig(), automat.getHersteller("A"));
        automat.insertKuchen(kuchen);
        automat.updateKuchen(kuchen.getFachnummer());
        assertNotNull(kuchen.getInspektionsdatum());
    }

    @Test
    void getElement() {
        Muerbeteig muerbeteig = new Muerbeteig();
        KuchenImpl kuchen = new KuchenImpl(muerbeteig, automat.getHersteller("A"));
        assertEquals(muerbeteig, kuchen.getElement());
    }

    @Test
    void testToString() {
        KuchenImpl kuchen = new KuchenImpl(new Muerbeteig(), automat.getHersteller("A"));
        assertEquals("{Fachnummer: 0, Hersteller: A, Inspeketionsdatum: null, Preis: 12, Name: Muerbeteig, Haltbarkeit: PT12H, Allergene: [Gluten], Naehrwert: 600}", kuchen.toString());
    }
}