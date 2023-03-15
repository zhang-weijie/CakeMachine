package com.example.cakemachine.cli.event.listener.kuchenlistener;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.cli.event.CMDEvent;
import com.example.cakemachine.cli.event.CMDEventListener;
import com.example.cakemachine.gl.AutomatInstanceManager;
import com.example.cakemachine.gl.KuchenImpl;
import com.example.cakemachine.gl.element.Element;
import com.example.cakemachine.gl.element.belag.*;
import com.example.cakemachine.gl.element.boden.Hefeteig;
import com.example.cakemachine.gl.element.boden.Muerbeteig;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class InsertKuchenEventListener extends CMDEventListener {


    public InsertKuchenEventListener(AutomatInstanceManager automatInstanceManager) {
        super(automatInstanceManager);
    }

    @Override
    public void onCmdEvent(CMDEvent cmdEvent) {
        String[] args = cmdEvent.getArgs();
        boolean inserted = parseArgsToInsertKuchen(args);
        if (inserted) {
            System.out.println("Kuchen wurde eingefuegt!");
        } else {
            System.out.println("Falsche Parameter oder kein Fach verfuegbar!");
        }
    }

    private static final String MUERBETEIG = "Muerbeteig";
    private static final String HEFETEIG = "Hefeteig";
    private static final String APFELBELAG = "Apfel";
    private static final String BIRNEBELAG = "Birne";
    private static final String KIRSCHEBELAG = "Kirsche";
    private static final String SAHNEBELAG = "Sahne";
    private static final String PUDDINGBELAG = "Pudding";

    boolean parseArgsToInsertKuchen(String[] args) {
        Element kuchenElement;
        switch (args[0]) {
            case MUERBETEIG:
                kuchenElement = new Muerbeteig();
                break;
            case HEFETEIG:
                kuchenElement = new Hefeteig();
                break;
            default:
                return false;
        }

        for (int i = 2; i < args.length; i++) {
            switch (args[i]) {
                case APFELBELAG:
                    kuchenElement = new Apfelbelag(kuchenElement);
                    break;
                case BIRNEBELAG:
                    kuchenElement = new Birnebelag(kuchenElement);
                    break;
                case KIRSCHEBELAG:
                    kuchenElement = new Kirschebelag(kuchenElement);
                    break;
                case SAHNEBELAG:
                    kuchenElement = new Sahnebelag(kuchenElement);
                    break;
                case PUDDINGBELAG:
                    kuchenElement = new Puddingbelag(kuchenElement);
                    break;
                default:
                    return false;
            }
        }
        return getAutomat().insertKuchen(new KuchenImpl(kuchenElement, getAutomat().getHersteller(args[1])));
    }
}