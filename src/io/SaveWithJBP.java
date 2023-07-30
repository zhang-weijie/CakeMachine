package io;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import gl.Automat;
import gl.AutomatBean;
import gl.persistencestrategy.SaveStrategy;
import gl.PersistenceUtil;

import java.util.ArrayList;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class SaveWithJBP implements SaveStrategy {
    @Override
    public boolean doSave(Automat automat, String filename) {
        ArrayList<AutomatBean> automatBeans = new ArrayList<>();
        automatBeans.add(PersistenceUtil.convertToAutomatBean(automat));
        return EncodeUtil.encode(filename, automatBeans);
    }
}
