package simulation.simulation3alternative;

import gl.Automat;
import gl.KuchenImpl;
import gl.element.belag.Apfelbelag;
import gl.element.boden.Hefeteig;
import gl.element.boden.Muerbeteig;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateThreadTest {

    @Test
    void inspect_normal() {
        Automat automat = new Automat(3);
        Automat spyAutomat = spy(automat);
        spyAutomat.addHersteller("Hersteller1");
        spyAutomat.addHersteller("Hersteller2");

        KuchenImpl kuchen1 = new KuchenImpl(new Muerbeteig(), automat.getHersteller("Hersteller1"));
        KuchenImpl kuchen2 = new KuchenImpl(new Apfelbelag(new Muerbeteig()), automat.getHersteller("Hersteller1"));
        KuchenImpl kuchen3 = new KuchenImpl(new Hefeteig(), automat.getHersteller("Hersteller2"));

        spyAutomat.insertKuchen(kuchen1);
        spyAutomat.insertKuchen(kuchen2);
        spyAutomat.insertKuchen(kuchen3);

        Random random = mock(Random.class);
        when(random.nextInt(spyAutomat.readAllKuchen().size())).thenReturn(2);

        UpdateThread updateThread = new UpdateThread(spyAutomat, random, 1);

        updateThread.inspect();
        assertNotNull(spyAutomat.readAllKuchen().get(2).getInspektionsdatum());
    }
}