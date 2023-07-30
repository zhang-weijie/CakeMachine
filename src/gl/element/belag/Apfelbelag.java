package gl.element.belag;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import gl.element.Element;
import vertrag.Allergen;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class Apfelbelag extends Belag {
    public Apfelbelag(Element element) {
        super(element, "Apfel", Duration.ofHours(4), new HashSet<>(Collections.singletonList(Allergen.Gluten)),100, BigDecimal.valueOf(4));
    }
}