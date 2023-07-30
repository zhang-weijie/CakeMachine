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
public class Muerbeteig extends Boden {
    public Muerbeteig() {
        super("Muerbeteig", Duration.ofHours(12), new HashSet<>(Collections.singletonList(Allergen.Gluten)), 600, BigDecimal.valueOf(12));
    }
}
