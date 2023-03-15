package com.example.cakemachine.cli.client;

import com.example.cakemachine.cli.console.ConsoleImpl;
import com.example.cakemachine.cli.event.CMDEvent;
import com.example.cakemachine.cli.event.source.ManageHerstellerEventSource;
import com.example.cakemachine.cli.event.source.ManageKuchenEventSource;
import com.example.cakemachine.cli.event.source.PersistenceEventSource;

public class Client {
    private static final String MODUS_EINFUEGEN = ":c";
    private static final String MODUS_LOESCHEN = ":d";
    private static final String MODUS_ANZEIGEN = ":r";
    private static final String MODUS_AENDERN = ":u";
    private static final String MODUS_PERSISTENZ = ":p";
    public static final String STATUS_JOS_FILE = "status.ser";
    public static final String STATUS_JBP_FILE = "status.xml";

    private ConsoleImpl clientConsole;
    private ManageHerstellerEventSource clientManageHerstellerEventSource;
    private ManageKuchenEventSource clientManageKuchenEventSource;
    private PersistenceEventSource clientPersistenceEventSource;

    public Client(ConsoleImpl console, ManageHerstellerEventSource manageHerstellerEventSource, ManageKuchenEventSource manageKuchenEventSource, PersistenceEventSource persistenceEventSource) {
        clientConsole = console;
        clientManageHerstellerEventSource = manageHerstellerEventSource;
        clientManageKuchenEventSource = manageKuchenEventSource;
        clientPersistenceEventSource = persistenceEventSource;
    }

    private String CUR_MODUS = MODUS_ANZEIGEN;
    public void run() {
        boolean toExit = false;
        while (!toExit) {
            String cmd = clientConsole.readString("Modus: " + CUR_MODUS + ". Geben Sie einen Befehl ein: ");
            if ("exit".equals(cmd)) {
                System.out.println("Exiting...");
                toExit = true;
            } else {
                switch (cmd) {
                    case MODUS_AENDERN:
                        CUR_MODUS = MODUS_AENDERN;
                        break;
                    case MODUS_ANZEIGEN:
                        CUR_MODUS = MODUS_ANZEIGEN;
                        break;
                    case MODUS_EINFUEGEN:
                        CUR_MODUS = MODUS_EINFUEGEN;
                        break;
                    case MODUS_LOESCHEN:
                        CUR_MODUS = MODUS_LOESCHEN;
                        break;
                    case MODUS_PERSISTENZ:
                        CUR_MODUS = MODUS_PERSISTENZ;
                        break;
                    default: {
                        String[] cmdArr = cmd.trim().split("\\s+");
                        switch (CUR_MODUS) {
                            case MODUS_ANZEIGEN:
                                switch (cmdArr[0]) {
                                    case "hersteller":
                                        if (cmdArr.length == 1) {
                                            if (clientManageHerstellerEventSource.getShowHerstellerEventHandler() != null) {
                                                clientManageHerstellerEventSource.getShowHerstellerEventHandler().handle(new CMDEvent(clientManageHerstellerEventSource, cmdArr));
                                            } else {
                                                System.out.println("Funktion deaktiviert!");
                                            }
                                        } else {
                                            System.out.println("Falsche Anzahl von Parametern");
                                        }
                                        break;
                                    case "kuchen":
                                        if (cmdArr.length == 1) {
                                            if (clientManageKuchenEventSource.getReadAllKuchenEventHandler() != null) {
                                                clientManageKuchenEventSource.getReadAllKuchenEventHandler().handle(new CMDEvent(clientManageKuchenEventSource, cmdArr));
                                            } else {
                                                System.out.println("Funktion deaktiviert!");
                                            }
                                        } else {
                                            if (cmdArr.length == 2 && "Muerbeteig".equals(cmdArr[1])) {
                                                if (clientManageKuchenEventSource.getReadMuerbeKuchenEventHandler() != null) {
                                                    clientManageKuchenEventSource.getReadMuerbeKuchenEventHandler().handle(new CMDEvent(clientManageKuchenEventSource, cmdArr));
                                                } else {
                                                    System.out.println("Funktion deaktiviert!");
                                                }
                                            } else if (cmdArr.length == 2 && "Hefeteig".equals(cmdArr[1])) {
                                                if (clientManageKuchenEventSource.getReadHefeKuchenEventHandler() != null) {
                                                    clientManageKuchenEventSource.getReadHefeKuchenEventHandler().handle(new CMDEvent(clientManageKuchenEventSource, cmdArr));
                                                } else {
                                                    System.out.println("Funktion deaktiviert!");
                                                }
                                            } else {
                                                System.out.println("Unbekannter Bodentyp!");
                                            }
                                        }
                                        break;
                                    case "allergene":
                                        if (cmdArr.length == 2) {
                                            switch (cmdArr[1]) {
                                                case "i":
                                                    if (clientManageKuchenEventSource.getShowIncludedAllergenEventHandler() != null) {
                                                        clientManageKuchenEventSource.getShowIncludedAllergenEventHandler().handle(new CMDEvent(clientManageKuchenEventSource, cmdArr));
                                                    } else {
                                                        System.out.println("Funktion deaktiviert!");
                                                    }
                                                    break;
                                                case "e":
                                                    if (clientManageKuchenEventSource.getShowExcludedAllergenEventHandler() != null) {
                                                        clientManageKuchenEventSource.getShowExcludedAllergenEventHandler().handle(new CMDEvent(clientManageHerstellerEventSource, cmdArr));
                                                    } else {
                                                        System.out.println("Funktion deaktiviert!");
                                                    }
                                                    break;
                                                default:
                                                    System.out.println("Falscher Befehl!");
                                            }
                                        } else {
                                            System.out.println("Falsche Anzahl von Parametern!");
                                        }
                                        break;
                                    default:
                                        System.out.println("Falscher Befehl!");
                                }
                                break;
                            case MODUS_EINFUEGEN:
                                if (cmdArr.length == 1) {
                                    if (clientManageHerstellerEventSource.getInsertHerstellerEventHandler() != null) {
                                        clientManageHerstellerEventSource.getInsertHerstellerEventHandler().handle(new CMDEvent(clientManageHerstellerEventSource, cmdArr));
                                    } else {
                                        System.out.println("Funktion deaktiviert!");
                                    }
                                } else if (cmdArr.length <= 7) {
                                    if (clientManageKuchenEventSource.getInsertKuchenEventHandler() != null) {
                                        clientManageKuchenEventSource.getInsertKuchenEventHandler().handle(new CMDEvent(clientManageKuchenEventSource, cmdArr));
                                    } else {
                                        System.out.println("Funktion deaktiviert!");
                                    }
                                } else {
                                    System.out.println("Falscher Befehl!");
                                }
                                break;
                            case MODUS_AENDERN:
                                if (cmdArr.length == 1) {
                                    if (cmdArr[0].matches("[0-9]*")) {
                                        if (clientManageKuchenEventSource.getUpdateKuchenEventHandler() != null) {
                                            clientManageKuchenEventSource.getUpdateKuchenEventHandler().handle(new CMDEvent(clientManageKuchenEventSource, cmdArr));
                                        } else {
                                            System.out.println("Funktion deaktiviert!");
                                        }
                                    } else {
                                        System.out.println("Ungueltige Fachnummer!");
                                    }
                                } else {
                                    System.out.println("Falscher Befehl!");
                                }
                                break;
                            case MODUS_LOESCHEN:
                                if (cmdArr.length == 1) {
                                    if (cmdArr[0].matches("[0-9]*")) {
                                        if (clientManageKuchenEventSource.getDeleteKuchenEventHandler() != null) {
                                            clientManageKuchenEventSource.getDeleteKuchenEventHandler().handle(new CMDEvent(clientManageKuchenEventSource, cmdArr));
                                        } else {
                                            System.out.println("Funktion deaktiviert!");
                                        }
                                    } else {
                                        if (clientManageHerstellerEventSource.getDeleteHerstellerEventHandler() != null) {
                                            clientManageHerstellerEventSource.getDeleteHerstellerEventHandler().handle(new CMDEvent(clientManageHerstellerEventSource, cmdArr));
                                        } else {
                                            System.out.println("Funktion deaktiviert!");
                                        }
                                    }
                                } else {
                                    System.out.println("Falscher Befehl!");
                                }
                                break;
                            case MODUS_PERSISTENZ:
                                if (cmdArr.length == 1) {
                                    switch (cmdArr[0]) {
                                        case "saveJOS":
                                            if (clientPersistenceEventSource.getSaveJOSEventHandler() != null) {
                                                clientPersistenceEventSource.getSaveJOSEventHandler().handle(new CMDEvent(clientPersistenceEventSource, cmdArr));
                                            } else {
                                                System.out.println("Funktion deaktiviert!");
                                            }
                                            break;
                                        case "saveJBP":
                                            if (clientPersistenceEventSource.getSaveJBPEventHandler() != null) {
                                                clientPersistenceEventSource.getSaveJBPEventHandler().handle(new CMDEvent(clientPersistenceEventSource, cmdArr));
                                            } else {
                                                System.out.println("Funktion deaktiviert!");
                                            }
                                            break;
                                        case "loadJOS":
                                            if (clientPersistenceEventSource.getLoadJOSEventHandler() != null) {
                                                clientPersistenceEventSource.getLoadJOSEventHandler().handle(new CMDEvent(clientPersistenceEventSource, cmdArr));
                                            } else {
                                                System.out.println("Funktion deaktiviert!");
                                            }
                                            break;
                                        case "loadJBP":
                                            if (clientPersistenceEventSource.getLoadJBPEventHandler() != null) {
                                                clientPersistenceEventSource.getLoadJBPEventHandler().handle(new CMDEvent(clientPersistenceEventSource, cmdArr));
                                            } else {
                                                System.out.println("Funktion deaktiviert!");
                                            }
                                            break;
                                        default:
                                            System.out.println("Falscher Befehl!");
                                    }
                                } else {
                                    System.out.println("Falscher Befehl!");
                                }
                        }
                    }
                }
            }
        }
    }
}