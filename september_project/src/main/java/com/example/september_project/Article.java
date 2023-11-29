package com.example.september_project;

import java.sql.*;
import java.util.ArrayList;

public class Article extends Login{

    private int id;
    private String name;
    private String description;
    private float price;
    private Manufacturer manufacturer;
    Manufacturer manRW = new Manufacturer();

    public Article(int id, String name, String description, float price, Manufacturer manufacturer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public Article() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void writeArticle(Article ar){

        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = DriverManager.getConnection(getLogin());

            String sql = "insert into article values (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, ar.getId());
            stmt.setString(2, ar.getName());
            stmt.setString(3, ar.getDescription());
            stmt.setFloat(4, ar.getPrice());
            stmt.setInt(5, ar.getManufacturer().getId());


            stmt.executeUpdate();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                stmt.close();
                conn.close();
            }
            catch (Exception ex1){
                System.out.println(ex1.getMessage());
            }
        }
    }

    public int getMaxID(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(getLogin());
            String sql = "select max(articleID) from article";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int high = rs.getInt("max(articleID)");
                return high + 1;
            }

        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        finally {
            try{
                rs.close();
                stmt.close();
                rs.close();
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return 0;
    }

    public ArrayList<String> readAllArticles(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<String> allEmp = new ArrayList<>();

        try{
            conn = DriverManager.getConnection(getLogin());
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select articleName from article");

            while(rs.next()){
                allEmp.add(rs.getString("articleName"));
            }
            return allEmp;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        finally {
            try{
                rs.close();
                stmt.close();
                conn.close();
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }

    public Article readArticleByFullName(String name){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = DriverManager.getConnection(getLogin());

            String query = "select articleID, articleName, description, price, manufacturerID from article " +
                    "where articleName = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            rs = stmt.executeQuery();

            while(rs.next()){
                int new_id = rs.getInt("articleID");
                String first = rs.getString("articleName");
                String last = rs.getString("description");
                Float number = rs.getFloat("price");
                int manu = rs.getInt("manufacturerID");
                Article ar = new Article(new_id,first,last,number,manRW.readManufacturerByID(manu));
                return ar;
            }


        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        finally {
            try{
                rs.close();
                stmt.close();
                conn.close();
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }


    public void deleteArticle(Article art){

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(getLogin());
            String sql = "delete from article where articleID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,art.getId());
            stmt.executeUpdate();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                stmt.close();
                conn.close();
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }


    public void updateArticle(Article art){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(getLogin());
            String sql = "update article set articleName=? , description=?, price=?, manufacturerID = ? where articleID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,art.getName());
            stmt.setString(2,art.getDescription());
            stmt.setFloat(3,art.getPrice());
            stmt.setInt(4,art.getManufacturer().getId());
            stmt.setInt(5,art.getId());

            stmt.executeUpdate();

        }
        catch (Exception ex){
            System.out.println(ex.getMessage());

        }
        finally {
            try {
                stmt.close();
                conn.close();
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    public Article readArticleByID(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = DriverManager.getConnection(getLogin());

            String query = "select articleID, articleName, description, price, manufacturerID from article " +
                    "where articleID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while(rs.next()){
                int new_id = rs.getInt("articleID");
                String first = rs.getString("articleName");
                String last = rs.getString("description");
                Float number = rs.getFloat("price");
                int manu = rs.getInt("manufacturerID");
                Article ar = new Article(new_id,first,last,number,manRW.readManufacturerByID(manu));
                return ar;
            }


        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        finally {
            try{
                rs.close();
                stmt.close();
                conn.close();
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }





}
