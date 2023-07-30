package io;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import gl.Automat;
import gl.persistencestrategy.SaveStrategy;
import java.util.ArrayList;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */

public class SaveWithJOS implements SaveStrategy {
    @Override
    public boolean doSave(Automat automat, String filename) {
        ArrayList<Automat> automats = new ArrayList<>();
        automats.add(automat);
        return SerializationUtil.serialize(filename, automats);
    }
}
