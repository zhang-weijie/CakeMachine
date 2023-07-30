package simulation.simulation3alternative;

import gl.Automat;
import gl.KuchenImpl;
import org.junit.jupiter.api.Test;
import simulation.SimulationUtil;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class DeleteThread3Test {

    @Test
    void deleteOldestKuchen_normal() {
        Automat automat = SimulationUtil.init(3);

        KuchenImpl kuchen1 = mock(KuchenImpl.class);
        when(kuchen1.getHersteller()).thenReturn(automat.getHersteller("Hersteller1"));
        when(kuchen1.getInspektionsdatum()).thenReturn(new Date(1));
        when(kuchen1.getFachnummer()).thenReturn(1);
        when(kuchen1.toString()).thenReturn("kuchen1 mit Fachnummer 1");

        KuchenImpl kuchen2 = mock(KuchenImpl.class);
        when(kuchen2.getHersteller()).thenReturn(automat.getHersteller("Hersteller1"));
        when(kuchen2.getInspektionsdatum()).thenReturn(new Date(2));
        when(kuchen2.getFachnummer()).thenReturn(2);
        when(kuchen2.toString()).thenReturn("kuchen2 mit Fachnummer 2");

        KuchenImpl kuchen3 = mock(KuchenImpl.class);
        when(kuchen3.getHersteller()).thenReturn(automat.getHersteller("Hersteller1"));
        when(kuchen3.getInspektionsdatum()).thenReturn(new Date(0));
        when(kuchen3.getFachnummer()).thenReturn(3);
        when(kuchen3.toString()).thenReturn("kuchen3 mit Fachnummer 3");

        automat.insertKuchen(kuchen1);
        automat.insertKuchen(kuchen2);
        automat.insertKuchen(kuchen3);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        DeleteThread3 deleteThread3 = new DeleteThread3(automat, 1);

        deleteThread3.deleteOldestKuchen();
        deleteThread3.deleteOldestKuchen();
        deleteThread3.deleteOldestKuchen();

        String expected =
                        "DeleteThread 1:\tEntfernen:\tkuchen3 mit Fachnummer 3" + System.lineSeparator() +
                        "DeleteThread 1:\tEntfernen:\tkuchen1 mit Fachnummer 1" + System.lineSeparator() +
                        "DeleteThread 1:\tEntfernen:\tkuchen2 mit Fachnummer 2" + System.lineSeparator();
        assertEquals(expected, bos.toString());
    }

    @Test
    void deleteOldestKuchen_noInspketionsdatum(){
        Automat automat = SimulationUtil.init(3);

        KuchenImpl kuchen1 = mock(KuchenImpl.class);
        when(kuchen1.getHersteller()).thenReturn(automat.getHersteller("Hersteller1"));
        when(kuchen1.getInspektionsdatum()).thenReturn(null);
        when(kuchen1.getFachnummer()).thenReturn(1);
        when(kuchen1.toString()).thenReturn("kuchen1 mit Fachnummer 1");

        automat.insertKuchen(kuchen1);

        DeleteThread3 deleteThread3 = new DeleteThread3(automat, 1);
        assertFalse(deleteThread3.deleteOldestKuchen());
    }
}