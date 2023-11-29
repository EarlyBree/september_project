package com.example.september_project;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class september extends Application {

        VBox view = new VBox();
        VBox root = new VBox();
    @Override
    public void start(Stage stage) throws IOException {


        Menu menu1 = new Menu("Menu");
        MenuBar menubar = new MenuBar();
        menubar.getMenus().addAll(menu1);
        MenuItem emp = new MenuItem("Employee");
        MenuItem cus = new MenuItem("Customer");
        MenuItem art = new MenuItem("Article");
        MenuItem manu = new MenuItem("Manufacturer");
        MenuItem saleshop = new MenuItem("Transactions");
        MenuItem scan = new MenuItem("Scan data");
        menu1.getItems().addAll(emp, cus, art, manu, saleshop, scan);






        Emp_view ev = new Emp_view();
        emp.setOnAction(actionEvent -> {view.getChildren().setAll(ev.employee_view());});
        Cus_view cv = new Cus_view();
        cus.setOnAction(actionEvent -> {view.getChildren().setAll(cv.customer_view());});
        Manu_view mv = new Manu_view();
        manu.setOnAction(actionEvent -> {view.getChildren().setAll(mv.manufacturer_view());});
        Art_view av = new Art_view();
        art.setOnAction(actionEvent -> {view.getChildren().setAll(av.article_view());});
        Shop_view sv = new Shop_view();
        saleshop.setOnAction(actionEvent -> {view.getChildren().setAll(sv.show_view());});
        Scan_view sv1 = new Scan_view();
        scan.setOnAction(actionEvent -> {view.getChildren().setAll(sv1.scan_view());});

        root.getChildren().addAll(menubar, view);
        Scene scene1 = new Scene(root,700,700);




        stage.setTitle("Shop");
        stage.setScene(scene1);
        stage.show();




    }

    public static void main(String[] args) {
        launch();
    }


}