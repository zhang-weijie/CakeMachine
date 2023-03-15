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
public class DeleteHerstellerEventListener extends CMDEventListener {

    public DeleteHerstellerEventListener(AutomatInstanceManager automatInstanceManager) {
        super(automatInstanceManager);
    }

    @Override
    public void onCmdEvent(CMDEvent cmdEvent) {
        String[] args = cmdEvent.getArgs();
        boolean deleted = getAutomat().removeHersteller(args[0]);
        if (deleted) {
            System.out.println("Hersteller und zugehoerige Kuchen wurden entfernt!");
        } else {
            System.out.println("Unbekannter Hersteller!");
        }
    }
}
