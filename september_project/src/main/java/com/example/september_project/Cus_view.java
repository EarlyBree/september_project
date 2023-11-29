package com.example.september_project;

import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Cus_view {

        Customer cusRW = new Customer();

        TextField c_id = new TextField();
        TextField firstName= new TextField();
        TextField lastName = new TextField();
        TextField cusNumber = new TextField();
        ComboBox bonus = new ComboBox();
        TextField street = new TextField();
        TextField city = new TextField();

        TextField c_id_e = new TextField();
        TextField firstName_e = new TextField();
        TextField lastName_e = new TextField();
        TextField cusNumber_e = new TextField();
        ComboBox bonus_e = new ComboBox();
        TextField street_e = new TextField();
        TextField city_e = new TextField();
        ComboBox allCus = new ComboBox();



    public VBox customer_view(){
        VBox v1 = new VBox();
        VBox add_box = new VBox();
        VBox edit_box = new VBox();

        Label customer_lbl = new Label("Add customer: ");
        Label c_id_lbl = new Label("Customer ID: ");
        Label firstName_lbl = new Label("First Name: ");
        Label lastName_lbl = new Label("Last Name: ");
        Label cusNumber_lbl = new Label("Customer Number: ");
        Label bonus_lbl = new Label("Bonus Customer: ");
        Label street_lbl = new Label("Street: ");
        Label city_lbl = new Label("City: ");

        Label customer_lbl_e = new Label("Edit customer: ");
        Label c_id_lbl_e = new Label("Customer ID: ");
        Label firstName_lbl_e = new Label("First Name: ");
        Label lastName_lbl_e = new Label("Last Name: ");
        Label cusNumber_lbl_e = new Label("Customer Number: ");
        Label bonus_lbl_e = new Label("Bonus Customer: ");
        Label street_lbl_e = new Label("Street: ");
        Label city_lbl_e = new Label("City: ");

        Button add_btn = new Button("Add customer");
        Button find_btn = new Button("Get customer info");
        Button del_btn = new Button("Delete customer");
        Button edit_btn = new Button("Edit customer");

        HBox c_id_box = new HBox(c_id_lbl, c_id);
        HBox firstName_box = new HBox(firstName_lbl, firstName);
        HBox lastName_box = new HBox(lastName_lbl, lastName);
        HBox cusNumber_box = new HBox(cusNumber_lbl, cusNumber);
        HBox bonus_box = new HBox(bonus_lbl, bonus);
        HBox street_box = new HBox(street_lbl, street);
        HBox city_box = new HBox(city_lbl, city);

        HBox getEmp_box = new HBox(allCus, find_btn);
        HBox c_id_box_e = new HBox(c_id_lbl_e, c_id_e);
        HBox firstName_box_e = new HBox(firstName_lbl_e, firstName_e);
        HBox lastName_box_e = new HBox(lastName_lbl_e, lastName_e);
        HBox cusNumber_box_e = new HBox(cusNumber_lbl_e, cusNumber_e);
        HBox bonus_box_e = new HBox(bonus_lbl_e, bonus_e);
        HBox street_box_e = new HBox(street_lbl_e, street_e);
        HBox city_box_e = new HBox(city_lbl_e, city_e);
        HBox btn_box = new HBox(del_btn, edit_btn);

        add_box.getChildren().addAll(customer_lbl, c_id_box, firstName_box, lastName_box, cusNumber_box, bonus_box,
                street_box, city_box, add_btn);
        edit_box.getChildren().addAll(customer_lbl_e, getEmp_box, c_id_box_e, firstName_box_e, lastName_box_e, cusNumber_box_e, bonus_box_e,
                street_box_e, city_box_e, btn_box);

        v1.getChildren().addAll(add_box, edit_box);

        bonus.getItems().clear();
        bonus_e.getItems().clear();
        find_btn.setDisable(true);
        c_id.setEditable(false);
        c_id_e.setEditable(false);
        customer_lbl.setStyle("-fx-font: 24 arial;");
        customer_lbl_e.setStyle("-fx-font: 24 arial;");
        add_box.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 3;" +
                "-fx-border-insets: 15;" +
                "-fx-border-radius: 10;" +
                "-fx-border-color: black;");
        edit_box.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 3;" +
                "-fx-border-insets: 15;" +
                "-fx-border-radius: 10;" +
                "-fx-border-color: black;");
        customer_lbl.setPadding(new Insets(5, 0, 15, 0));
        c_id_lbl.setPadding(new Insets(5, 49, 0, 0));
        firstName_lbl.setPadding(new Insets(5, 58, 0, 0));
        lastName_lbl.setPadding(new Insets(5, 60, 0, 0));
        cusNumber_lbl.setPadding(new Insets(5, 17, 0, 0));
        bonus_lbl.setPadding(new Insets(5, 30, 0, 0));
        street_lbl.setPadding(new Insets(5, 85, 0, 0));
        city_lbl.setPadding(new Insets(5, 97, 10, 0));
        customer_lbl_e.setPadding(new Insets(5, 0, 10, 0));
        c_id_lbl_e.setPadding(new Insets(5, 49, 0, 0));
        firstName_lbl_e.setPadding(new Insets(5, 58, 0, 0));
        lastName_lbl_e.setPadding(new Insets(5, 60, 0, 0));
        cusNumber_lbl_e.setPadding(new Insets(5, 17, 0, 0));
        bonus_lbl_e.setPadding(new Insets(5, 30, 0, 0));
        street_lbl_e.setPadding(new Insets(5, 85, 0, 0));
        city_lbl_e.setPadding(new Insets(5, 97, 10, 0));
        getEmp_box.setPadding(new Insets(5, 0, 10, 0));

        c_id.setText(Integer.toString(cusRW.getMaxID()));
        bonus.getItems().addAll("Yes", "No");
        bonus.getSelectionModel().select(1);
        bonus_e.getItems().addAll("Yes", "No");
        allCus.getItems().clear();
        for(int i = 0; i < cusRW.readAllCustomers().size(); i++){
            allCus.getItems().add(cusRW.readAllCustomers().get(i));
        }

        BooleanBinding bb = c_id.textProperty().isEmpty().or(firstName.textProperty().isEmpty().
                or(lastName.textProperty().isEmpty().or(cusNumber.textProperty().isEmpty().
                        or(street.textProperty().isEmpty().or(city.textProperty().isEmpty())))));
        add_btn.disableProperty().bind(bb);

        BooleanBinding bb1 = c_id_e.textProperty().isEmpty().or(firstName_e.textProperty().isEmpty().
                or(lastName_e.textProperty().isEmpty().or(cusNumber_e.textProperty().isEmpty().
                        or(street_e.textProperty().isEmpty().or(city_e.textProperty().isEmpty())))));
        del_btn.disableProperty().bind(bb1);
        edit_btn.disableProperty().bind(bb1);

        add_btn.setOnAction(actionEvent -> {
            int id = Integer.parseInt(c_id.getCharacters().toString());
            String first = firstName.getCharacters().toString();
            String last = lastName.getCharacters().toString();
            String number = cusNumber.getCharacters().toString();
            Object yesno = bonus.getSelectionModel().getSelectedItem().toString();
            Boolean yes = false;
            if(yesno == "Yes") yes=true;
            String str = street.getCharacters().toString();
            String cit = city.getCharacters().toString();
            Customer c1 = new Customer(id,first,last,number,yes,str,cit);
            cusRW.writeCustomer(c1);
            clearCus();
        });


        allCus.valueProperty().addListener((observableValue, o, t1) -> {
            if(t1 != null){
                find_btn.setDisable(false);
            }
        });

        find_btn.setOnAction(actionEvent -> {
            Customer em = cusRW.readCustomerByFullName(allCus.getValue().toString().split("\\s+")[0],
                    allCus.getValue().toString().split("\\s+")[1]);
            c_id_e.setText(Integer.toString(em.getId()));
            firstName_e.setText(em.getFirstName());
            lastName_e.setText(em.getLastName());
            cusNumber_e.setText(em.getCustomerNumber());
            int i = 0;
            if(em.getBonus() == true){
                i = 0;
            }
            else if(em.getBonus() == false){
                i = 1;
            }
            bonus_e.getSelectionModel().select(i);
            street_e.setText(em.getStreet());
            city_e.setText(em.getCity());
        });


        del_btn.setOnAction(actionEvent -> {
            Object yesno = bonus_e.getSelectionModel().getSelectedItem().toString();
            Boolean yes = false;
            if(yesno == "Yes") yes=true;
            Customer cu = new Customer(Integer.parseInt(c_id_e.getCharacters().toString()),
                    firstName_e.getCharacters().toString(), lastName_e.getCharacters().toString(),
                    cusNumber_e.getCharacters().toString(), yes, street_e.getCharacters().toString(),
                    city_e.getCharacters().toString());
            cusRW.deleteCustomer(cu);
            clearCus();
        });

        edit_btn.setOnAction(actionEvent -> {
            Object yesno = bonus_e.getSelectionModel().getSelectedItem().toString();
            Boolean yes = false;
            if(yesno == "Yes") yes=true;
            Customer cu = new Customer(Integer.parseInt(c_id_e.getCharacters().toString()),
                    firstName_e.getCharacters().toString(), lastName_e.getCharacters().toString(),
                    cusNumber_e.getCharacters().toString(), yes, street_e.getCharacters().toString(),
                    city_e.getCharacters().toString());
            cusRW.updateCustomer(cu);
            clearCus();
        });

        return v1;
    }

    public void clearCus(){
        c_id.clear();
        firstName.clear();
        lastName.clear();
        cusNumber.clear();
        street.clear();
        city.clear();
        c_id_e.clear();
        firstName_e.clear();
        lastName_e.clear();
        cusNumber_e.clear();
        street_e.clear();
        city_e.clear();
        bonus.getSelectionModel().select(1);
        allCus.getItems().clear();
        c_id.setText(Integer.toString(cusRW.getMaxID()));
        for(int i = 0; i < cusRW.readAllCustomers().size(); i++){
            allCus.getItems().add(cusRW.readAllCustomers().get(i));
        }
    }



}
