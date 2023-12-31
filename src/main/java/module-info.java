module application {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.sql;

    opens application to javafx.fxml;
    opens gui to javafx.fxml;
    opens model.entities to javafx.base;

    exports application;
}
