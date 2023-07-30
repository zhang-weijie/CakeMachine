package gl.persistencestrategy;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import gl.Automat;

import java.io.Serializable;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public interface SaveStrategy extends Serializable {
    long serialVersionUID = 1L;
    boolean doSave(Automat automat, String filename);
}
