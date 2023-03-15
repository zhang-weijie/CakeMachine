package com.example.cakemachine.cli.event.source;

import com.example.cakemachine.cli.console.ConsoleImpl;
import com.example.cakemachine.cli.event.CMDEventHandler;

public class PersistenceEventSource extends ConsoleImpl {
    private CMDEventHandler saveJOSEventHandler;
    private CMDEventHandler saveJBPEventHandler;
    private CMDEventHandler loadJOSEventHandler;

    private CMDEventHandler loadJBPEventHandler;

    public CMDEventHandler getSaveJOSEventHandler() {
        return saveJOSEventHandler;
    }

    public void setSaveJOSEventHandler(CMDEventHandler saveJOSEventHandler) {
        this.saveJOSEventHandler = saveJOSEventHandler;
    }

    public CMDEventHandler getSaveJBPEventHandler() {
        return saveJBPEventHandler;
    }

    public void setSaveJBPEventHandler(CMDEventHandler saveJBPEventHandler) {
        this.saveJBPEventHandler = saveJBPEventHandler;
    }

    public CMDEventHandler getLoadJOSEventHandler() {
        return loadJOSEventHandler;
    }

    public void setLoadJOSEventHandler(CMDEventHandler loadJOSEventHandler) {
        this.loadJOSEventHandler = loadJOSEventHandler;
    }

    public CMDEventHandler getLoadJBPEventHandler() {
        return loadJBPEventHandler;
    }

    public void setLoadJBPEventHandler(CMDEventHandler loadJBPEventHandler) {
        this.loadJBPEventHandler = loadJBPEventHandler;
    }
}
