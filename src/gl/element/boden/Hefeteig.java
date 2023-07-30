package gl.element.boden;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import vertrag.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class Hefeteig extends Boden {
    public Hefeteig() {
        super("Hefeteig", Duration.ofHours(10), new HashSet<>(Collections.singletonList(Allergen.Gluten)), 500, BigDecimal.valueOf(10));
    }
}
