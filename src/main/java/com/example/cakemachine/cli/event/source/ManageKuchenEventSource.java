package com.example.cakemachine.cli.event.source;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.cli.console.ConsoleImpl;
import com.example.cakemachine.cli.event.CMDEventHandler;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class ManageKuchenEventSource extends ConsoleImpl {
    private CMDEventHandler deleteKuchenEventHandler;
    private CMDEventHandler insertKuchenEventHandler;
    private CMDEventHandler readAllKuchenEventHandler;
    private CMDEventHandler readMuerbeKuchenEventHandler;
    private CMDEventHandler readHefeKuchenEventHandler;
    private CMDEventHandler showIncludedAllergenEventHandler;
    private CMDEventHandler showExcludedAllergenEventHandler;
    private CMDEventHandler updateKuchenEventHandler;

    public CMDEventHandler getDeleteKuchenEventHandler() {
        return deleteKuchenEventHandler;
    }

    public void setDeleteKuchenEventHandler(CMDEventHandler deleteKuchenEventHandler) {
        this.deleteKuchenEventHandler = deleteKuchenEventHandler;
    }

    public CMDEventHandler getInsertKuchenEventHandler() {
        return insertKuchenEventHandler;
    }

    public void setInsertKuchenEventHandler(CMDEventHandler insertKuchenEventHandler) {
        this.insertKuchenEventHandler = insertKuchenEventHandler;
    }

    public CMDEventHandler getReadAllKuchenEventHandler() {
        return readAllKuchenEventHandler;
    }

    public void setReadAllKuchenEventHandler(CMDEventHandler readAllKuchenEventHandler) {
        this.readAllKuchenEventHandler = readAllKuchenEventHandler;
    }

    public CMDEventHandler getReadHefeKuchenEventHandler() {
        return readHefeKuchenEventHandler;
    }

    public void setReadHefeKuchenEventHandler(CMDEventHandler readHefeKuchenEventHandler) {
        this.readHefeKuchenEventHandler = readHefeKuchenEventHandler;
    }

    public CMDEventHandler getShowExcludedAllergenEventHandler() {
        return showExcludedAllergenEventHandler;
    }

    public void setShowExcludedAllergenEventHandler(CMDEventHandler showExcludedAllergenEventHandler) {
        this.showExcludedAllergenEventHandler = showExcludedAllergenEventHandler;
    }

    public CMDEventHandler getReadMuerbeKuchenEventHandler() {
        return readMuerbeKuchenEventHandler;
    }

    public void setReadMuerbeKuchenEventHandler(CMDEventHandler readMuerbeKuchenEventHandler) {
        this.readMuerbeKuchenEventHandler = readMuerbeKuchenEventHandler;
    }

    public CMDEventHandler getShowIncludedAllergenEventHandler() {
        return showIncludedAllergenEventHandler;
    }

    public void setShowIncludedAllergenEventHandler(CMDEventHandler showIncludedAllergenEventHandler) {
        this.showIncludedAllergenEventHandler = showIncludedAllergenEventHandler;
    }

    public CMDEventHandler getUpdateKuchenEventHandler() {
        return updateKuchenEventHandler;
    }

    public void setUpdateKuchenEventHandler(CMDEventHandler updateKuchenEventHandler) {
        this.updateKuchenEventHandler = updateKuchenEventHandler;
    }
}