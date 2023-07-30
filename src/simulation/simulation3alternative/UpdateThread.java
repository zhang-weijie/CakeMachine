package simulation.simulation3alternative;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import gl.Automat;
import gl.KuchenImpl;

import java.util.List;
import java.util.Random;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class UpdateThread extends Thread {
    private Automat automat;
    private Random random;
    private int num;

    public UpdateThread(Automat automat, Random random, int num) {
        this.automat = automat;
        this.random = random;
        this.num = num;
    }

    void inspect() {
        List<KuchenImpl> kuchenList = automat.readAllKuchen();
        if (!kuchenList.isEmpty()) {
            int i = random.nextInt(kuchenList.size());
            System.out.println("UpdateThread " + num + ":\tInspizieren:\t" + kuchenList.get(i));
            automat.updateKuchen(kuchenList.get(i).getFachnummer());
        }
    }

    @Override
    public void run() {
        while (true) {
            inspect();
        }
    }
}
