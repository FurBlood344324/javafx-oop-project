module com.example.bagistakipsistemi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.bagistakipsistemi to javafx.fxml;
    exports com.example.bagistakipsistemi;
    exports com.example.bagistakipsistemi.Classes;
    opens com.example.bagistakipsistemi.Classes to javafx.fxml;
    exports com.example.bagistakipsistemi.Controllers;
    opens com.example.bagistakipsistemi.Controllers to javafx.fxml;
}