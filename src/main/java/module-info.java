module com.example.cakemachine {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.cakemachine to javafx.fxml, javafx.graphics;
    exports com.example.cakemachine;
    opens com.example.cakemachine.gui to javafx.fxml, javafx.graphics;
    exports com.example.cakemachine.gui;
    opens com.example.cakemachine.gui.popup to javafx.fxml, javafx.graphics;
    exports com.example.cakemachine.gui.popup;
}

//module com.example.cakemachine {
//        requires javafx.controls;
//        requires javafx.fxml;
//        requires java.desktop;
//
//
//        opens com.example.cakemachine to javafx.fxml;
//        exports com.example.cakemachine;
//}