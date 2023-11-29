package com.example.september_project;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Emp_view{

        Employee empRW = new Employee();

        TextField e_id = new TextField();
        TextField firstName= new TextField();
        TextField lastName = new TextField();
        TextField empNumber = new TextField();
        TextField street = new TextField();
        TextField city = new TextField();

        TextField e_id_e = new TextField();
        TextField firstName_e = new TextField();
        TextField lastName_e = new TextField();
        TextField empNumber_e = new TextField();
        TextField street_e = new TextField();
        TextField city_e = new TextField();
        ComboBox allEmp = new ComboBox();


    public VBox employee_view(){

        VBox v1 = new VBox();
        VBox add_box = new VBox();
        VBox edit_box = new VBox();
        Label employee_lbl = new Label("Add employee: ");
        Label e_id_lbl = new Label("Employee ID: ");
        Label firstName_lbl = new Label("First Name: ");
        Label lastName_lbl = new Label("Last Name: ");
        Label empNumber_lbl = new Label("Employee Number: ");
        Label street_lbl = new Label("Street: ");
        Label city_lbl = new Label("City: ");

        Label employee_lbl_e = new Label("Edit employee: ");
        Label e_id_lbl_e = new Label("Employee ID: ");
        Label firstName_lbl_e = new Label("First Name: ");
        Label lastName_lbl_e = new Label("Last Name: ");
        Label empNumber_lbl_e = new Label("Employee Number: ");
        Label street_lbl_e = new Label("Street: ");
        Label city_lbl_e = new Label("City: ");

        Button add_btn = new Button("Add employee");
        Button find_btn = new Button("Get employee info");
        Button del_btn = new Button("Delete employee");
        Button edit_btn = new Button("Edit employee");

        HBox e_id_box = new HBox(e_id_lbl, e_id);
        HBox firstName_box = new HBox(firstName_lbl, firstName);
        HBox lastName_box = new HBox(lastName_lbl, lastName);
        HBox empNumber_box = new HBox(empNumber_lbl, empNumber);
        HBox street_box = new HBox(street_lbl, street);
        HBox city_box = new HBox(city_lbl, city);

        HBox getEmp_box = new HBox(allEmp, find_btn);
        HBox e_id_box_e = new HBox(e_id_lbl_e, e_id_e);
        HBox firstName_box_e = new HBox(firstName_lbl_e, firstName_e);
        HBox lastName_box_e = new HBox(lastName_lbl_e, lastName_e);
        HBox empNumber_box_e = new HBox(empNumber_lbl_e, empNumber_e);
        HBox street_box_e = new HBox(street_lbl_e, street_e);
        HBox city_box_e = new HBox(city_lbl_e, city_e);
        HBox btn_box = new HBox(del_btn, edit_btn);

        e_id.setEditable(false);
        e_id_e.setEditable(false);
        find_btn.setDisable(true);
        employee_lbl.setStyle("-fx-font: 24 arial;");
        employee_lbl_e.setStyle("-fx-font: 24 arial;");
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
        employee_lbl.setPadding(new Insets(5, 0, 15, 0));
        e_id_lbl.setPadding(new Insets(5, 49, 0, 0));
        firstName_lbl.setPadding(new Insets(5, 58, 0, 0));
        lastName_lbl.setPadding(new Insets(5, 60, 0, 0));
        empNumber_lbl.setPadding(new Insets(5, 17, 0, 0));
        street_lbl.setPadding(new Insets(5, 85, 0, 0));
        city_lbl.setPadding(new Insets(5, 97, 10, 0));
        employee_lbl_e.setPadding(new Insets(5, 0, 10, 0));
        e_id_lbl_e.setPadding(new Insets(5, 49, 0, 0));
        firstName_lbl_e.setPadding(new Insets(5, 58, 0, 0));
        lastName_lbl_e.setPadding(new Insets(5, 60, 0, 0));
        empNumber_lbl_e.setPadding(new Insets(5, 17, 0, 0));
        street_lbl_e.setPadding(new Insets(5, 85, 0, 0));
        city_lbl_e.setPadding(new Insets(5, 97, 10, 0));
        getEmp_box.setPadding(new Insets(5, 0, 10, 0));

        add_box.getChildren().addAll(employee_lbl, e_id_box, firstName_box, lastName_box, empNumber_box,
                street_box, city_box, add_btn);
        edit_box.getChildren().addAll(employee_lbl_e, getEmp_box, e_id_box_e,firstName_box_e, lastName_box_e, empNumber_box_e,
                street_box_e, city_box_e, btn_box);
        v1.getChildren().addAll(add_box, edit_box);

//        e_id.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (!newValue.matches("\\d*")) {
//                e_id.setText(newValue.replaceAll("[^\\d]", ""));
//            }
//        });

        BooleanBinding bb = e_id.textProperty().isEmpty().or(firstName.textProperty().isEmpty().
                or(lastName.textProperty().isEmpty().or(empNumber.textProperty().isEmpty().
                or(street.textProperty().isEmpty().or(city.textProperty().isEmpty())))));
        add_btn.disableProperty().bind(bb);
        BooleanBinding bb1 = e_id_e.textProperty().isEmpty().or(firstName_e.textProperty().isEmpty().
                or(lastName_e.textProperty().isEmpty().or(empNumber_e.textProperty().isEmpty().
                        or(street_e.textProperty().isEmpty().or(city_e.textProperty().isEmpty())))));
        del_btn.disableProperty().bind(bb1);
        edit_btn.disableProperty().bind(bb1);

        e_id.setText(Integer.toString(empRW.getMaxID()));
        allEmp.getItems().clear();
        for(int i = 0; i < empRW.readAllEmployees().size(); i++){
            allEmp.getItems().add(empRW.readAllEmployees().get(i));
        }
        find_btn.setOnAction(actionEvent -> {
            Employee em = empRW.readEmployeeByFullName(allEmp.getValue().toString().split("\\s+")[0],
                    allEmp.getValue().toString().split("\\s+")[1]);
            e_id_e.setText(Integer.toString(em.getId()));
            firstName_e.setText(em.getFirstName());
            lastName_e.setText(em.getLastName());
            empNumber_e.setText(em.getEmployeeNumber());
            street_e.setText(em.getStreet());
            city_e.setText(em.getCity());
        });
        edit_btn.setOnAction(actionEvent -> {
            Employee em = new Employee(Integer.parseInt(e_id_e.getCharacters().toString()),
                    firstName_e.getCharacters().toString(), lastName_e.getCharacters().toString(),
                    empNumber_e.getCharacters().toString(),street_e.getCharacters().toString(),
                    city_e.getCharacters().toString());
            empRW.updateEmployee(em);
            clearEmp();
        });
        del_btn.setOnAction(actionEvent -> {
            Employee em = new Employee(Integer.parseInt(e_id_e.getCharacters().toString()),
                    firstName_e.getCharacters().toString(), lastName_e.getCharacters().toString(),
                    empNumber_e.getCharacters().toString(),street_e.getCharacters().toString(),
                    city_e.getCharacters().toString());
            empRW.deleteEmployee(em);
            clearEmp();
        });

        add_btn.setOnAction(actionEvent -> {
            int id = Integer.parseInt(e_id.getCharacters().toString());
            String first = firstName.getCharacters().toString();
            String last = lastName.getCharacters().toString();
            String number = empNumber.getCharacters().toString();
            String str = street.getCharacters().toString();
            String cit = city.getCharacters().toString();
            Employee e1 = new Employee(id, first,last,number,str,cit);
            empRW.writeEmployee(e1);
            clearEmp();
        });

//        allEmp.valueProperty().addListener(new ChangeListener() {
//            @Override
//            public void changed(ObservableValue observableValue, Object o, Object t1) {
//                Employee em = empRW.readEmployeeByFullName(allEmp.getValue().toString().split("\\s+")[0],
//                        allEmp.getValue().toString().split("\\s+")[1]);
//                e_id_e.setText(Integer.toString(em.getId()));
//                firstName_e.setText(em.getFirstName());
//                lastName_e.setText(em.getLastName());
//                empNumber_e.setText(em.getEmployeeNumber());
//                street_e.setText(em.getStreet());
//                city_e.setText(em.getCity());
//            }
//        });
        allEmp.valueProperty().addListener((observableValue, o, t1) -> {
            if(t1 != null){
                find_btn.setDisable(false);
            }
        });


        return v1;
    }


    public void clearEmp(){
        e_id.clear();
        firstName.clear();
        lastName.clear();
        empNumber.clear();
        street.clear();
        city.clear();
        e_id_e.clear();
        firstName_e.clear();
        lastName_e.clear();
        empNumber_e.clear();
        street_e.clear();
        city_e.clear();
        allEmp.getItems().clear();
        e_id.setText(Integer.toString(empRW.getMaxID()));
        for(int i = 0; i < empRW.readAllEmployees().size(); i++){
            allEmp.getItems().add(empRW.readAllEmployees().get(i));
        }
    }
}
