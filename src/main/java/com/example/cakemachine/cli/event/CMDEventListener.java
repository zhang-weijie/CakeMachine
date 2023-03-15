package com.example.cakemachine.cli.event;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.gl.Automat;
import com.example.cakemachine.gl.AutomatInstanceManager;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public abstract class CMDEventListener {
    private AutomatInstanceManager automatInstanceManager;

    public CMDEventListener(AutomatInstanceManager automatInstanceManager) {
        this.automatInstanceManager = automatInstanceManager;
    }

    public Automat getAutomat() {
        return automatInstanceManager.getAutomat();
    }

    public abstract void onCmdEvent(CMDEvent cmdEvent);
}
