package simulation.simulation2alternative;
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
public class DeleteThread2 extends Thread {
    private Automat automat;
    private Random random;
    private int num;

    public DeleteThread2(Automat automat, Random random, int num) {
        this.automat = automat;
        this.random = random;
        this.num = num;
    }

    void delete() {
        List<KuchenImpl> kuchenList = automat.readAllKuchen();
        if (!kuchenList.isEmpty()) {
            int i = random.nextInt(kuchenList.size());
            System.out.println("DeleteThread " + num + ":\tEntfernen:\t" + kuchenList.get(i));
            automat.deleteKuchen(kuchenList.get(i).getFachnummer());
        }
    }

    @Override
    public void run() {
        while (true) {
            delete();
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
