package com.example.cakemachine.simulation.simulation1;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.gl.Automat;
import com.example.cakemachine.gl.KuchenImpl;

import java.util.List;
import java.util.Random;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class DeleteThread1 extends Thread {
    private Automat automat;
    private Random random;

    public DeleteThread1(Automat automat, Random random) {
        this.automat = automat;
        this.random = random;
    }

    void delete() {
        List<KuchenImpl> kuchenList = automat.readAllKuchen();
        if (!kuchenList.isEmpty()) {
            int i = random.nextInt(kuchenList.size());
            System.out.println("Entfernen:\t" + kuchenList.get(i));
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
