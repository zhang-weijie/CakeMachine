package com.example.cakemachine.cli.event.listener.persistencelistener;

import com.example.cakemachine.cli.client.Client;
import com.example.cakemachine.cli.event.CMDEvent;
import com.example.cakemachine.gl.AutomatInstanceManager;
import com.example.cakemachine.io.LoadWithJOS;

public class LoadJOSEventListener extends LoadStatusEventListener {


    public LoadJOSEventListener(AutomatInstanceManager automatInstanceManager) {
        super(automatInstanceManager);
    }

    @Override
    public void onCmdEvent(CMDEvent cmdEvent) {
        setLoadStrategy(new LoadWithJOS());
        boolean loaded = loadAutomat(Client.STATUS_JOS_FILE);
        if (loaded) {
            System.out.println("Zustand wurde mit JOS erfolgreich geladen!");
        } else {
            System.out.println("Keine Datei liegt vor oder kann geladen werden!");
        }
    }
}