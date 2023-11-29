package com.example.september_project;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class Shop_view {

        Customer cusRW = new Customer();
        Employee empRW = new Employee();
        Article artRW = new Article();
        Shopsale ssRW = new Shopsale();
        Onlinesale osRW = new Onlinesale();

        TextField ss_id = new TextField();
        ComboBox customer = new ComboBox();
        ComboBox employee = new ComboBox();
        ComboBox article = new ComboBox();
        ComboBox quantity = new ComboBox();
        TextField price = new TextField();

        TextField ss_id_e = new TextField();
        ComboBox customer_e = new ComboBox();
        ComboBox payment = new ComboBox();
        ComboBox delivery = new ComboBox();
        ComboBox article_e = new ComboBox();
        ComboBox quantity_e = new ComboBox();
        TextField price_e = new TextField();

        DatePicker date = new DatePicker();
        DatePicker date_e = new DatePicker();

    public VBox show_view(){
        VBox v1 = new VBox();
        VBox shop_box = new VBox();
        VBox online_box = new VBox();

        Label shop_sale_lbl = new Label("Shop sale:");
        Label online_sale_lbl = new Label("Internet sale:");
        Label ss_id_lbl = new Label("Shopsale ID:");
        Label customer_lbl = new Label("Customer: ");
        Label employee_lbl = new Label("Employee: ");
        Label article_lbl = new Label("Article: ");
        Label quantity_lbl = new Label("Quantity: ");
        Label price_lbl = new Label("Price: ");
        Label date_lbl = new Label("Date: ");
        Label comm = new Label();

        Label ss_id_lbl_e = new Label("Onlinesale ID:");
        Label customer_lbl_e = new Label("Customer: ");
        Label payment_lbl = new Label("Payment: ");
        Label delivery_lbl = new Label("Delivery: ");
        Label article_lbl_e = new Label("Article: ");
        Label quantity_lbl_e = new Label("Quantity: ");
        Label price_lbl_e = new Label("Price: ");
        Label date_lbl_e = new Label("Date: ");
        Label comm_e = new Label();

        Button calc = new Button("Calculate Price");
        Button save = new Button("Save transaction");
        Button print = new Button("Print receipt");
        Button calc_e = new Button("Calculate Price");
        Button save_e = new Button("Save transaction");


        HBox shopsale_id_box = new HBox(ss_id_lbl, ss_id);
        HBox customer_box = new HBox(customer_lbl, customer);
        HBox employee_box = new HBox(employee_lbl, employee);
        HBox article_box = new HBox(article_lbl, article);
        HBox quantity_box = new HBox(quantity_lbl, quantity);
        HBox price_box = new HBox(price_lbl, price);
        HBox date_box = new HBox(date_lbl, date);
        HBox button_box = new HBox(calc, save, print);

        HBox ss_id_box = new HBox(ss_id_lbl_e, ss_id_e);
        HBox customer_box_e = new HBox(customer_lbl_e, customer_e);
        HBox payment_box = new HBox(payment_lbl, payment);
        HBox delivery_box = new HBox(delivery_lbl, delivery);
        HBox article_box_e = new HBox(article_lbl_e, article_e);
        HBox quantity_box_e = new HBox(quantity_lbl_e, quantity_e);
        HBox price_box_e = new HBox(price_lbl_e, price_e);
        HBox button_box_e = new HBox(calc_e, save_e);
        HBox date_box_e = new HBox(date_lbl_e, date_e);

        shop_box.getChildren().addAll(shop_sale_lbl, shopsale_id_box, customer_box, employee_box, article_box, quantity_box, price_box, date_box, button_box, comm);
        online_box.getChildren().addAll(online_sale_lbl, ss_id_box, customer_box_e, payment_box, delivery_box, article_box_e, quantity_box_e, price_box_e, date_box_e, button_box_e, comm_e);
        v1.getChildren().addAll(shop_box, online_box);

        shop_sale_lbl.setStyle("-fx-font: 24 arial;");
        online_sale_lbl.setStyle("-fx-font: 24 arial;");
        shop_box.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 3;" +
                "-fx-border-insets: 15;" +
                "-fx-border-radius: 10;" +
                "-fx-border-color: black;");
        online_box.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 3;" +
                "-fx-border-insets: 15;" +
                "-fx-border-radius: 10;" +
                "-fx-border-color: black;");

        ss_id.setEditable(false);
        shop_sale_lbl.setPadding(new Insets(5, 0, 15, 0));
        ss_id_lbl.setPadding(new Insets(5, 17, 0, 0));
        ss_id_lbl_e.setPadding(new Insets(5, 8, 0, 0));
        customer_lbl.setPadding(new Insets(5, 23, 0, 0));
        employee_lbl.setPadding(new Insets(5, 22, 0, 0));
        article_lbl.setPadding(new Insets(5, 40, 0, 0));
        quantity_lbl.setPadding(new Insets(5, 28, 0, 0));
        price_lbl.setPadding(new Insets(5, 48, 0, 0));
        date_lbl.setPadding(new Insets(5, 49, 0, 0));
        date_lbl_e.setPadding(new Insets(5, 49, 0, 0));
        online_sale_lbl.setPadding(new Insets(5, 0, 15, 0));
        customer_lbl_e.setPadding(new Insets(5, 23, 0, 0));
        payment_lbl.setPadding(new Insets(5, 28, 0, 0));
        delivery_lbl.setPadding(new Insets(5, 32, 0, 0));
        article_lbl_e.setPadding(new Insets(5, 40, 0, 0));
        quantity_lbl_e.setPadding(new Insets(5, 28, 0, 0));
        price_lbl_e.setPadding(new Insets(5, 48, 0, 0));

        date.setValue(LocalDate.now());
        date_e.setValue(LocalDate.now());
        ss_id.setText(Integer.toString(ssRW.getMaxID()));
        ss_id_e.setText(Integer.toString(osRW.getMaxID()));
        price.setText(Integer.toString(0));
        price_e.setText(Integer.toString(0));
        customer.getItems().clear();
        payment.getItems().addAll("Credit card", "Paypal");
        delivery.getItems().addAll("Normal", "Express");
        for(int i = 0; i < cusRW.readAllCustomers().size(); i++){
            customer.getItems().add(cusRW.readAllCustomers().get(i));
        }
        employee.getItems().clear();
        for(int i = 0; i < empRW.readAllEmployees().size(); i++){
            employee.getItems().add(empRW.readAllEmployees().get(i));
        }
        article.getItems().clear();
        for(int i = 0; i < artRW.readAllArticles().size(); i++){
            article.getItems().add(artRW.readAllArticles().get(i));
        }
        customer_e.getItems().clear();
        for(int i = 0; i < cusRW.readAllCustomers().size(); i++){
            customer_e.getItems().add(cusRW.readAllCustomers().get(i));
        }
        article_e.getItems().clear();
        for(int i = 0; i < artRW.readAllArticles().size(); i++){
            article_e.getItems().add(artRW.readAllArticles().get(i));
        }
        quantity.getItems().clear();
        for(int i= 1; i < 11; i++){
            quantity.getItems().add(i);
        }
        for(int i= 1; i < 11; i++){
            quantity_e.getItems().add(i);
        }
        customer.getSelectionModel().select(0);
        employee.getSelectionModel().select(0);
        article.getSelectionModel().select(0);
        quantity.getSelectionModel().select(0);
        customer_e.getSelectionModel().select(0);
        article_e.getSelectionModel().select(0);
        quantity_e.getSelectionModel().select(0);
        payment.getSelectionModel().select(0);
        delivery.getSelectionModel().select(0);

        calc.setOnAction(actionEvent -> {
            Shopsale ss = new Shopsale(Integer.parseInt(ss_id.getCharacters().toString()),
                    cusRW.readCustomerByFullName(customer.getValue().toString().split("\\s+")[0],
                    customer.getValue().toString().split("\\s+")[1]),
                    empRW.readEmployeeByFullName(employee.getValue().toString().split("\\s+")[0],
                    employee.getValue().toString().split("\\s+")[1]),
                    artRW.readArticleByFullName(article.getValue().toString()),
                    Integer.parseInt(quantity.getValue().toString()),
                    Float.parseFloat(price.getCharacters().toString()),
                    date.getValue());
            price.setText(Float.toString(ssRW.calculateShopSale(ss).getPrice()));
        });
        save.setOnAction(actionEvent -> {
            ssRW.writeShopSale(new Shopsale(Integer.parseInt(ss_id.getCharacters().toString()),
                    cusRW.readCustomerByFullName(customer.getValue().toString().split("\\s+")[0],
                            customer.getValue().toString().split("\\s+")[1]),
                    empRW.readEmployeeByFullName(employee.getValue().toString().split("\\s+")[0],
                            employee.getValue().toString().split("\\s+")[1]),
                    artRW.readArticleByFullName(article.getValue().toString()),
                    Integer.parseInt(quantity.getValue().toString()),
                    Float.parseFloat(price.getCharacters().toString()), date.getValue()));
        comm.setText("Transaction has been saved.");
        });

        print.setOnAction(actionEvent -> {
            ssRW.printReceipt(new Shopsale(Integer.parseInt(ss_id.getCharacters().toString()),
                    cusRW.readCustomerByFullName(customer.getValue().toString().split("\\s+")[0],
                            customer.getValue().toString().split("\\s+")[1]),
                    empRW.readEmployeeByFullName(employee.getValue().toString().split("\\s+")[0],
                            employee.getValue().toString().split("\\s+")[1]),
                    artRW.readArticleByFullName(article.getValue().toString()),
                    Integer.parseInt(quantity.getValue().toString()),
                    Float.parseFloat(price.getCharacters().toString()),
                    date.getValue()));
        comm.setText("Bill has been printed.");
        });

        calc_e.setOnAction(actionEvent -> {
            Onlinesale os = new Onlinesale(Integer.parseInt(ss_id_e.getCharacters().toString()),
                    cusRW.readCustomerByFullName(customer_e.getValue().toString().split("\\s+")[0],
                            customer_e.getValue().toString().split("\\s+")[1]),
                    payment.getValue().toString(),delivery.getValue().toString(),
                    artRW.readArticleByFullName(article_e.getValue().toString()),
                    Integer.parseInt(quantity_e.getValue().toString()),
                    Float.parseFloat(price_e.getCharacters().toString()), date_e.getValue());
            price_e.setText(Float.toString(osRW.calculateOnlineSale(os).getPrice()));
        });

        save_e.setOnAction(actionEvent -> {
            osRW.writeOnlineSale(new Onlinesale(Integer.parseInt(ss_id_e.getCharacters().toString()),
                    cusRW.readCustomerByFullName(customer_e.getValue().toString().split("\\s+")[0],
                            customer_e.getValue().toString().split("\\s+")[1]),
                    payment.getValue().toString(),delivery.getValue().toString(),
                    artRW.readArticleByFullName(article_e.getValue().toString()),
                    Integer.parseInt(quantity_e.getValue().toString()),
                    Float.parseFloat(price_e.getCharacters().toString()), date_e.getValue()));
        comm_e.setText("Transaction has been saved.");
        });



        return v1;
    }


}
