package cli.event.listener.persistencelistener;

import gl.AutomatInstanceManager;
import io.SaveWithJOS;
import cli.client.Client;
import cli.event.CMDEvent;


public class SaveJOSEventListener extends SaveStatusEventListener {
    private AutomatInstanceManager automatInstanceManager;

    public SaveJOSEventListener(AutomatInstanceManager automatInstanceManager) {
        super(automatInstanceManager);
        this.automatInstanceManager = automatInstanceManager;
    }

    @Override
    public void onCmdEvent(CMDEvent cmdEvent) {
        setSaveStrategy(new SaveWithJOS());
        boolean saved = getAutomat().save(Client.STATUS_JOS_FILE);
        if (saved){
            System.out.println("Zustand wurde mit JOS erfolgreich gespeichert!");
        } else {
            System.out.println("Speicherung schlug fehl!");
        }
    }
}
