package io;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import gl.AutomatBean;
import gl.persistencestrategy.LoadStrategy;
import java.util.*;

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
