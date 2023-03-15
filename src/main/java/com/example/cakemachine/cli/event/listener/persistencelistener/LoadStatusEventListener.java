package com.example.cakemachine.cli.event.listener.persistencelistener;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.cli.event.CMDEventListener;
import com.example.cakemachine.gl.AutomatInstanceManager;
import com.example.cakemachine.gl.persistencestrategy.LoadStrategy;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public abstract class LoadStatusEventListener extends CMDEventListener {
    private AutomatInstanceManager automatInstanceManager;

    public LoadStatusEventListener(AutomatInstanceManager automatInstanceManager) {
        super(automatInstanceManager);
        this.automatInstanceManager = automatInstanceManager;
    }

    boolean loadAutomat(String filename){
        return automatInstanceManager.loadAutomat(filename);
    }

    void setLoadStrategy(LoadStrategy loadStrategy){
        automatInstanceManager.setLoadStrategy(loadStrategy);
    }
}
