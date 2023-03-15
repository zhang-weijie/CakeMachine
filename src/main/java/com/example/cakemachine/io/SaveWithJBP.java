package com.example.cakemachine.io;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.gl.Automat;
import com.example.cakemachine.gl.AutomatBean;
import com.example.cakemachine.gl.PersistenceUtil;
import com.example.cakemachine.gl.persistencestrategy.SaveStrategy;

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
