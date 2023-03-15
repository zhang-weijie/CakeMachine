package com.example.cakemachine.cli.oberserver;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.gl.Automat;
import com.example.cakemachine.gl.KuchenImpl;
import com.example.cakemachine.vertrag.Beobachter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class KuchenBeobachter implements Beobachter {
    private Automat automat;
    private HashMap<Integer, KuchenImpl> oldFachnummerKuchenMap;
    private HashMap<Integer, Date> oldFachnummerInspektionsdatumMap;

    public KuchenBeobachter(Automat automat) {
        this.automat = automat;
        oldFachnummerKuchenMap = new HashMap<>();
        for (int i = 0; i < automat.getMaxCapacity(); i++) {
            oldFachnummerKuchenMap.put(i + 1, null);
        }
        for (KuchenImpl kuchen : automat.readAllKuchen()){
            oldFachnummerKuchenMap.replace(kuchen.getFachnummer(),kuchen);
        }
    }

    @Override
    public void aktualisiere() {
        HashMap<Integer, KuchenImpl> newFachnummerKuchenMap = new HashMap<>();
        for (int i = 0; i < automat.getMaxCapacity(); i++) {
            newFachnummerKuchenMap.put(i + 1, null);
        }
        for (KuchenImpl kuchen : automat.readAllKuchen()){
            newFachnummerKuchenMap.replace(kuchen.getFachnummer(), kuchen);
        }

        for (Map.Entry<Integer, KuchenImpl> entry : newFachnummerKuchenMap.entrySet()){
            if (entry.getValue() == null && oldFachnummerKuchenMap.get(entry.getKey()) != null){
                System.out.println("Entfernt:\t" + oldFachnummerKuchenMap.get(entry.getKey()));
            } else if (entry.getValue() != null && oldFachnummerKuchenMap.get(entry.getKey()) == null){
                System.out.println("Eingefuegt:\t" + newFachnummerKuchenMap.get(entry.getKey()));
            }
        }
        oldFachnummerKuchenMap = newFachnummerKuchenMap;
    }
}