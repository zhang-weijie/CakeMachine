package com.example.cakemachine.cli.oberserver;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.gl.Automat;
import com.example.cakemachine.vertrag.Beobachter;


/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class CapacityBeobachter implements Beobachter {
    private Automat automat;
    private double oldFullness;

    public CapacityBeobachter(Automat automat) {
        this.automat = automat;
        oldFullness = 1d - ((double) automat.getCurCapacity() / automat.getMaxCapacity());
    }

    @Override
    public void aktualisiere() {
        double newFullness = 1d -  ((double) automat.getCurCapacity() / automat.getMaxCapacity());
        if (oldFullness < 0.9 && newFullness >= 0.9){
            System.out.println("Info: 90% der Kapazitaet wurde ueberschritten!");
        } else if (oldFullness >= 0.9 && newFullness < 0.9){
            System.out.println("Info: Kapazitaetauslastung wurde auf unter 90 % reduziert");
        }
        oldFullness = newFullness;
    }
}