package com.example.cakemachine.gui.popup;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.gl.Automat;
import com.example.cakemachine.gl.HerstellerImpl;
import com.example.cakemachine.gl.KuchenImpl;
import com.example.cakemachine.gl.element.Element;
import com.example.cakemachine.gl.element.belag.*;
import com.example.cakemachine.gl.element.boden.Hefeteig;
import com.example.cakemachine.gl.element.boden.Muerbeteig;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.LinkedList;
import java.util.List;


/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class InsertKuchenController {
    //    private Automat automat;
    @FXML
    private ChoiceBox<String> herstellerNameChoiceBox;
    @FXML
    private ChoiceBox<String> bodenTypChoiceBox;
    private IntegerProperty apfelNumProperty = new SimpleIntegerProperty();
    private StringProperty apfelNumStrProperty = new SimpleStringProperty();
    @FXML
    private TextField apfelTextField;
    private IntegerProperty birneNumProperty = new SimpleIntegerProperty();
    @FXML
    private TextField birneTextField;
    private IntegerProperty kirscheNumProperty = new SimpleIntegerProperty();
    @FXML
    private TextField kirscheTextField;
    private IntegerProperty sahneNumProperty = new SimpleIntegerProperty();
    @FXML
    private TextField sahneTextField;
    private IntegerProperty puddingNumProperty = new SimpleIntegerProperty();
    @FXML
    private TextField puddingTextField;

    @FXML
    void initialize() {
        LinkedList<String> bodenTypList = new LinkedList<>();
        bodenTypList.add("Muerbeteig");
        bodenTypList.add("Hefeteig");
        bodenTypChoiceBox.setItems(FXCollections.observableList(bodenTypList));

        StringConverter<Number> stringConverter = new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return object.toString();
            }

            @Override
            public Number fromString(String string) {
                return Integer.parseInt(string);
            }
        };

        apfelTextField.textProperty().bindBidirectional(apfelNumProperty, stringConverter);
        birneTextField.textProperty().bindBidirectional(birneNumProperty, stringConverter);
        kirscheTextField.textProperty().bindBidirectional(kirscheNumProperty, stringConverter);
        sahneTextField.textProperty().bindBidirectional(sahneNumProperty, stringConverter);
        puddingTextField.textProperty().bindBidirectional(puddingNumProperty, stringConverter);
    }

    void updateProperties() {
        List<String> herstellerNameList = new LinkedList<>();
        for (HerstellerImpl hersteller : automat.readAllHersteller()) {
            herstellerNameList.add(hersteller.getName());
        }
        herstellerNameChoiceBox.setItems(FXCollections.observableList(herstellerNameList));
    }

    private Automat automat;

    public void initData(Automat automat) {
        this.automat = automat;
        updateProperties();
    }

    void minus(IntegerProperty numProperty) {
        if (numProperty.getValue() > 0) {
            numProperty.setValue(numProperty.getValue() - 1);
        }
    }

    public void apfelMinus() {
        minus(apfelNumProperty);
    }

    public void birneMinus() {
        minus(birneNumProperty);
    }

    public void kirscheMinus() {
        minus(kirscheNumProperty);
    }

    public void sahneMinus() {
        minus(sahneNumProperty);
    }

    public void puddingMinus() {
        minus(puddingNumProperty);
    }

    void plus(IntegerProperty numProperty) {
        if (numProperty.getValue() < Integer.MAX_VALUE) {
            numProperty.setValue(numProperty.getValue() + 1);
        }
    }

    public void apfelPlus() {
        plus(apfelNumProperty);
    }

    public void birnePlus() {
        plus(birneNumProperty);
    }

    public void kirschePlus() {
        plus(kirscheNumProperty);
    }

    public void sahnePlus() {
        plus(sahneNumProperty);
    }

    public void puddingPlus() {
        plus(puddingNumProperty);
    }


    public void confirm() {
        String herstellerName = herstellerNameChoiceBox.getSelectionModel().getSelectedItem();
        String bodenTyp = bodenTypChoiceBox.getSelectionModel().getSelectedItem();
        int apfelAnzahl = Integer.parseInt(apfelTextField.getText());
        int birneAnzahl = Integer.parseInt(birneTextField.getText());
        int kirscheAnzahl = Integer.parseInt(kirscheTextField.getText());
        int sahneAnzahl = Integer.parseInt(sahneTextField.getText());
        int puddingAnzahl = Integer.parseInt(puddingTextField.getText());

        boolean inserted = false;
        try {
            Element kuchenElement;
            switch (bodenTyp) {
                case "Muerbeteig":
                    kuchenElement = new Muerbeteig();
                    break;
                default:
                    kuchenElement = new Hefeteig();
                    break;
            }
            for (int i = 0; i < apfelAnzahl; i++) {
                kuchenElement = new Apfelbelag(kuchenElement);
            }
            for (int i = 0; i < birneAnzahl; i++) {
                kuchenElement = new Birnebelag(kuchenElement);
            }
            for (int i = 0; i < kirscheAnzahl; i++) {
                kuchenElement = new Kirschebelag(kuchenElement);
            }
            for (int i = 0; i < sahneAnzahl; i++) {
                kuchenElement = new Sahnebelag(kuchenElement);
            }
            for (int i = 0; i < puddingAnzahl; i++) {
                kuchenElement = new Puddingbelag(kuchenElement);
            }
            inserted = automat.insertKuchen(new KuchenImpl(kuchenElement, automat.getHersteller(herstellerName)));
        } catch (Exception ignored) {}

        if (!inserted) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Falsche Eingabe oder alle Fächer sind besetzt!");
            alert.showAndWait();
        } else {
            updateProperties();
            Stage stage = (Stage) herstellerNameChoiceBox.getScene().getWindow();
            stage.close();
        }
    }

    public void reset() {
        herstellerNameChoiceBox.getSelectionModel().clearSelection();
        bodenTypChoiceBox.getSelectionModel().clearSelection();
        apfelTextField.setText("0");
        birneTextField.setText("0");
        kirscheTextField.setText("0");
        sahneTextField.setText("0");
        puddingTextField.setText("0");
    }
}
