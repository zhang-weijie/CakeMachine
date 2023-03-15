package com.example.cakemachine.io;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.gl.AutomatBean;
import com.example.cakemachine.gl.persistencestrategy.LoadStrategy;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class LoadWithJBP implements LoadStrategy {

    @Override
    public AutomatBean doLoad(String filename) {
        Collection<AutomatBean> automatBeans = EncodeUtil.decode(filename);
        if (automatBeans == null || automatBeans.size() == 0){
            return null;
        }
        ArrayList<AutomatBean> automatBeansList = new ArrayList<>(automatBeans);
        return automatBeansList.get(0);
    }
}
