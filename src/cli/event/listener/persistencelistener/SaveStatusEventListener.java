package cli.event.listener.persistencelistener;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import gl.AutomatInstanceManager;
import gl.persistencestrategy.SaveStrategy;
import cli.event.CMDEventListener;

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
