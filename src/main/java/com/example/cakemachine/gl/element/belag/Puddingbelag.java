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
public class Puddingbelag extends Belag {
    public Puddingbelag(Element element) {
        super(element, "Pudding", Duration.ofHours(5), new HashSet<>(Arrays.asList(Allergen.Haselnuss, Allergen.Erdnuss)),150, BigDecimal.valueOf(5));
    }
}
