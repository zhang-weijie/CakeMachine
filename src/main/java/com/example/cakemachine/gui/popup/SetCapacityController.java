package com.example.cakemachine.gui.popup;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.gl.Automat;
import com.example.cakemachine.gl.AutomatInstanceManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class SetCapacityController {
    @FXML
    private TextField nameTextField;

    private AutomatInstanceManager automatInstanceManager;

    public void initData(AutomatInstanceManager automatInstanceManager) {
        this.automatInstanceManager = automatInstanceManager;
    }

    public void confirm() {
        String capacityStr = nameTextField.getText();
        if (capacityStr == null || "".equals(capacityStr)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Keine Eingabe!");
            alert.showAndWait();
        } else if (!capacityStr.matches("[0-9]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Falsche Eingabe!");
            alert.showAndWait();
        } else if (Integer.parseInt(capacityStr) == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Kapazität muss positiv sein!");
            alert.showAndWait();
        } else {
            int capacity = Integer.parseInt(capacityStr);
            Automat automat = new Automat(capacity);
            automatInstanceManager.setAutomat(automat);
            Stage stage = (Stage) nameTextField.getScene().getWindow();
            stage.close();
        }
    }

    public void reset() {
        nameTextField.clear();
    }
}
