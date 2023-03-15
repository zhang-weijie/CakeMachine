package com.example.cakemachine.gl.element;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.vertrag.Allergen;
import com.example.cakemachine.vertrag.Verkaufsobjekt;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.HashSet;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public abstract class Element implements Verkaufsobjekt, Serializable {
    static final long serialVersionUID=2L;
    protected String name;
    protected Duration haltbarkeit;
    protected HashSet<Allergen> allergene;
    protected int naehrwert;
    protected BigDecimal preis;

    public Element(String name, Duration haltbarkeit, HashSet<Allergen> allergene, int naehrwert, BigDecimal preis) {
        this.name = name;
        this.haltbarkeit = haltbarkeit;
        this.allergene = allergene;
        this.naehrwert = naehrwert;
        this.preis = preis;
    }

    public String getName() {
        return name;
    }

    public Duration getHaltbarkeit() {
        return haltbarkeit;
    }

    public HashSet<Allergen> getAllergene() {
        return new HashSet<>(allergene);
    }

    public int getNaehrwert() {
        return naehrwert;
    }

    public BigDecimal getPreis() {
        return preis;
    }
}