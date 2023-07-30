package simulation.simulation1;

import gl.Automat;
import gl.KuchenImpl;
import gl.element.belag.Apfelbelag;
import gl.element.boden.Hefeteig;
import gl.element.boden.Muerbeteig;
import org.junit.jupiter.api.Test;
import simulation.SimulationUtil;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


class InsertThread1Test {

    @Test
    void insert_checkRandomBehavior() {
        Automat automat = SimulationUtil.init(3);
        Random random = mock(Random.class);
        when(random.nextInt(3)).thenReturn(2).thenReturn(0).thenReturn(1);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        InsertThread1 insertThread1 = new InsertThread1(automat, random);
        insertThread1.insert();
        insertThread1.insert();
        insertThread1.insert();

        verify(random,times(3)).nextInt(3);
    }

    @Test
    void insert_checkOutput(){
        Automat automat = SimulationUtil.init(3);

        KuchenImpl kuchen1 = new KuchenImpl(new Muerbeteig(), automat.getHersteller("Hersteller1"));
        KuchenImpl kuchen2 = new KuchenImpl(new Apfelbelag(new Muerbeteig()), automat.getHersteller("Hersteller1"));
        KuchenImpl kuchen3 = new KuchenImpl(new Hefeteig(), automat.getHersteller("Hersteller2"));

        Random random = mock(Random.class);
        when(random.nextInt(3)).thenReturn(2).thenReturn(0).thenReturn(1);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        InsertThread1 insertThread1 = new InsertThread1(automat, random);
        insertThread1.insert();
        insertThread1.insert();
        insertThread1.insert();

        verify(random,times(3)).nextInt(3);
        String expected =
                "Einfuegen:\t" + kuchen3.toString() + System.lineSeparator() +
                        "Einfuegen:\t" + kuchen1.toString() + System.lineSeparator() +
                        "Einfuegen:\t" + kuchen2.toString() + System.lineSeparator();
        assertEquals(expected, bos.toString());
    }
}