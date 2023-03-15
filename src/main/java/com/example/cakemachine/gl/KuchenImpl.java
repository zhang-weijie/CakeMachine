package com.example.cakemachine.gl;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.gl.element.Element;
import com.example.cakemachine.vertrag.Allergen;
import com.example.cakemachine.vertrag.Kuchen;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class KuchenImpl implements Kuchen, Serializable {
    static final long serialVersionUID = 3L;
    private Element element;
    private HerstellerImpl hersteller;
    private int fachnummer;
    private Instant insertionsdatum;
    private Date inspeketionsdatum;

    public KuchenImpl(Element element, HerstellerImpl hersteller) {
        this.element = element;
        this.hersteller = hersteller;
    }

    public String getName() {
        return element.getName();
    }

    public Duration getHaltbarkeit() {
        if (getInsertionsdatum() == null) {
            return element.getHaltbarkeit();
        } else {
            return element.getHaltbarkeit().minus(Duration.between(getInsertionsdatum(), Instant.now()));
        }
    }

    public HashSet<Allergen> getAllergene() {
        return element.getAllergene();
    }

    public int getNaehrwert() {
        return element.getNaehrwert();
    }

    public BigDecimal getPreis() {
        return element.getPreis();
    }

    void setFachnummer(int fachnummer) {
        this.fachnummer = fachnummer;
    }

    void setInsertionsdatum(Instant insertionsdatum) {
        this.insertionsdatum = insertionsdatum;
    }

    void setInspektionsdatum(Date inspeketionsdatum) {
        this.inspeketionsdatum = inspeketionsdatum;
    }

    @Override
    public HerstellerImpl getHersteller() {
        return hersteller;
    }

    @Override
    public int getFachnummer() {
        return fachnummer;
    }

    @Override
    public Instant getInsertionsdatum() {
        return insertionsdatum;
    }

    @Override
    public Date getInspektionsdatum() {
        return inspeketionsdatum;
    }

    public Element getElement(){
        return element;
    }

    @Override
    public String toString() {
        return "{Fachnummer: " + getFachnummer() +
                ", Hersteller: " + getHersteller().getName() +
                ", Inspeketionsdatum: " + getInspektionsdatum() +
                ", Preis: " + getPreis() +
                ", Name: " + getName() +
                ", Haltbarkeit: " + getHaltbarkeit() +
                ", Allergene: " + getAllergene() +
                ", Naehrwert: " + getNaehrwert() +
                "}";
    }
}