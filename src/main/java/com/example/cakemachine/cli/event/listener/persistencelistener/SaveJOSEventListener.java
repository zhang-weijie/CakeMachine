package com.example.cakemachine.cli.event.listener.persistencelistener;

import com.example.cakemachine.cli.client.Client;
import com.example.cakemachine.cli.event.CMDEvent;
import com.example.cakemachine.gl.AutomatInstanceManager;
import com.example.cakemachine.io.SaveWithJOS;


public class SaveJOSEventListener extends SaveStatusEventListener {
    private AutomatInstanceManager automatInstanceManager;

    public SaveJOSEventListener(AutomatInstanceManager automatInstanceManager) {
        super(automatInstanceManager);
        this.automatInstanceManager = automatInstanceManager;
    }

    @Override
    public void onCmdEvent(CMDEvent cmdEvent) {
        setSaveStrategy(new SaveWithJOS());
        boolean saved = getAutomat().save(Client.STATUS_JOS_FILE);
        if (saved){
            System.out.println("Zustand wurde mit JOS erfolgreich gespeichert!");
        } else {
            System.out.println("Speicherung schlug fehl!");
        }
    }
}
