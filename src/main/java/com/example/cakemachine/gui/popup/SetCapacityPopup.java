package com.example.cakemachine.gui.popup;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.gl.AutomatInstanceManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class SetCapacityPopup {
    public void display(AutomatInstanceManager automatInstanceManager) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./setCapacityRoot.fxml"));
        Parent root = loader.load();
        SetCapacityController controller = loader.getController();
        controller.initData(automatInstanceManager);
        Stage insertHerstellerStage = new Stage();
        insertHerstellerStage.setTitle("Kapazität bestimmen");
        insertHerstellerStage.setScene(new Scene(root));
        insertHerstellerStage.showAndWait();
    }
}
