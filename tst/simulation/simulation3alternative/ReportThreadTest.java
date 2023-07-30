package simulation.simulation3alternative;

import gl.Automat;
import gl.KuchenImpl;
import org.junit.jupiter.api.Test;
import simulation.SimulationUtil;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class ReportThreadTest {

    @Test
    void report_normal() {
        Automat automat = SimulationUtil.init(3);

        KuchenImpl kuchen1 = mock(KuchenImpl.class);
        when(kuchen1.getHersteller()).thenReturn(automat.getHersteller("Hersteller1"));
        when(kuchen1.getInspektionsdatum()).thenReturn(new Date(1));
        when(kuchen1.getFachnummer()).thenReturn(1);
        when(kuchen1.toString()).thenReturn("kuchen1 mit Fachnummer 1");

        automat.insertKuchen(kuchen1);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        ReportThread reportThread = new ReportThread(automat, 1);

        reportThread.report();
        String expected =
                "---------Aktuelle Herstelleranzahl: 2--------" + System.lineSeparator() +
                "Hersteller1, Kuchenanzahl: 1" + System.lineSeparator() +
                "Hersteller2, Kuchenanzahl: 0" + System.lineSeparator() +
                "---------Aktuelle Kuchenanzahl: 1--------" + System.lineSeparator() +
                "kuchen1 mit Fachnummer 1" + System.lineSeparator() +
                "-----------------------------------------" + System.lineSeparator();
        assertEquals(expected, bos.toString());
    }
}