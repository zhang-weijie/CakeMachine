package com.example.cakemachine.cli.event.listener.kuchenlistener;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.cli.event.CMDEvent;
import com.example.cakemachine.cli.event.CMDEventListener;
import com.example.cakemachine.gl.AutomatInstanceManager;
import com.example.cakemachine.gl.KuchenImpl;

import java.util.List;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class ReadMuerbeKuchenEventListener extends CMDEventListener {


    public ReadMuerbeKuchenEventListener(AutomatInstanceManager automatInstanceManager) {
        super(automatInstanceManager);
    }

    @Override
    public void onCmdEvent(CMDEvent cmdEvent) {
        List<KuchenImpl> kuchenList = getAutomat().readMuerbeKuchen();
        System.out.println("-----------------------------");
        for (KuchenImpl kuchen : kuchenList) {
            System.out.println(kuchen);
        }
        System.out.println("-----------------------------");
    }
}
