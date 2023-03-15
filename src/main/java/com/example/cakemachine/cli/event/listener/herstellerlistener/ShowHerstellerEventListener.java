package com.example.cakemachine.cli.event.listener.herstellerlistener;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.cli.event.CMDEvent;
import com.example.cakemachine.cli.event.CMDEventListener;
import com.example.cakemachine.gl.AutomatInstanceManager;
import com.example.cakemachine.gl.HerstellerImpl;
import com.example.cakemachine.gl.KuchenImpl;

import java.util.List;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class ShowHerstellerEventListener extends CMDEventListener {


    public ShowHerstellerEventListener(AutomatInstanceManager automatInstanceManager) {
        super(automatInstanceManager);
    }

    @Override
    public void onCmdEvent(CMDEvent cmdEvent) {
        List<HerstellerImpl> herstellerList = getAutomat().readAllHersteller();
        List<KuchenImpl> kuchenList = getAutomat().readAllKuchen();
        System.out.println("-----------------------------");
        for (HerstellerImpl hersteller : herstellerList) {
            int kuchenNum = 0;
            for (KuchenImpl kuchen : kuchenList){
                if (kuchen.getHersteller() == hersteller){
                    kuchenNum++;
                }
            }
            System.out.println(hersteller.getName() + "\tKuchenanzahl: " + kuchenNum);
        }
        System.out.println("-----------------------------");
    }
}