package com.example.cakemachine.cli.event.listener.persistencelistener;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.cli.event.CMDEventListener;
import com.example.cakemachine.gl.AutomatInstanceManager;
import com.example.cakemachine.gl.persistencestrategy.SaveStrategy;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public abstract class SaveStatusEventListener extends CMDEventListener {
    private AutomatInstanceManager automatInstanceManager;

    public SaveStatusEventListener(AutomatInstanceManager automatInstanceManager) {
        super(automatInstanceManager);
        this.automatInstanceManager = automatInstanceManager;
    }

    void setSaveStrategy(SaveStrategy saveStrategy){
        automatInstanceManager.setSaveStrategy(saveStrategy);
    }
}
