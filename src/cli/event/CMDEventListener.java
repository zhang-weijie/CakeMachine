package cli.event;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import gl.Automat;
import gl.AutomatInstanceManager;

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
