package gui;

import gl.*;
import io.LoadWithJBP;
import io.LoadWithJOS;
import io.SaveWithJBP;
import io.SaveWithJOS;
import gui.popup.InsertHerstellerPopup;
import gui.popup.InsertKuchenPopup;
import gui.popup.SetCapacityPopup;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import vertrag.Allergen;

import java.io.File;
import java.time.Duration;
import java.util.*;
import java.util.function.Consumer;

public class GUIController {
    private AutomatInstanceManager automatInstanceManager;
    private MapProperty<HerstellerImpl, Integer> herstellerKuchenanzahlMapProperty = new SimpleMapProperty<>();
    private MapProperty<KuchenImpl, String> kuchenHerstellerNameMapProperty = new SimpleMapProperty<>();
    private ListProperty<KuchenImpl> kuchenListProperty = new SimpleListProperty<>();
    private SetProperty<Allergen> allergenSetProperty = new SimpleSetProperty<>();

    @FXML private TableView<HerstellerImpl> herstellerTableView;
    @FXML private TableColumn<HerstellerImpl, String> herstellerNameTableColumn;
    @FXML private TableColumn<HerstellerImpl, Integer> herstellerKuchenanzahlTableColumn;
    @FXML private TableView<KuchenImpl> kuchenTableView;
    @FXML private TableColumn<KuchenImpl, Integer> kuchenFachnummerTableColumn;
    @FXML private TableColumn<KuchenImpl, String> kuchenHerstellerTableColumn;
    @FXML private TableColumn<KuchenImpl, Date> kuchenInspektionsdatumTableColumn;
    @FXML private TableColumn<KuchenImpl, Duration> kuchenHaltbarkeitTableColumn;
    @FXML private TableView<Allergen> allergenTableView;
    @FXML private TableColumn<Allergen, Allergen> allergenTypTableColumn;
    @FXML private Label allergenLabel;
    @FXML void initialize(){
        herstellerNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        herstellerKuchenanzahlTableColumn.setCellValueFactory(param -> new ObservableValueBase<Integer>() {
            @Override
            public Integer getValue() {
                return herstellerKuchenanzahlMapProperty.get(param.getValue());
            }
        });

        kuchenFachnummerTableColumn.setCellValueFactory(new PropertyValueFactory<>("fachnummer"));
        kuchenHerstellerTableColumn.setCellValueFactory(param -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return kuchenHerstellerNameMapProperty.get(param.getValue());
            }
        });
        kuchenInspektionsdatumTableColumn.setCellValueFactory(new PropertyValueFactory<>("inspektionsdatum"));
        kuchenHaltbarkeitTableColumn.setCellValueFactory(new PropertyValueFactory<>("haltbarkeit"));
        allergenTypTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
    }

    private SetCapacityPopup setCapacityPopup = new SetCapacityPopup();
    public void initData(AutomatInstanceManager automatInstanceManager) throws Exception {
        this.automatInstanceManager = automatInstanceManager;
        while (automatInstanceManager.getAutomat() == null){
            setCapacityPopup.display(automatInstanceManager);
        }
        updateProperties();
    }

    public void updateKuchen(){
        KuchenImpl kuchen = kuchenTableView.getSelectionModel().getSelectedItem();
        if (kuchen == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Kein Kuchen ausgewählt!");
            alert.showAndWait();
        } else {
            automatInstanceManager.getAutomat().updateKuchen(kuchen.getFachnummer());
        }
        updateProperties();
    }

    private InsertKuchenPopup insertKuchenPopup = new InsertKuchenPopup();
    public void insertKuchen() throws Exception {
        insertKuchenPopup.display(automatInstanceManager.getAutomat());
        updateProperties();
    }

    public void deleteKuchen(){
        KuchenImpl kuchen = kuchenTableView.getSelectionModel().getSelectedItem();
        if (kuchen == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Kein Kuchen ausgewählt!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Bestätigung");
            alert.setHeaderText("Möchten Sie den Kuchen entfernen?");
            alert.setContentText("Klicken Sie OK zu bestätigen.");
            alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
                @Override
                public void accept(ButtonType buttonType) {
                    if (buttonType == ButtonType.OK){
                        automatInstanceManager.getAutomat().deleteKuchen(kuchen.getFachnummer());
                        updateProperties();
                    }
                }
            });
        }
    }

    private InsertHerstellerPopup insertHerstellerPopup = new InsertHerstellerPopup();
    public void insertHersteller() throws Exception {
        insertHerstellerPopup.display(automatInstanceManager.getAutomat());
        updateProperties();
    }

    public void deleteHersteller(){
        HerstellerImpl hersteller = herstellerTableView.getSelectionModel().getSelectedItem();
        if (hersteller == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Kein Hersteller ausgewählt!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Bestätigung");
            alert.setHeaderText("Möchten Sie den Hersteller entfernen? ");
            alert.setContentText("Klicken Sie OK zu bestätigen.");
            alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
                @Override
                public void accept(ButtonType buttonType) {
                    if (buttonType == ButtonType.OK){
                        automatInstanceManager.getAutomat().removeHersteller(hersteller.getName());
                        updateProperties();
                    }
                }
            });
        }
    }

    private boolean showVorhandenAllergen = true;
    public void switchAllergen(){
        showVorhandenAllergen = !showVorhandenAllergen;
        updateProperties();
    }

    public void saveWithJOS(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Mit JOS Automatenstatus speichern");
        fileChooser.setInitialFileName("status");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Ser Files", "*.ser"));
        File statusFile = fileChooser.showSaveDialog(herstellerTableView.getScene().getWindow());
        if (statusFile == null){
            return;
        }

        automatInstanceManager.setSaveStrategy(new SaveWithJOS());
        automatInstanceManager.getAutomat().save(statusFile.getName());
    }

    public void saveWithJBP(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Mit JBP Automatenstatus speichern");
        fileChooser.setInitialFileName("status");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
        File statusFile = fileChooser.showSaveDialog(herstellerTableView.getScene().getWindow());

        if (statusFile == null){
            return;
        }

        automatInstanceManager.setSaveStrategy(new SaveWithJBP());
        automatInstanceManager.getAutomat().save(statusFile.getName());
    }

    public void loadWithJOS(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Mit JOS Automatenstatus laden");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Ser Files", "*.ser"));
        File statusFile = fileChooser.showOpenDialog(herstellerTableView.getScene().getWindow());
        if (statusFile == null){
            return;
        }

        automatInstanceManager.setLoadStrategy(new LoadWithJOS());
        automatInstanceManager.loadAutomat(statusFile.getName());
        updateProperties();
    }

    public void loadWithJBP(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Mit JBP Automatenstatus laden");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
        File statusFile = fileChooser.showOpenDialog(herstellerTableView.getScene().getWindow());
        if (statusFile == null){
            return;
        }

        automatInstanceManager.setLoadStrategy(new LoadWithJBP());
        automatInstanceManager.loadAutomat(statusFile.getName());
        updateProperties();
    }

    private void updateProperties(){
        herstellerTableView.getItems().clear();
        for (HerstellerImpl hersteller : automatInstanceManager.getAutomat().readAllHersteller()) {
            herstellerTableView.getItems().add(hersteller);
        }

        HashMap<HerstellerImpl, Integer> herstellerKuchenanzahlHashMap = new HashMap<>();
        for (HerstellerImpl hersteller : automatInstanceManager.getAutomat().readAllHersteller()){
            int kuchenNum = 0;
            for (KuchenImpl kuchen : automatInstanceManager.getAutomat().readAllKuchen()){
                if (hersteller == kuchen.getHersteller()){
                    kuchenNum++;
                }
            }
            herstellerKuchenanzahlHashMap.put(hersteller, kuchenNum);
        }
        herstellerKuchenanzahlMapProperty.setValue(FXCollections.observableMap(herstellerKuchenanzahlHashMap));

        kuchenTableView.getItems().clear();
        kuchenListProperty.setValue(FXCollections.observableList(automatInstanceManager.getAutomat().readAllKuchen()));
        for (KuchenImpl kuchen : kuchenListProperty) {
            kuchenTableView.getItems().add(kuchen);
        }

        HashMap<KuchenImpl, String> kuchenHerstellerNameHashMap = new HashMap<>();
        for (KuchenImpl kuchen : automatInstanceManager.getAutomat().readAllKuchen()){
            kuchenHerstellerNameHashMap.put(kuchen, kuchen.getHersteller().getName());
        }
        kuchenHerstellerNameMapProperty.setValue(FXCollections.observableMap(kuchenHerstellerNameHashMap));

        allergenTableView.getItems().clear();
        if (showVorhandenAllergen){
            allergenLabel.setText("Vorhandene Allergene");
            allergenSetProperty.setValue(FXCollections.observableSet(automatInstanceManager.getAutomat().getVorhandeneAllergene()));
        } else {
            allergenLabel.setText("Nicht vorhandene Allergene");
            allergenSetProperty.setValue(FXCollections.observableSet(automatInstanceManager.getAutomat().getNichtVorhandeneAllergene()));
        }
        for (Allergen allergen : allergenSetProperty) {
            allergenTableView.getItems().add(allergen);
        }
    }
}