package com.example.cakemachine.gui.popup;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.gl.Automat;
import com.example.cakemachine.gl.HerstellerImpl;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class InsertHerstellerController {
    private ListProperty<HerstellerImpl> herstellerListProperty = new SimpleListProperty<>();
    @FXML private TextField nameTextField;
    @FXML void initialize(){
    }

    private Automat automat;
    public void initData(Automat automat){
        this.automat = automat;
        updateProperties();
    }

    public void confirm(){
        String name = nameTextField.getText();
        if (name == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Keine Eingabe erkannt!");
            alert.showAndWait();
        } else if ("".equals(name) || name.split(" ").length == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Illegaler Herstellername!");
            alert.showAndWait();
        } else {
            boolean existiert = false;
            for (HerstellerImpl hersteller : herstellerListProperty) {
                if (name.equals(hersteller.getName())){
                    existiert = true;
                    break;
                }
            }
            if (existiert){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fehler");
                alert.setHeaderText("Hersteller existiert!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Info");
                alert.setHeaderText("Hersteller erfolgreich eingefügt!");
                alert.showAndWait();
                automat.addHersteller(name);
                updateProperties();
                Stage stage = (Stage) nameTextField.getScene().getWindow();
                stage.close();
            }
        }
    }

    void updateProperties(){
        herstellerListProperty.setValue(FXCollections.observableList(automat.readAllHersteller()));
    }

    public void reset(){
        nameTextField.clear();
    }
}
