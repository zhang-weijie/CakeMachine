package com.example.cakemachine.cli.event;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import java.util.LinkedList;
import java.util.List;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class CMDEventHandler {
    private List<CMDEventListener> listenerList = new LinkedList<>();
    public boolean addListener(CMDEventListener listener){
        return listenerList.add(listener);
    }
    public boolean removeListener(CMDEventListener listener){
        return listenerList.remove(listener);
    }
    public List<CMDEventListener> getListenerList() {
        return listenerList;
    }
    public void handle(CMDEvent cmdEvent){
        for (CMDEventListener cmdEventListener : listenerList) {
            cmdEventListener.onCmdEvent(cmdEvent);
        }
    }
}