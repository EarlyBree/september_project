package com.example.september_project;

import java.sql.*;
import java.util.ArrayList;

public class Manufacturer extends Login{

    private int id;
    private String name;
    private String street;
    private String city;

    public Manufacturer(int id, String name, String street, String city) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.city = city;
    }

    public Manufacturer(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public void writeManufacturer(Manufacturer ma){

        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = DriverManager.getConnection(getLogin());

            String sql = "insert into manufacturer values (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, ma.getId());
            stmt.setString(2, ma.getName());
            stmt.setString(3, ma.getStreet());
            stmt.setString(4, ma.getCity());


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
            String sql = "select max(manufacturerID) from manufacturer";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int high = rs.getInt("max(manufacturerID)");
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

    public ArrayList<String> readAllManufacturers(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<String> allEmp = new ArrayList<>();

        try{
            conn = DriverManager.getConnection(getLogin());
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select manufacturerName from manufacturer");

            while(rs.next()){
                allEmp.add(rs.getString("manufacturerName"));
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

    public Manufacturer readManufacturerByFullName(String name){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = DriverManager.getConnection(getLogin());

            String query = "select manufacturerID, manufacturerName, street, city from manufacturer " +
                    "where manufacturerName = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            rs = stmt.executeQuery();

            while(rs.next()){
                int new_id = rs.getInt("manufacturerID");
                String first = rs.getString("manufacturerName");
                String last = rs.getString("street");
                String number = rs.getString("city");
                Manufacturer ma = new Manufacturer(new_id,first,last,number);
                return ma;
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

    public void deleteManufacturer(Manufacturer man){

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(getLogin());
            String sql = "delete from manufacturer where manufacturerID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,man.getId());
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

    public void updateManufacturer(Manufacturer man){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(getLogin());
            String sql = "update manufacturer set manufacturerName=? , street=?, city=? " +
                    "where manufacturerID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, man.getName());
            stmt.setString(2, man.getStreet());
            stmt.setString(3, man.getCity());
            stmt.setInt(4, man.getId());

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

    public Manufacturer readManufacturerByID(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = DriverManager.getConnection(getLogin());

            String query = "select manufacturerID, manufacturerName, street, city from manufacturer " +
                    "where manufacturerID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while(rs.next()){
                int new_id = rs.getInt("manufacturerID");
                String first = rs.getString("manufacturerName");
                String last = rs.getString("street");
                String number = rs.getString("city");
                Manufacturer ma = new Manufacturer(new_id,first,last,number);
                return ma;
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
