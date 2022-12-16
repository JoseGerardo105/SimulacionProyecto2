module stibride {
    requires javafx.controls;
    requires javafx.graphics;
    requires java.desktop;
    requires java.base;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;
    
    opens application.view to javafx.fxml, javafx.controls, org.controlsfx.controls;
    exports application.view;
    exports application.main;
    
    opens application.dto to javafx.base;


    opens application.jdbc to java.sql;
    exports application.jdbc;
}
