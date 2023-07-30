package gl;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import vertrag.Hersteller;

import java.io.Serializable;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class HerstellerImpl implements Hersteller, Serializable {
    static final long serialVersionUID=1L;
    private String name;

    public HerstellerImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}