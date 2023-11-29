package com.example.september_project;

import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Manu_view {

        Manufacturer manRW = new Manufacturer();

        TextField m_id = new TextField();
        TextField manuName = new TextField();
        TextField street = new TextField();
        TextField city = new TextField();

        TextField m_id_e = new TextField();
        TextField manuName_e = new TextField();
        TextField street_e = new TextField();
        TextField city_e = new TextField();
        ComboBox allMan = new ComboBox();


    public VBox manufacturer_view(){
        VBox v1 = new VBox();
        VBox add_box = new VBox();
        VBox edit_box = new VBox();

        Label add_manu_lbl = new Label("Add manufacturer:");
        Label m_id_lbl = new Label("Manufacturer ID: ");
        Label manu_lbl = new Label("Manufacturer Name: ");
        Label street_lbl = new Label("Street: ");
        Label city_lbl = new Label("City: ");

        Label edit_manu_lbl = new Label("Edit manufacturer:");
        Label m_id_lbl_e = new Label("Manufacturer ID: ");
        Label manu_lbl_e = new Label("Manufacturer Name: ");
        Label street_lbl_e = new Label("Street: ");
        Label city_lbl_e = new Label("City: ");

        Button add_btn = new Button("Add manufacturer");
        Button find_btn = new Button("Get manufacturer info");
        Button del_btn = new Button("Delete manufacturer");
        Button edit_btn = new Button("Edit manufacturer");

        HBox m_id_box = new HBox(m_id_lbl, m_id);
        HBox manu_box = new HBox(manu_lbl, manuName);
        HBox street_box = new HBox(street_lbl, street);
        HBox city_box = new HBox(city_lbl, city);

        HBox find_top_box = new HBox(allMan, find_btn);
        HBox m_id_box_e = new HBox(m_id_lbl_e, m_id_e);
        HBox manu_box_e = new HBox(manu_lbl_e, manuName_e);
        HBox street_box_e = new HBox(street_lbl_e, street_e);
        HBox city_box_e = new HBox(city_lbl_e, city_e);
        HBox btn_box = new HBox(del_btn, edit_btn);


        add_box.getChildren().addAll(add_manu_lbl, m_id_box, manu_box, street_box, city_box, add_btn);
        edit_box.getChildren().addAll(edit_manu_lbl, find_top_box, m_id_box_e, manu_box_e, street_box_e, city_box_e, btn_box);
        v1.getChildren().addAll(add_box, edit_box);

        m_id.setEditable(false);
        m_id_e.setEditable(false);
        find_btn.setDisable(true);
        add_manu_lbl.setStyle("-fx-font: 24 arial;");
        edit_manu_lbl.setStyle("-fx-font: 24 arial;");
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

        add_manu_lbl.setPadding(new Insets(5, 0, 7, 0));
        m_id_lbl.setPadding(new Insets(5, 29, 0, 0));
        manu_lbl.setPadding(new Insets(5, 8, 0, 0));
        street_lbl.setPadding(new Insets(5, 83, 0, 0));
        city_lbl.setPadding(new Insets(5, 94, 10, 0));
        edit_manu_lbl.setPadding(new Insets(5, 0, 5, 0));
        m_id_lbl_e.setPadding(new Insets(5, 29, 0, 0));
        manu_lbl_e.setPadding(new Insets(5, 8, 0, 0));
        street_lbl_e.setPadding(new Insets(5, 83, 0, 0));
        city_lbl_e.setPadding(new Insets(5, 94, 10, 0));
        find_top_box.setPadding(new Insets(5, 0, 5, 0));

        m_id.setText(Integer.toString(manRW.getMaxID()));
        allMan.getItems().clear();
        for(int i = 0; i < manRW.readAllManufacturers().size(); i++){
            allMan.getItems().add(manRW.readAllManufacturers().get(i));
        }

        BooleanBinding bb = m_id.textProperty().isEmpty().or(manuName.textProperty().isEmpty().
                or(street.textProperty().isEmpty().or(city.textProperty().isEmpty())));
        add_btn.disableProperty().bind(bb);
        BooleanBinding bb1 = m_id_e.textProperty().isEmpty().or(manuName_e.textProperty().isEmpty().
                or(street_e.textProperty().isEmpty().or(city_e.textProperty().isEmpty())));
        del_btn.disableProperty().bind(bb1);
        edit_btn.disableProperty().bind(bb1);

        find_btn.setOnAction(actionEvent -> {
            Manufacturer ma = manRW.readManufacturerByFullName(allMan.getValue().toString());
            m_id_e.setText(Integer.toString(ma.getId()));
            manuName_e.setText(ma.getName());
            street_e.setText(ma.getStreet());
            city_e.setText(ma.getCity());
        });


        add_btn.setOnAction(actionEvent -> {
            int id = Integer.parseInt(m_id.getCharacters().toString());
            String name = manuName.getCharacters().toString();
            String str = street.getCharacters().toString();
            String cit = city.getCharacters().toString();
            Manufacturer m1 = new Manufacturer(id,name,str,cit);
            manRW.writeManufacturer(m1);
            clearMan();
        });

        del_btn.setOnAction(actionEvent -> {
            Manufacturer ma = new Manufacturer(Integer.parseInt(m_id_e.getCharacters().toString()),
                    manuName_e.getCharacters().toString(), street_e.getCharacters().toString(),
                    city_e.getCharacters().toString());
            manRW.deleteManufacturer(ma);
            clearMan();
        });

        edit_btn.setOnAction(actionEvent -> {
            Manufacturer ma = new Manufacturer(Integer.parseInt(m_id_e.getCharacters().toString()),
                    manuName_e.getCharacters().toString(), street_e.getCharacters().toString(),
                    city_e.getCharacters().toString());
            manRW.updateManufacturer(ma);
            clearMan();
        });


        allMan.valueProperty().addListener((observableValue, o, t1) -> {
            if(t1 != null){
                find_btn.setDisable(false);
            }
        });

        return v1;
    }

    public void clearMan(){
        m_id.clear();
        manuName.clear();
        street.clear();
        city.clear();
        m_id_e.clear();
        manuName_e.clear();
        street_e.clear();
        city_e.clear();
        m_id.setText(Integer.toString(manRW.getMaxID()));
        allMan.getItems().clear();
        for(int i = 0; i < manRW.readAllManufacturers().size(); i++){
            allMan.getItems().add(manRW.readAllManufacturers().get(i));
        }
    }





}
