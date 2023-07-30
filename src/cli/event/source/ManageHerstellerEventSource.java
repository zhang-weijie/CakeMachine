package cli.event.source;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import cli.console.ConsoleImpl;
import cli.event.CMDEventHandler;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class ManageHerstellerEventSource extends ConsoleImpl {
    private CMDEventHandler deleteHerstellerEventHandler;
    private CMDEventHandler insertHerstellerEventHandler;
    private CMDEventHandler showHerstellerEventHandler;

    public CMDEventHandler getDeleteHerstellerEventHandler() {
        return deleteHerstellerEventHandler;
    }

    public void setDeleteHerstellerEventHandler(CMDEventHandler deleteHerstellerEventHandler) {
        this.deleteHerstellerEventHandler = deleteHerstellerEventHandler;
    }

    public CMDEventHandler getInsertHerstellerEventHandler() {
        return insertHerstellerEventHandler;
    }

    public void setInsertHerstellerEventHandler(CMDEventHandler insertHerstellerEventHandler) {
        this.insertHerstellerEventHandler = insertHerstellerEventHandler;
    }

    public CMDEventHandler getShowHerstellerEventHandler() {
        return showHerstellerEventHandler;
    }

    public void setShowHerstellerEventHandler(CMDEventHandler showHerstellerEventHandler) {
        this.showHerstellerEventHandler = showHerstellerEventHandler;
    }
}