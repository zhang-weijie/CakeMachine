package cli.event.listener.kuchenlistener;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import gl.AutomatInstanceManager;
import gl.KuchenImpl;
import cli.event.CMDEvent;
import cli.event.CMDEventListener;

import java.util.List;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class ReadHefeKuchenEventListener extends CMDEventListener {

    public ReadHefeKuchenEventListener(AutomatInstanceManager automatInstanceManager) {
        super(automatInstanceManager);
    }

    @Override
    public void onCmdEvent(CMDEvent cmdEvent) {
        List<KuchenImpl> kuchenList = getAutomat().readHefeKuchen();
        System.out.println("-----------------------------");
        for (KuchenImpl kuchen : kuchenList) {
            System.out.println(kuchen);
        }
        System.out.println("-----------------------------");
    }
}
