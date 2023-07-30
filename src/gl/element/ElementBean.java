package gl.element;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import javafx.beans.property.*;

import java.io.Serializable;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class ElementBean implements Serializable {
    static final long serialVersionUID = 1L;
    private StringProperty nameProperty = new SimpleStringProperty();
    private LongProperty haltbarkeitProperty = new SimpleLongProperty();
    private ObjectProperty<String[]> allergeneProperty = new SimpleObjectProperty<>();
    private IntegerProperty naehrwertProperty = new SimpleIntegerProperty();
    private StringProperty preisProperty = new SimpleStringProperty();
    private ObjectProperty<ElementBean> elementProperty = new SimpleObjectProperty<>();

    public String getNameProperty() {
        return nameProperty.get();
    }


    public void setNameProperty(String nameProperty) {
        this.nameProperty.set(nameProperty);
    }


    public void setHaltbarkeitProperty(long haltbarkeitProperty) {
        this.haltbarkeitProperty.set(haltbarkeitProperty);
    }


    public void setAllergeneProperty(String[] allergeneProperty) {
        this.allergeneProperty.set(allergeneProperty);
    }


    public void setNaehrwertProperty(int naehrwertProperty) {
        this.naehrwertProperty.set(naehrwertProperty);
    }

    public void setPreisProperty(String preisProperty) {
        this.preisProperty.set(preisProperty);
    }

    public ElementBean getElementProperty() {
        return elementProperty.get();
    }

    public void setElementProperty(ElementBean elementProperty) {
        this.elementProperty.set(elementProperty);
    }
}
