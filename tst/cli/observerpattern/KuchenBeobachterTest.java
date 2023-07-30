package cli.observerpattern;

import gl.Automat;
import gl.KuchenImpl;
import cli.oberserver.KuchenBeobachter;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class KuchenBeobachterTest {

    @Test
    void aktualisiere_whenInsertKuchen() {
        Automat automat = new Automat(3);
        KuchenBeobachter kuchenBeobachter = new KuchenBeobachter(automat);
        automat.meldeAn(kuchenBeobachter);
        automat.addHersteller("A");

        KuchenImpl kuchen1 = mock(KuchenImpl.class);
        when(kuchen1.getHersteller()).thenReturn(automat.getHersteller("A"));
        when(kuchen1.getFachnummer()).thenReturn(1);
        when(kuchen1.toString()).thenReturn("kuchen1 mit Fachnummer 1");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        automat.insertKuchen(kuchen1);

        String expected = "Eingefuegt:\tkuchen1 mit Fachnummer 1" + System.lineSeparator();
        assertEquals(expected, bos.toString());
    }

    @Test
    void aktualisiere_whenDeleteKuchen(){
        Automat automat = new Automat(3);
        automat.addHersteller("A");

        KuchenImpl kuchen1 = mock(KuchenImpl.class);
        when(kuchen1.getHersteller()).thenReturn(automat.getHersteller("A"));
        when(kuchen1.getFachnummer()).thenReturn(1);
        when(kuchen1.toString()).thenReturn("kuchen1 mit Fachnummer 1");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        automat.insertKuchen(kuchen1);

        KuchenBeobachter kuchenBeobachter = new KuchenBeobachter(automat);
        automat.meldeAn(kuchenBeobachter);

        automat.deleteKuchen(1);

        String expected = "Entfernt:\tkuchen1 mit Fachnummer 1" + System.lineSeparator();
        assertEquals(expected, bos.toString());
    }
}