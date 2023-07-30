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
public class InsertKuchenPopup {
    public void display(Automat automat) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("insertKuchenRoot.fxml"));
        Parent root = loader.load();
        InsertKuchenController controller = loader.getController();
        controller.initData(automat);
        Stage insertKuchenStage = new Stage();
        insertKuchenStage.setTitle("Kuchen einfügen");
        insertKuchenStage.setScene(new Scene(root));
        insertKuchenStage.showAndWait();
    }
}
