package simulation;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import gl.Automat;
import gl.KuchenImpl;
import gl.element.belag.Apfelbelag;
import gl.element.boden.Hefeteig;
import gl.element.boden.Muerbeteig;

import java.util.Random;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class SimulationUtil {
    private SimulationUtil() {

    }

    public static Automat init(int capacity){
        Automat automat = new Automat(capacity);
        automat.addHersteller("Hersteller1");
        automat.addHersteller("Hersteller2");
        return automat;
    }

    public static KuchenImpl generateKuchen(Automat automat, Random r) {
        switch (r.nextInt(3)) {
            case 0: return new KuchenImpl(new Muerbeteig(), automat.getHersteller("Hersteller1"));
            case 1: return new KuchenImpl(new Apfelbelag(new Muerbeteig()), automat.getHersteller("Hersteller1"));
            default: return new KuchenImpl(new Hefeteig(), automat.getHersteller("Hersteller2"));
        }
    }
}