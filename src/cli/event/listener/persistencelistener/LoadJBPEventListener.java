package cli.event.listener.persistencelistener;

import gl.*;
import io.LoadWithJBP;
import cli.client.Client;
import cli.event.CMDEvent;

public class LoadJBPEventListener extends LoadStatusEventListener {
    public LoadJBPEventListener(AutomatInstanceManager automatInstanceManager) {
        super(automatInstanceManager);
    }

    @Override
    public void onCmdEvent(CMDEvent cmdEvent) {
        setLoadStrategy(new LoadWithJBP());
        boolean loaded = loadAutomat(Client.STATUS_JBP_FILE);
        if (loaded) {
            System.out.println("Zustand wurde mit JBP erfolgreich geladen!");
        } else {
            System.out.println("Keine Datei liegt vor oder kann geladen werden!");
        }
    }
}
