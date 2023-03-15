package com.example.cakemachine.cli.event.listener.persistencelistener;

import com.example.cakemachine.cli.client.Client;
import com.example.cakemachine.cli.event.CMDEvent;
import com.example.cakemachine.gl.AutomatInstanceManager;
import com.example.cakemachine.io.SaveWithJBP;

public class SaveJBPEventListener extends SaveStatusEventListener {
    public SaveJBPEventListener(AutomatInstanceManager automatInstanceManager) {
        super(automatInstanceManager);
    }

    @Override
    public void onCmdEvent(CMDEvent cmdEvent) {
        setSaveStrategy(new SaveWithJBP());
        boolean saved = getAutomat().save(Client.STATUS_JBP_FILE);
        if (saved){
            System.out.println("Zustand wurde mit JBP erfolgreich gespeichert!");
        } else {
            System.out.println("Speicherung schlug fehl!");
        }
    }
}