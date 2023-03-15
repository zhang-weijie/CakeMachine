package com.example.cakemachine.vertrag;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public interface Verkaufsobjekt {
    String getName();
    Duration getHaltbarkeit();
    Collection<Allergen> getAllergene();
    int getNaehrwert();
    BigDecimal getPreis();
}