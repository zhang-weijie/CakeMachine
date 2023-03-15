package com.example.cakemachine.simulation.simulation3alternative;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.gl.Automat;
import com.example.cakemachine.simulation.SimulationUtil;

import java.util.Random;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class Simulation3 {
    static void run(int capacity, int threadNum, long period) {
        Automat automat = SimulationUtil.init(capacity);
        Random insertRandom = new Random();
        Random inspektionRandom = new Random();

        ReportThread reportThread = new ReportThread(automat, period);
        reportThread.start();

        InsertThread3[] insertThread3s = new InsertThread3[threadNum];
        DeleteThread3[] deleteThread3s = new DeleteThread3[threadNum];
        UpdateThread[] updateThreads = new UpdateThread[threadNum];

        for (int i = 0; i < threadNum; i++) {
            insertThread3s[i] = new InsertThread3(automat, insertRandom, i + 1);
            insertThread3s[i].start();
            deleteThread3s[i] = new DeleteThread3(automat, i + 1);
            deleteThread3s[i].start();
            updateThreads[i] = new UpdateThread(automat, inspektionRandom, i + 1);
            updateThreads[i].start();
        }


        try {
            reportThread.join();
            for (int i = 0; i < threadNum; i++) {
                insertThread3s[i].join();
                deleteThread3s[i].join();
                updateThreads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            if (args.length == 3){
                int capacity = Integer.parseInt(args[0]);
                int threadNum = Integer.parseInt(args[1]);
                long interval = Long.parseLong(args[2]);
                run(capacity, threadNum, interval);
            } else {
                System.out.println("Falsche Anzahl von Argumenten!");
            }
        } catch (Exception exception){
            System.out.println("Falsche Argumente!");
        }
    }
}