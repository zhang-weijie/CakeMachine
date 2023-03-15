package com.example.cakemachine.net.client;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.cli.console.ConsoleImpl;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class Frontend {
    private static final String MODUS_EINFUEGEN = ":c";
    private static final String MODUS_LOESCHEN = ":d";
    private static final String MODUS_ANZEIGEN = ":r";
    private static final String MODUS_AENDERN = ":u";
    private static final String MODUS_PERSISTENZ = ":p";
    private static String curModus = ":r";

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
    private static String curCMD;


    public static String getCMD(ConsoleImpl console) {
        String cmd = null;
        StringBuilder cmdStrBuilder = new StringBuilder();
        while (cmdStrBuilder.length() == 0) {
            cmd = console.readString("Modus: " + curModus + ". Geben Sie einen Befehl ein: ");
            switch (cmd) {
                case MODUS_AENDERN:
                    curModus = MODUS_AENDERN;
                    break;
                case MODUS_ANZEIGEN:
                    curModus = MODUS_ANZEIGEN;
                    break;
                case MODUS_EINFUEGEN:
                    curModus = MODUS_EINFUEGEN;
                    break;
                case MODUS_LOESCHEN:
                    curModus = MODUS_LOESCHEN;
                    break;
                case MODUS_PERSISTENZ:
                    curModus = MODUS_PERSISTENZ;
                    break;
                default: {
                    String[] cmdArr = cmd.trim().split("\\s+");
                    switch (curModus) {
                        case MODUS_ANZEIGEN:
                            switch (cmdArr[0]) {
                                case "hersteller":
                                    if (cmdArr.length == 1) {
                                        cmdStrBuilder.append(SHOW_HERSTELLER_CMD);
                                    } else {
                                        System.out.println("Falsche Anzahl von Parametern");
                                    }
                                    break;
                                case "kuchen":
                                    if (cmdArr.length == 1) {
                                        cmdStrBuilder.append(SHOW_KUCHEN_CMD);
                                    } else {
                                        if (cmdArr.length == 2 && "Muerbeteig".equals(cmdArr[1])) {
                                            cmdStrBuilder.append(SHOW_MUERBEKUCHEN_CMD);
                                        } else if (cmdArr.length == 2 && "Hefeteig".equals(cmdArr[1])) {
                                            cmdStrBuilder.append(SHOW_HEFEKUCHEN_CMD);
                                        } else {
                                            System.out.println("Unbekannter Bodentyp!");
                                        }
                                    }
                                    break;
                                case "allergene":
                                    if (cmdArr.length == 2) {
                                        switch (cmdArr[1]) {
                                            case "i":
                                                cmdStrBuilder.append(SHOW_INCLUDED_ALLERGENE_CMD);
                                                break;
                                            case "e":
                                                cmdStrBuilder.append(SHOW_EXCLUDED_ALLERGENE_CMD);
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
                                cmdStrBuilder.append(INSERT_HERSTELLER_CMD);
                            } else if (cmdArr.length <= 7) {
                                cmdStrBuilder.append(INSERT_KUCHEN_CMD);
                            } else {
                                System.out.println("Falscher Befehl!");
                            }
                            break;
                        case MODUS_AENDERN:
                            if (cmdArr.length == 1) {
                                if (cmdArr[0].matches("[0-9]*")) {
                                    cmdStrBuilder.append(UPDATE_KUCHEN_CMD);
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
                                    cmdStrBuilder.append(DELETE_KUCHEN_CMD);
                                } else {
                                    cmdStrBuilder.append(DELETE_HERSTELLER_CMD);
                                }
                            } else {
                                System.out.println("Falscher Befehl!");
                            }
                            break;
                        case MODUS_PERSISTENZ:
                            if (cmdArr.length == 1) {
                                switch (cmdArr[0]) {
                                    case "saveJOS":
                                        cmdStrBuilder.append(SAVE_JOS_CMD);
                                        break;
                                    case "saveJBP":
                                        cmdStrBuilder.append(SAVE_JBP_CMD);
                                        break;
                                    case "loadJOS":
                                        cmdStrBuilder.append(LOAD_JOS_CMD);
                                        break;
                                    case "loadJBP":
                                        cmdStrBuilder.append(LOAD_JBP_CMD);
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
        curCMD = cmdStrBuilder.substring(0);
        cmdStrBuilder.append(" ");
        cmdStrBuilder.append(cmd);
        return cmdStrBuilder.toString();
    }
}