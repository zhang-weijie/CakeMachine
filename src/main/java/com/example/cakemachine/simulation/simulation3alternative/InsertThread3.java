package com.example.cakemachine.simulation.simulation3alternative;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.gl.Automat;
import com.example.cakemachine.gl.KuchenImpl;
import com.example.cakemachine.simulation.SimulationUtil;

import java.util.Random;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class InsertThread3 extends Thread {
    private Automat automat;
    private Random random;
    private int num;

    public InsertThread3(Automat automat, Random random, int num) {
        this.automat = automat;
        this.random = random;
        this.num = num;
    }

    void insert() {
        KuchenImpl kuchen = SimulationUtil.generateKuchen(automat, random);
        System.out.println("InsertThread " + num + ":\tEinfuegen:\t" + kuchen);
        automat.insertKuchen(kuchen);
    }

    @Override
    public void run() {
        try {
            synchronized (automat) {
                while (true) {
                    if (automat.getCurCapacity() != 0) {
                        insert();
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
