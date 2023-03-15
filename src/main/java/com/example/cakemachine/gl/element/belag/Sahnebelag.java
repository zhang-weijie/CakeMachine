package com.example.cakemachine.gl.element.belag;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.gl.element.Element;
import com.example.cakemachine.vertrag.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class Sahnebelag extends Belag {
    public Sahnebelag(Element element) {
        super(element, "Sahne", Duration.ofHours(6), new HashSet<>(Arrays.asList(Allergen.Gluten, Allergen.Sesamsamen, Allergen.Haselnuss, Allergen.Sesamsamen)),200, BigDecimal.valueOf(6));
    }
}
