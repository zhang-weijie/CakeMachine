package cli.event.listener.kuchenlistener;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import gl.AutomatInstanceManager;
import cli.event.CMDEvent;
import cli.event.CMDEventListener;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class DeleteKuchenEventListener extends CMDEventListener {


    public DeleteKuchenEventListener(AutomatInstanceManager automatInstanceManager) {
        super(automatInstanceManager);
    }

    @Override
    public void onCmdEvent(CMDEvent cmdEvent) {
        String[] args = cmdEvent.getArgs();
        try {
            boolean deleted = getAutomat().deleteKuchen(Integer.parseInt(args[0]));
            if (deleted) {
                System.out.println("Kuchen wurde entfernt!");
            } else {
                System.out.println("Falsche Fachnummer oder Leeres Fach!");
            }
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Ungueltige Fachnummer!");
        }
    }
}
