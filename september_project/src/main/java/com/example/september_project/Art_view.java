package com.example.september_project;

import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Art_view {

        Manufacturer manRW = new Manufacturer();
        Article artRW = new Article();

        TextField a_id = new TextField();
        TextField name = new TextField();
        TextField description = new TextField();
        TextField price = new TextField();
        ComboBox manufacturer = new ComboBox();

        TextField a_id_e = new TextField();
        TextField name_e = new TextField();
        TextField description_e = new TextField();
        TextField price_e = new TextField();
        ComboBox manufacturer_e = new ComboBox();

        ComboBox allArt = new ComboBox();

    public VBox article_view(){
        VBox v1 = new VBox();
        VBox add_box = new VBox();
        VBox edit_box = new VBox();

        Label art_lbl = new Label("Add article:");
        Label a_id_lbl = new Label("Article ID: ");
        Label name_lbl = new Label("Article Name: ");
        Label description_lbl = new Label("Description: ");
        Label price_lbl = new Label("Price: ");
        Label manufacturer_lbl = new Label("Manufacturer: ");

        Label art_lbl_e = new Label("Edit article:");
        Label a_id_lbl_e = new Label("Article ID: ");
        Label name_lbl_e = new Label("Article Name: ");
        Label description_lbl_e = new Label("Description: ");
        Label price_lbl_e = new Label("Price: ");
        Label manufacturer_lbl_e = new Label("Manufacturer: ");

        Button add_btn = new Button("Add article");
        Button find_btn = new Button("Get article info");
        Button del_btn = new Button("Delete article");
        Button edit_btn = new Button("Edit article");

        HBox a_id_box = new HBox(a_id_lbl, a_id);
        HBox name_box = new HBox(name_lbl, name);
        HBox description_box = new HBox(description_lbl, description);
        HBox price_box = new HBox(price_lbl, price);
        HBox manufacturer_box = new HBox(manufacturer_lbl, manufacturer);

        HBox a_id_box_e = new HBox(a_id_lbl_e, a_id_e);
        HBox name_box_e = new HBox(name_lbl_e, name_e);
        HBox description_box_e = new HBox(description_lbl_e, description_e);
        HBox price_box_e = new HBox(price_lbl_e, price_e);
        HBox manufacturer_box_e = new HBox(manufacturer_lbl_e, manufacturer_e);
        HBox btn_box = new HBox(del_btn, edit_btn);
        HBox find_box = new HBox(allArt, find_btn);

        add_box.getChildren().addAll(art_lbl, a_id_box, name_box, description_box, price_box, manufacturer_box, add_btn);
        edit_box.getChildren().addAll(art_lbl_e, find_box, a_id_box_e, name_box_e, description_box_e, price_box_e, manufacturer_box_e, btn_box);
        v1.getChildren().addAll(add_box, edit_box);

        a_id.setEditable(false);
        a_id_e.setEditable(false);
        find_btn.setDisable(true);
        art_lbl.setStyle("-fx-font: 24 arial;");
        art_lbl_e.setStyle("-fx-font: 24 arial;");
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
        art_lbl.setPadding(new Insets(5, 0, 15, 0));
        a_id_lbl.setPadding(new Insets(5, 25, 0, 0));
        name_lbl.setPadding(new Insets(5, 5, 0, 0));
        description_lbl.setPadding(new Insets(5, 14, 0, 0));
        price_lbl.setPadding(new Insets(5, 49, 0, 0));
        manufacturer_lbl.setPadding(new Insets(5, 3, 0, 0));
        art_lbl_e.setPadding(new Insets(5, 0, 7, 0));
        a_id_lbl_e.setPadding(new Insets(5, 25, 0, 0));
        name_lbl_e.setPadding(new Insets(5, 5, 0, 0));
        description_lbl_e.setPadding(new Insets(5, 14, 0, 0));
        price_lbl_e.setPadding(new Insets(5, 49, 0, 0));
        manufacturer_lbl_e.setPadding(new Insets(5, 3, 10, 0));
        find_box.setPadding(new Insets(5, 0, 10, 0));


        a_id.setText(Integer.toString(artRW.getMaxID()));
        manufacturer.getItems().clear();
        for(int i = 0; i < manRW.readAllManufacturers().size(); i++){
            manufacturer.getItems().add(manRW.readAllManufacturers().get(i));
        }
        for(int i = 0; i < manRW.readAllManufacturers().size(); i++){
            manufacturer_e.getItems().add(manRW.readAllManufacturers().get(i));
        }
        manufacturer.getSelectionModel().select(0);
        BooleanBinding bb = a_id.textProperty().isEmpty().or(name.textProperty().isEmpty().
                or(description.textProperty().isEmpty().or(price.textProperty().isEmpty())));
        add_btn.disableProperty().bind(bb);
        BooleanBinding bb1 = a_id_e.textProperty().isEmpty().or(name_e.textProperty().isEmpty().
                or(description_e.textProperty().isEmpty().or(price_e.textProperty().isEmpty())));
        del_btn.disableProperty().bind(bb1);
        edit_btn.disableProperty().bind(bb1);
        allArt.getItems().clear();
        for(int i = 0; i < artRW.readAllArticles().size(); i++){
            allArt.getItems().add(artRW.readAllArticles().get(i));
        }



        add_btn.setOnAction(actionEvent -> {
            int id = Integer.parseInt(a_id.getCharacters().toString());
            String first = name.getCharacters().toString();
            String last = description.getCharacters().toString();
            float number = Float.parseFloat(price.getCharacters().toString());
            Manufacturer m1 = manRW.readManufacturerByFullName(manufacturer.getValue().toString());
            Article a1 = new Article(id,first,last,number,m1);
            artRW.writeArticle(a1);
            clearArt();
        });

        find_btn.setOnAction(actionEvent -> {
            Article ar = artRW.readArticleByFullName(allArt.getValue().toString());
            a_id_e.setText(Integer.toString(ar.getId()));
            name_e.setText(ar.getName());
            description_e.setText(ar.getDescription());
            price_e.setText(Float.toString(ar.getPrice()));
            manufacturer_e.getSelectionModel().select(ar.getManufacturer().getId()-1);

        });

        del_btn.setOnAction(actionEvent -> {
            Article ar = new Article(Integer.parseInt(a_id_e.getCharacters().toString()),
                    name_e.getCharacters().toString(), description_e.getCharacters().toString(),
                    Float.parseFloat(price_e.getCharacters().toString()),manRW.readManufacturerByFullName(manufacturer_e.getValue().toString()));
            artRW.deleteArticle(ar);
            clearArt();
        });

        edit_btn.setOnAction(actionEvent -> {
            Article ar = new Article(Integer.parseInt(a_id_e.getCharacters().toString()),
                    name_e.getCharacters().toString(), description_e.getCharacters().toString(),
                    Float.parseFloat(price_e.getCharacters().toString()),manRW.readManufacturerByFullName(manufacturer_e.getValue().toString()));
            artRW.updateArticle(ar);
            clearArt();
        });


        allArt.valueProperty().addListener((observableValue, o, t1) -> {
            if(t1 != null){
                find_btn.setDisable(false);
            }
        });

        return v1;
    }

    public void clearArt(){

        a_id.clear();
        name.clear();
        description.clear();
        price.clear();
        a_id_e.clear();
        name_e.clear();
        description_e.clear();
        price_e.clear();

        a_id.setText(Integer.toString(artRW.getMaxID()));
        manufacturer_e.getItems().clear();
        manufacturer.getItems().clear();
        for(int i = 0; i < manRW.readAllManufacturers().size(); i++){
            manufacturer.getItems().add(manRW.readAllManufacturers().get(i));
        }
        for(int i = 0; i < manRW.readAllManufacturers().size(); i++){
            manufacturer_e.getItems().add(manRW.readAllManufacturers().get(i));
        }
        allArt.getItems().clear();
        for(int i = 0; i < artRW.readAllArticles().size(); i++){
            allArt.getItems().add(artRW.readAllArticles().get(i));
        }
    }



}
