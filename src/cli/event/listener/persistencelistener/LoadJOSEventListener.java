package cli.event.listener.persistencelistener;

import gl.AutomatInstanceManager;
import io.LoadWithJOS;
import cli.client.Client;
import cli.event.CMDEvent;

public class LoadJOSEventListener extends LoadStatusEventListener {


    public LoadJOSEventListener(AutomatInstanceManager automatInstanceManager) {
        super(automatInstanceManager);
    }

    @Override
    public void onCmdEvent(CMDEvent cmdEvent) {
        setLoadStrategy(new LoadWithJOS());
        boolean loaded = loadAutomat(Client.STATUS_JOS_FILE);
        if (loaded) {
            System.out.println("Zustand wurde mit JOS erfolgreich geladen!");
        } else {
            System.out.println("Keine Datei liegt vor oder kann geladen werden!");
        }
    }
}