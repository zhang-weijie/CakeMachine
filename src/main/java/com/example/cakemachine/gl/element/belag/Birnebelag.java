package com.example.cakemachine.gl.element.belag;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.gl.element.Element;
import com.example.cakemachine.vertrag.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class Birnebelag extends Belag {
    public Birnebelag(Element element) {
        super(element, "Birne", Duration.ofHours(3), new HashSet<>(Collections.singletonList(Allergen.Haselnuss)),90, BigDecimal.valueOf(3));
    }
}
