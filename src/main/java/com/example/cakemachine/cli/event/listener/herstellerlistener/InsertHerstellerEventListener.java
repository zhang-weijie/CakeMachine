package com.example.cakemachine.cli.event.listener.herstellerlistener;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.cli.event.CMDEvent;
import com.example.cakemachine.cli.event.CMDEventListener;
import com.example.cakemachine.gl.AutomatInstanceManager;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class InsertHerstellerEventListener extends CMDEventListener {


    public InsertHerstellerEventListener(AutomatInstanceManager automatInstanceManager) {
        super(automatInstanceManager);
    }

    @Override
    public void onCmdEvent(CMDEvent cmdEvent) {
        String[] args = cmdEvent.getArgs();
        boolean added = getAutomat().addHersteller(args[0]);
        if (added) {
            System.out.println("Hersteller wurde eingefuegt!");
        } else {
            System.out.println("Hersteller mit diesem Namen wurde bereits eingefuegt!");
        }
    }
}
