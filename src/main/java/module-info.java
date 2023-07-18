module application {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens application to javafx.fxml;
    opens gui to javafx.fxml;
    opens model.entities to javafx.base;


    exports application;
}
