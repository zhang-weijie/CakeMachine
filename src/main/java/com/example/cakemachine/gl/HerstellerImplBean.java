package com.example.cakemachine.gl;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class HerstellerImplBean implements Serializable {
    static final long serialVersionUID=1L;
    private StringProperty nameProperty = new SimpleStringProperty();

    public String getNameProperty() {
        return nameProperty.get();
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty.set(nameProperty);
    }
}