package com.example.cakemachine.gl.element.boden;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.gl.element.Element;
import com.example.cakemachine.vertrag.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.HashSet;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public abstract class Boden extends Element {
    public Boden(String name, Duration haltbarkeit, HashSet<Allergen> allergene, int naehrwert, BigDecimal preis) {
        super(name, haltbarkeit, allergene, naehrwert, preis);
    }
}
