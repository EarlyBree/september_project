module com.example.september_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.september_project to javafx.fxml;
    exports com.example.september_project;
}