package io;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import gl.Automat;
import gl.AutomatBean;
import gl.persistencestrategy.LoadStrategy;
import gl.PersistenceUtil;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class LoadWithJOS implements LoadStrategy {
    @Override
    public AutomatBean doLoad(String filename) {
        Collection<Automat> automats = SerializationUtil.deserialize(filename);
        if (automats == null || automats.size() == 0){
            return null;
        }
        return PersistenceUtil.convertToAutomatBean(new ArrayList<>(automats).get(0));
    }
}
