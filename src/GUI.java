import gl.AutomatInstanceManager;
import gui.GUIController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        AutomatInstanceManager automatInstanceManager = AutomatInstanceManager.getInstance();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/root.fxml"));
        Parent root = loader.load();
        GUIController controller = loader.getController();
        controller.initData(automatInstanceManager);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Automat");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
