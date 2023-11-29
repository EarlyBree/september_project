package com.example.september_project;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class Scan_view {

        Customer cusRW = new Customer();
        Employee empRW = new Employee();
        Shopsale ssRW = new Shopsale();
        Onlinesale osRW = new Onlinesale();

        ComboBox customer = new ComboBox();
        ComboBox employee = new ComboBox();
        DatePicker date_start = new DatePicker();
        DatePicker date_end = new DatePicker();
        DatePicker date_start_e = new DatePicker();
        DatePicker date_end_e = new DatePicker();
        TextArea cus_fld = new TextArea();
        TextArea emp_fld = new TextArea();

    public VBox scan_view(){
        VBox v1 = new VBox();
        VBox customer_box = new VBox();
        VBox employee_box = new VBox();

        Label customer_head_lbl = new Label("Search for Customers");
        Label customer_select_lbl = new Label("Select customer: ");
        Label date_start_lbl = new Label("Select a start date: ");
        Label date_end_lbl = new Label("Select an end date: ");

        Label employee_head_lbl = new Label("Search for Employee");
        Label emloyee_select_lbl = new Label("Select employee: ");
        Label date_start_lbl_e = new Label("Select a start date: ");
        Label date_end_lbl_e = new Label("Select an end date: ");

        Button search = new Button("Search for selection");
        Button search_e = new Button("Search for selection");


        HBox customer_select_box = new HBox(customer_select_lbl, customer);
        HBox date_start_box = new HBox(date_start_lbl, date_start);
        HBox date_end_box = new HBox(date_end_lbl, date_end);
        HBox employee_select_box_e = new HBox(emloyee_select_lbl, employee);
        HBox date_start_box_e = new HBox(date_start_lbl_e, date_start_e);
        HBox date_end_box_e = new HBox(date_end_lbl_e, date_end_e);


        customer_box.getChildren().addAll(customer_head_lbl, customer_select_box, date_start_box, date_end_box, search, cus_fld);
        employee_box.getChildren().addAll(employee_head_lbl, employee_select_box_e, date_start_box_e, date_end_box_e, search_e, emp_fld);
        v1.getChildren().addAll(customer_box, employee_box);

        employee_head_lbl.setStyle("-fx-font: 24 arial;");
        customer_head_lbl.setStyle("-fx-font: 24 arial;");
        customer_box.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 3;" +
                "-fx-border-insets: 15;" +
                "-fx-border-radius: 10;" +
                "-fx-border-color: black;");
        employee_box.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 3;" +
                "-fx-border-insets: 15;" +
                "-fx-border-radius: 10;" +
                "-fx-border-color: black;");
        customer_select_lbl.setPadding(new Insets(5, 22, 0, 0));
        date_start_lbl.setPadding(new Insets(5, 12, 0, 0));
        date_end_lbl.setPadding(new Insets(5, 9, 0, 0));
        emloyee_select_lbl.setPadding(new Insets(5, 19, 0, 0));
        date_start_lbl_e.setPadding(new Insets(5, 12, 0, 0));
        date_end_lbl_e.setPadding(new Insets(5, 9, 0, 0));


        date_start.setValue(LocalDate.now());
        date_end.setValue(LocalDate.now());
        date_start_e.setValue(LocalDate.now());
        date_end_e.setValue(LocalDate.now());
        employee.getItems().clear();
        for(int i = 0; i < empRW.readAllEmployees().size(); i++){
            employee.getItems().add(empRW.readAllEmployees().get(i));
        }

        customer.getItems().clear();
        for(int i = 0; i < cusRW.readAllCustomers().size(); i++){
            customer.getItems().add(cusRW.readAllCustomers().get(i));
        }
        customer.getSelectionModel().select(0);
        employee.getSelectionModel().select(0);

        search_e.setOnAction(actionEvent -> {
            emp_fld.setText("");
            for(int i = 0; i < ssRW.searchEmployee(empRW.readEmployeeGetIDByName(employee.getValue().toString().split("\\s+")[0],
                            employee.getValue().toString().split("\\s+")[1]),
                date_start_e.getValue(),date_end_e.getValue()).size(); i++){
                emp_fld.appendText(ssRW.searchEmployee(empRW.readEmployeeGetIDByName(employee.getValue().toString().split("\\s+")[0],
                            employee.getValue().toString().split("\\s+")[1]),
                            date_start_e.getValue(),date_end_e.getValue()).get(i).getArticle().getName() + "\n");
            }
        });



        search.setOnAction(actionEvent -> {
            cus_fld.setText("");
            for(int i = 0; i < ssRW.searchCustomer(cusRW.readCustomerGetIDByName(customer.getValue().toString().split("\\s+")[0],
                            customer.getValue().toString().split("\\s+")[1]),
                            date_start.getValue(),date_end.getValue()).size(); i++){
                cus_fld.appendText(ssRW.searchCustomer(cusRW.readCustomerGetIDByName(customer.getValue().toString().split("\\s+")[0],
                            customer.getValue().toString().split("\\s+")[1]),
                            date_start.getValue(),date_end.getValue()).get(i).getArticle().getName() + " bought in shop.\n");
            }
            for(int i = 0; i < osRW.searchCustomer(cusRW.readCustomerGetIDByName(customer.getValue().toString().split("\\s+")[0],
                            customer.getValue().toString().split("\\s+")[1]),
                            date_start.getValue(),date_end.getValue()).size(); i++){
                cus_fld.appendText(osRW.searchCustomer(cusRW.readCustomerGetIDByName(customer.getValue().toString().split("\\s+")[0],
                            customer.getValue().toString().split("\\s+")[1]),
                            date_start.getValue(),date_end.getValue()).get(i).getArticle().getName() + " bought online.\n");
            }

        });





        return v1;
    }


}
