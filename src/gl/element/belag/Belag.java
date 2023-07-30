package gl.element.belag;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import gl.element.Element;
import vertrag.Allergen;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.HashSet;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public abstract class Belag extends Element {
    Element element;

    public Belag(Element element, String name, Duration haltbarkeit, HashSet<Allergen> allergene, int naehrwert, BigDecimal preis) {
        super(name, haltbarkeit, allergene, naehrwert, preis);
        this.element = element;
    }

    @Override
    public String getName() {
        return element.getName() + "+" + name;
    }

    @Override
    public Duration getHaltbarkeit() {
        return element.getHaltbarkeit().compareTo(haltbarkeit) > 0 ? haltbarkeit : element.getHaltbarkeit();
    }

    @Override
    public HashSet<Allergen> getAllergene() {
        HashSet<Allergen> newAllergene = element.getAllergene();
        newAllergene.addAll(allergene);
        return newAllergene;
    }

    @Override
    public int getNaehrwert() {
        return element.getNaehrwert() + naehrwert;
    }

    @Override
    public BigDecimal getPreis() {
        return element.getPreis().add(preis);
    }

    public Element getElement(){
        return element;
    }
}