package com.example.cakemachine.simulation.simulation1;
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
public class Simulation1 {
    public static void run(int capacity) {
        Automat automat = SimulationUtil.init(capacity);
        KuchenBeobachter kuchenBeobachter = new KuchenBeobachter(automat);
        automat.meldeAn(kuchenBeobachter);
        Random insertRandom = new Random();
        Random deleteRandom = new Random();
        InsertThread1 insertThread = new InsertThread1(automat, insertRandom);
        DeleteThread1 deleteThread = new DeleteThread1(automat, deleteRandom);
        insertThread.start();
        deleteThread.start();
        try {
            insertThread.join();
            deleteThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            if (args.length == 1){
                int capacity = Integer.parseInt(args[0]);
                run(capacity);
            } else {
                System.out.println("Falsche Anzahl von Argumenten!");
            }
        } catch (Exception exception){
            System.out.println("Falsche Argumente!");
        }
    }
}