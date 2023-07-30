package cli.event.listener.persistencelistener;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import gl.AutomatInstanceManager;
import gl.persistencestrategy.LoadStrategy;
import cli.event.CMDEventListener;

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
