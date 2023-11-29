package com.example.september_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class september_main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(september.class.getResource("hello-view.fxml"));
        VBox root = new VBox();
        Scene scene1 = new Scene(root,600,600);
        stage.setTitle("Hello!");
        stage.setScene(scene1);
        stage.show();
        Connection conn = null;
    }

    public static void main(String[] args) {
        launch();
    }
}
