package gui.popup;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import gl.Automat;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class InsertHerstellerPopup {
    public void display(Automat automat) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("insertHerstellerRoot.fxml"));
        Parent root = loader.load();
        InsertHerstellerController controller = loader.getController();
        controller.initData(automat);
        Stage insertHerstellerStage = new Stage();
        insertHerstellerStage.setTitle("Hersteller einfügen");
        insertHerstellerStage.setScene(new Scene(root));
        insertHerstellerStage.showAndWait();
    }
}
