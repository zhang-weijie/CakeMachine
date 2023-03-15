package com.example.cakemachine.simulation.simulation3alternative;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.gl.Automat;
import com.example.cakemachine.gl.KuchenImpl;

import java.util.List;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class DeleteThread3 extends Thread {
    private Automat automat;
    private int num;

    public DeleteThread3(Automat automat, int num) {
        this.automat = automat;
        this.num = num;
    }

    boolean deleteOldestKuchen() {
        List<KuchenImpl> kuchenList = automat.readAllKuchen();
        KuchenImpl oldestKuchen = null;
        for (KuchenImpl kuchen : kuchenList) {
            if (kuchen.getInspektionsdatum() != null) {
                if (oldestKuchen == null || kuchen.getInspektionsdatum().before(oldestKuchen.getInspektionsdatum())) {
                    oldestKuchen = kuchen;
                }
            }
        }
        if (oldestKuchen != null) {
            System.out.println("DeleteThread " + num + ":\tEntfernen:\t" + oldestKuchen);
            automat.deleteKuchen(oldestKuchen.getFachnummer());
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        try {
            synchronized (automat) {
                while (true) {
                    if (automat.getCurCapacity() != automat.getMaxCapacity()) {
                        boolean oldestKuchenDeleted = deleteOldestKuchen();
                        if (!oldestKuchenDeleted) {
                            automat.notifyAll();
                            automat.wait();
                        }
                    } else {
                        automat.notifyAll();
                        automat.wait();
                    }
//                    Thread.sleep(3000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}