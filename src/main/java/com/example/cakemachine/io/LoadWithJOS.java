package com.example.cakemachine.io;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.gl.Automat;
import com.example.cakemachine.gl.AutomatBean;
import com.example.cakemachine.gl.PersistenceUtil;
import com.example.cakemachine.gl.persistencestrategy.LoadStrategy;

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
