package com.example.cakemachine.gl;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;
import java.util.Map;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class AutomatBean {
    private IntegerProperty maxCapacityProperty = new SimpleIntegerProperty();
    private ObjectProperty<List<HerstellerImplBean>> herstellerListProperty = new SimpleObjectProperty<>();
    private ObjectProperty<List<KuchenImplBean>> kuchenListProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Map<Integer, KuchenImplBean>> fachnummerKuchenMapProperty = new SimpleObjectProperty<>();

    public int getMaxCapacityProperty() {
        return maxCapacityProperty.get();
    }

    public void setMaxCapacityProperty(int maxCapacityProperty) {
        this.maxCapacityProperty.set(maxCapacityProperty);
    }

    public List<HerstellerImplBean> getHerstellerListProperty() {
        return herstellerListProperty.get();
    }


    public void setHerstellerListProperty(List<HerstellerImplBean> herstellerListProperty) {
        this.herstellerListProperty.set(herstellerListProperty);
    }

    public List<KuchenImplBean> getKuchenListProperty() {
        return kuchenListProperty.get();
    }

    public void setKuchenListProperty(List<KuchenImplBean> kuchenListProperty) {
        this.kuchenListProperty.set(kuchenListProperty);
    }
}
