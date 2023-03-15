package com.example.cakemachine.gl.persistencestrategy;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.gl.AutomatBean;

import java.io.Serializable;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public interface LoadStrategy extends Serializable {
    long serialVersionUID = 1L;
    AutomatBean doLoad(String filename);
}
