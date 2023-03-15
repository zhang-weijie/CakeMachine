package com.example.cakemachine.cli.event;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import java.util.EventObject;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class CMDEvent extends EventObject {
    private String[] args;

    public CMDEvent(Object source, String[] args) {
        super(source);
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }
}
