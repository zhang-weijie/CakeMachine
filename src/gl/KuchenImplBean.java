package gl;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import gl.element.ElementBean;
import javafx.beans.property.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class KuchenImplBean implements Serializable {
    private static final long serialVersionUID=1L;
    private IntegerProperty fachnummerProperty = new SimpleIntegerProperty();
    private ObjectProperty<Date> inspektionsdatumProperty = new SimpleObjectProperty<>();
    private LongProperty insertionsdatumProperty = new SimpleLongProperty();
    private StringProperty herstellerNameProperty = new SimpleStringProperty();
    private ObjectProperty<ElementBean> elementProperty =new SimpleObjectProperty<>();


    public int getFachnummerProperty() {
        return fachnummerProperty.get();
    }

    public void setFachnummerProperty(int fachnummerProperty) {
        this.fachnummerProperty.set(fachnummerProperty);
    }



    public Date getInspektionsdatumProperty() {
        return inspektionsdatumProperty.get();
    }


    public void setInspektionsdatumProperty(Date inspektionsdatumProperty) {
        this.inspektionsdatumProperty.set(inspektionsdatumProperty);
    }

    public long getInsertionsdatumProperty() {
        return insertionsdatumProperty.get();
    }


    public void setInsertionsdatumProperty(long insertionsdatumProperty) {
        this.insertionsdatumProperty.set(insertionsdatumProperty);
    }

    public String getHerstellerNameProperty() {
        return herstellerNameProperty.get();
    }


    public void setHerstellerNameProperty(String herstellerNameProperty) {
        this.herstellerNameProperty.set(herstellerNameProperty);
    }

    public ElementBean getElementProperty() {
        return elementProperty.get();
    }


    public void setElementProperty(ElementBean elementProperty) {
        this.elementProperty.set(elementProperty);
    }
}