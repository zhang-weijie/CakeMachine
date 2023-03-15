package com.example.cakemachine.simulation.simulation2alternative;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.cli.oberserver.KuchenBeobachter;
import com.example.cakemachine.gl.Automat;
import com.example.cakemachine.simulation.SimulationUtil;

import java.util.Random;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class Simulation2 {
    static void run(int capacity, int threadNum) {
        Automat automat = SimulationUtil.init(capacity);
        KuchenBeobachter kuchenBeobachter = new KuchenBeobachter(automat);
        automat.meldeAn(kuchenBeobachter);
        Random insertRandom = new Random();
        Random deleteRandom = new Random();

        InsertThread2[] insertThread2s = new InsertThread2[threadNum];
        DeleteThread2[] deleteThread2s = new DeleteThread2[threadNum];

        for (int i = 0; i < threadNum; i++) {
            insertThread2s[i] = new InsertThread2(automat, insertRandom, i + 1);
            deleteThread2s[i] = new DeleteThread2(automat, deleteRandom, i + 1);
        }

        for (int i = 0; i < threadNum; i++) {
            insertThread2s[i].start();
            deleteThread2s[i].start();
        }

        try {
            for (int i = 0; i < threadNum; i++) {
                insertThread2s[i].join();
                deleteThread2s[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            if (args.length == 2){
                int capacity = Integer.parseInt(args[0]);
                int threadNum = Integer.parseInt(args[1]);
                run(capacity, threadNum);
            } else {
                System.out.println("Falsche Anzahl von Argumenten!");
            }
        } catch (Exception exception){
            System.out.println("Falsche Argumente!");
        }
    }
}
