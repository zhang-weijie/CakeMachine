package com.example.cakemachine.cli.event.listener.persistencelistener;

import com.example.cakemachine.cli.client.Client;
import com.example.cakemachine.cli.event.CMDEvent;
import com.example.cakemachine.gl.AutomatInstanceManager;
import com.example.cakemachine.io.LoadWithJBP;

public class LoadJBPEventListener extends LoadStatusEventListener {
    public LoadJBPEventListener(AutomatInstanceManager automatInstanceManager) {
        super(automatInstanceManager);
    }

    @Override
    public void onCmdEvent(CMDEvent cmdEvent) {
        setLoadStrategy(new LoadWithJBP());
        boolean loaded = loadAutomat(Client.STATUS_JBP_FILE);
        if (loaded) {
            System.out.println("Zustand wurde mit JBP erfolgreich geladen!");
        } else {
            System.out.println("Keine Datei liegt vor oder kann geladen werden!");
        }
    }
}
