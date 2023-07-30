package simulation.simulation1;

import gl.Automat;
import gl.KuchenImpl;
import org.junit.jupiter.api.Test;
import simulation.SimulationUtil;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class DeleteThread1Test {
    @Test
    void delete_checkOutput(){
        Automat automat = SimulationUtil.init(3);

        KuchenImpl kuchen1 = mock(KuchenImpl.class);
        when(kuchen1.getHersteller()).thenReturn(automat.getHersteller("Hersteller1"));
        when(kuchen1.getFachnummer()).thenReturn(1);
        when(kuchen1.toString()).thenReturn("kuchen1 mit Fachnummer 1");

        KuchenImpl kuchen2 = mock(KuchenImpl.class);
        when(kuchen2.getHersteller()).thenReturn(automat.getHersteller("Hersteller1"));
        when(kuchen2.getFachnummer()).thenReturn(2);
        when(kuchen2.toString()).thenReturn("kuchen2 mit Fachnummer 2");

        KuchenImpl kuchen3 = mock(KuchenImpl.class);
        when(kuchen3.getHersteller()).thenReturn(automat.getHersteller("Hersteller1"));
        when(kuchen3.getFachnummer()).thenReturn(3);
        when(kuchen3.toString()).thenReturn("kuchen3 mit Fachnummer 3");

        automat.insertKuchen(kuchen1);
        automat.insertKuchen(kuchen2);
        automat.insertKuchen(kuchen3);

        Random random = mock(Random.class);
        when(random.nextInt(automat.readAllKuchen().size())).thenReturn(2).thenReturn(0).thenReturn(1);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        DeleteThread1 deleteThread1 = new DeleteThread1(automat, random);

        deleteThread1.delete();
        deleteThread1.delete();
        deleteThread1.delete();

        String expected =
                "Entfernen:\tkuchen3 mit Fachnummer 3" + System.lineSeparator() +
                        "Entfernen:\tkuchen1 mit Fachnummer 1" + System.lineSeparator() +
                        "Entfernen:\tkuchen2 mit Fachnummer 2" + System.lineSeparator();
        assertEquals(expected, bos.toString());
    }
}