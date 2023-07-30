package net.server;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import gl.Automat;
import gl.AutomatInstanceManager;
import cli.event.CMDEvent;
import cli.event.CMDEventHandler;
import cli.event.listener.herstellerlistener.DeleteHerstellerEventListener;
import cli.event.listener.herstellerlistener.InsertHerstellerEventListener;
import cli.event.listener.herstellerlistener.ShowHerstellerEventListener;
import cli.event.listener.kuchenlistener.*;
import cli.event.listener.persistencelistener.LoadJBPEventListener;
import cli.event.listener.persistencelistener.LoadJOSEventListener;
import cli.event.listener.persistencelistener.SaveJBPEventListener;
import cli.event.listener.persistencelistener.SaveJOSEventListener;
import cli.event.source.ManageHerstellerEventSource;
import cli.event.source.ManageKuchenEventSource;
import cli.event.source.PersistenceEventSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class Backend {
    private static final String INSERT_HERSTELLER_CMD = "INSERT_HERSTELLER_CMD";
    private static final String INSERT_KUCHEN_CMD = "INSERT_KUCHEN_CMD";
    private static final String SHOW_HERSTELLER_CMD = "SHOW_HERSTELLER_CMD";
    private static final String SHOW_KUCHEN_CMD = "SHOW_KUCHEN_CMD";
    private static final String SHOW_MUERBEKUCHEN_CMD = "SHOW_MUERBEKUCHEN_CMD";
    private static final String SHOW_HEFEKUCHEN_CMD = "SHOW_HEFEKUCHEN_CMD";
    private static final String SHOW_INCLUDED_ALLERGENE_CMD = "SHOW_INCLUDED_ALLERGENE_CMD";
    private static final String SHOW_EXCLUDED_ALLERGENE_CMD = "SHOW_EXCLUDED_ALLERGENE_CMD";
    private static final String DELETE_HERSTELLER_CMD = "DELETE_HERSTELLER_CMD";
    private static final String DELETE_KUCHEN_CMD = "DELETE_KUCHEN_CMD";
    private static final String UPDATE_KUCHEN_CMD = "UPDATE_KUCHEN_CMD";
    private static final String SAVE_JOS_CMD = "SAVE_JOS_CMD";
    private static final String SAVE_JBP_CMD = "SAVE_JBP_CMD";
    private static final String LOAD_JOS_CMD = "LOAD_JOS_CMD";
    private static final String LOAD_JBP_CMD = "LOAD_JBP_CMD";


    public static String processRequest(String cmd) {
        String[] cmdArr = cmd.trim().split("\\s+");
        String cmdTyp = cmdArr[0];
        String[] cmdContent = Arrays.copyOfRange(cmdArr, 1, cmdArr.length);
        switch (cmdTyp) {
            case INSERT_HERSTELLER_CMD:
                manageHerstellerEventSource.getInsertHerstellerEventHandler().handle(new CMDEvent(manageHerstellerEventSource, cmdContent));
                break;
            case INSERT_KUCHEN_CMD:
                manageKuchenEventSource.getInsertKuchenEventHandler().handle(new CMDEvent(manageKuchenEventSource, cmdContent));
                break;
            case SHOW_HERSTELLER_CMD:
                manageHerstellerEventSource.getShowHerstellerEventHandler().handle(new CMDEvent(manageHerstellerEventSource, cmdContent));
                break;
            case SHOW_KUCHEN_CMD:
                manageKuchenEventSource.getReadAllKuchenEventHandler().handle(new CMDEvent(manageKuchenEventSource, cmdContent));
                break;
            case SHOW_MUERBEKUCHEN_CMD:
                manageKuchenEventSource.getReadMuerbeKuchenEventHandler().handle(new CMDEvent(manageKuchenEventSource, cmdContent));
                break;
            case SHOW_HEFEKUCHEN_CMD:
                manageKuchenEventSource.getReadHefeKuchenEventHandler().handle(new CMDEvent(manageKuchenEventSource, cmdContent));
                break;
            case SHOW_INCLUDED_ALLERGENE_CMD:
                manageKuchenEventSource.getShowIncludedAllergenEventHandler().handle(new CMDEvent(manageKuchenEventSource, cmdContent));
                break;
            case SHOW_EXCLUDED_ALLERGENE_CMD:
                manageKuchenEventSource.getShowExcludedAllergenEventHandler().handle(new CMDEvent(manageKuchenEventSource, cmdContent));
                break;
            case DELETE_HERSTELLER_CMD:
                manageHerstellerEventSource.getDeleteHerstellerEventHandler().handle(new CMDEvent(manageHerstellerEventSource, cmdContent));
                break;
            case DELETE_KUCHEN_CMD:
                manageKuchenEventSource.getDeleteKuchenEventHandler().handle(new CMDEvent(manageKuchenEventSource, cmdContent));
                break;
            case UPDATE_KUCHEN_CMD:
                manageKuchenEventSource.getUpdateKuchenEventHandler().handle(new CMDEvent(manageKuchenEventSource, cmdContent));
                break;
            case SAVE_JOS_CMD:
                persistenceEventSource.getSaveJOSEventHandler().handle(new CMDEvent(persistenceEventSource, cmdContent));
                break;
            case SAVE_JBP_CMD:
                persistenceEventSource.getSaveJBPEventHandler().handle(new CMDEvent(persistenceEventSource, cmdContent));
                break;
            case LOAD_JOS_CMD:
                persistenceEventSource.getLoadJOSEventHandler().handle(new CMDEvent(persistenceEventSource, cmdContent));
                break;
            case LOAD_JBP_CMD:
                persistenceEventSource.getLoadJBPEventHandler().handle(new CMDEvent(persistenceEventSource, cmdContent));
                break;
            default:
        }
        String response = bos.toString();
        bos.reset();
        return response;
    }


    private static final ManageHerstellerEventSource manageHerstellerEventSource = new ManageHerstellerEventSource();
    private static final ManageKuchenEventSource manageKuchenEventSource = new ManageKuchenEventSource();
    private static final PersistenceEventSource persistenceEventSource = new PersistenceEventSource();
    private static final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    private static final PrintStream redirectedOut = new PrintStream(bos);

    public static void init(Automat automat) {
        AutomatInstanceManager automatInstanceManager = AutomatInstanceManager.getInstance();
        automatInstanceManager.setAutomat(automat);

//        ManageKuchenEventSource einrichten
        DeleteKuchenEventListener deleteKuchenEventListener = new DeleteKuchenEventListener(automatInstanceManager);
        InsertKuchenEventListener insertKuchenEventListener = new InsertKuchenEventListener(automatInstanceManager);
        ReadAllKuchenEventListener readAllKuchenEventListener = new ReadAllKuchenEventListener(automatInstanceManager);
        ReadMuerbeKuchenEventListener readMuerbeKuchenEventListener = new ReadMuerbeKuchenEventListener(automatInstanceManager);
        ReadHefeKuchenEventListener readHefeKuchenEventListener = new ReadHefeKuchenEventListener(automatInstanceManager);
        ShowIncludedAllergenEventListener showIncludedAllergenEventListener = new ShowIncludedAllergenEventListener(automatInstanceManager);
        ShowExcludedAllergenEventListener showExcludedAllergenEventListener = new ShowExcludedAllergenEventListener(automatInstanceManager);
        UpdateKuchenEventListener updateKuchenEventListener = new UpdateKuchenEventListener(automatInstanceManager);

        CMDEventHandler deleteKuchenEventHandler = new CMDEventHandler();
        CMDEventHandler insertKuchenEventHandler = new CMDEventHandler();
        CMDEventHandler readAllKuchenEventHandler = new CMDEventHandler();
        CMDEventHandler readMuerbeKuchenEventHandler = new CMDEventHandler();
        CMDEventHandler readHefeKuchenEventHandler = new CMDEventHandler();
        CMDEventHandler showIncludedAllergenEventHandler = new CMDEventHandler();
        CMDEventHandler showExcludedAllergenEventHandler = new CMDEventHandler();
        CMDEventHandler updateKuchenEventHandler = new CMDEventHandler();

        deleteKuchenEventHandler.addListener(deleteKuchenEventListener);
        insertKuchenEventHandler.addListener(insertKuchenEventListener);
        readAllKuchenEventHandler.addListener(readAllKuchenEventListener);
        readMuerbeKuchenEventHandler.addListener(readMuerbeKuchenEventListener);
        readHefeKuchenEventHandler.addListener(readHefeKuchenEventListener);
        showIncludedAllergenEventHandler.addListener(showIncludedAllergenEventListener);
        showExcludedAllergenEventHandler.addListener(showExcludedAllergenEventListener);
        updateKuchenEventHandler.addListener(updateKuchenEventListener);

        manageKuchenEventSource.setDeleteKuchenEventHandler(deleteKuchenEventHandler);
        manageKuchenEventSource.setInsertKuchenEventHandler(insertKuchenEventHandler);
        manageKuchenEventSource.setReadAllKuchenEventHandler(readAllKuchenEventHandler);
        manageKuchenEventSource.setReadMuerbeKuchenEventHandler(readMuerbeKuchenEventHandler);
        manageKuchenEventSource.setReadHefeKuchenEventHandler(readHefeKuchenEventHandler);
        manageKuchenEventSource.setShowIncludedAllergenEventHandler(showIncludedAllergenEventHandler);
        manageKuchenEventSource.setShowExcludedAllergenEventHandler(showExcludedAllergenEventHandler);
        manageKuchenEventSource.setUpdateKuchenEventHandler(updateKuchenEventHandler);

//        ManageHerstellerEventSource einrichten
        InsertHerstellerEventListener insertHerstellerEventListener = new InsertHerstellerEventListener(automatInstanceManager);
        DeleteHerstellerEventListener deleteHerstellerEventListener = new DeleteHerstellerEventListener(automatInstanceManager);
        ShowHerstellerEventListener showHerstellerEventListener = new ShowHerstellerEventListener(automatInstanceManager);

        CMDEventHandler insertHerstellerEventHandler = new CMDEventHandler();
        CMDEventHandler deleteHerstellerEventHandler = new CMDEventHandler();
        CMDEventHandler showHerstellerEventHandler = new CMDEventHandler();

        insertHerstellerEventHandler.addListener(insertHerstellerEventListener);
        deleteHerstellerEventHandler.addListener(deleteHerstellerEventListener);
        showHerstellerEventHandler.addListener(showHerstellerEventListener);

        manageHerstellerEventSource.setDeleteHerstellerEventHandler(deleteHerstellerEventHandler);
        manageHerstellerEventSource.setInsertHerstellerEventHandler(insertHerstellerEventHandler);
        manageHerstellerEventSource.setShowHerstellerEventHandler(showHerstellerEventHandler);

        // PersistenceEventSource einrichten
        SaveJOSEventListener saveJOSEventListener = new SaveJOSEventListener(automatInstanceManager);
        SaveJBPEventListener saveJBPEventListener = new SaveJBPEventListener(automatInstanceManager);
        LoadJOSEventListener loadJOSEventListener = new LoadJOSEventListener(automatInstanceManager);
        LoadJBPEventListener loadJBPEventListener = new LoadJBPEventListener(automatInstanceManager);

        CMDEventHandler saveJOSEventHandler = new CMDEventHandler();
        CMDEventHandler saveJBPEventHandler = new CMDEventHandler();
        CMDEventHandler loadJOSEventHandler = new CMDEventHandler();
        CMDEventHandler loadJBPEventHandler = new CMDEventHandler();

        saveJOSEventHandler.addListener(saveJOSEventListener);
        saveJBPEventHandler.addListener(saveJBPEventListener);
        loadJOSEventHandler.addListener(loadJOSEventListener);
        loadJBPEventHandler.addListener(loadJBPEventListener);

        persistenceEventSource.setSaveJOSEventHandler(saveJOSEventHandler);
        persistenceEventSource.setSaveJBPEventHandler(saveJBPEventHandler);
        persistenceEventSource.setLoadJOSEventHandler(loadJOSEventHandler);
        persistenceEventSource.setLoadJBPEventHandler(loadJBPEventHandler);

        //redirect System.out
        System.setOut(redirectedOut);
    }
}
