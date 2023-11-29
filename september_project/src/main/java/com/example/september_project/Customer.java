package com.example.september_project;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.*;
import java.util.ArrayList;

public class Customer extends Login{


    private int id;
    private String firstName;
    private String lastName;
    private String customerNumber;
    private Boolean bonus;
    private String street;
    private String city;

    public Customer(int id, String firstName, String lastName, String customerNumber, Boolean bonus, String street, String city) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerNumber = customerNumber;
        this.bonus = bonus;
        this.street = street;
        this.city = city;
    }

    public Customer(){}

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public Boolean getBonus() {
        return bonus;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public void writeCustomer(Customer cus){

        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = DriverManager.getConnection(getLogin());

            String sql = "insert into customer values (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, cus.getId());
            stmt.setString(2,cus.getFirstName());
            stmt.setString(3,cus.getLastName());
            stmt.setString(4,cus.getCustomerNumber());
            stmt.setBoolean(5,cus.getBonus());
            stmt.setString(6,cus.getStreet());
            stmt.setString(7,cus.getCity());

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
            String sql = "select max(customerID) from customer";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int high = rs.getInt("max(customerID)");
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

    public ArrayList<String> readAllCustomers(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<String> allEmp = new ArrayList<>();

        try{
            conn = DriverManager.getConnection(getLogin());
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select firstName, lastName from customer");

            while(rs.next()){
                String fullName = rs.getString("firstName") + " " + rs.getString("lastName");
                allEmp.add(fullName);
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

    public Customer readCustomerByFullName(String firstName, String lastName){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = DriverManager.getConnection(getLogin());

            String query = "select customerID, firstName, lastName, customerNumber, bonus, street, city from customer " +
                    "where firstName=? and lastName=?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1,firstName);
            stmt.setString(2,lastName);
            rs = stmt.executeQuery();

            while(rs.next()){
                int new_id = rs.getInt("customerID");
                String first = rs.getString("firstName");
                String last = rs.getString("lastName");
                String number = rs.getString("customerNumber");
                Boolean bonus = rs.getBoolean("bonus");
                String str = rs.getString("street");
                String cit = rs.getString("city");
                Customer cu = new Customer(new_id,first,last,number,bonus,str,cit);
                return cu;
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


    public void deleteCustomer(Customer cus){

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(getLogin());
            String sql = "delete from customer where customerID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,cus.getId());
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

    public void updateCustomer(Customer cus){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(getLogin());
            String sql = "update customer set firstName=? , lastName=?, customerNumber=?, bonus = ?, street=?, " +
                    "city=? where customerID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,cus.getFirstName());
            stmt.setString(2,cus.getLastName());
            stmt.setString(3,cus.getCustomerNumber());
            stmt.setBoolean(4,cus.getBonus());
            stmt.setString(5,cus.getStreet());
            stmt.setString(6,cus.getCity());
            stmt.setInt(7,cus.getId());

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


    public Customer readCustomerByID(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = DriverManager.getConnection(getLogin());

            String query = "select customerID, firstName, lastName, customerNumber, bonus, street, city from customer " +
                    "where customerID=?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();

            while(rs.next()){
                int new_id = rs.getInt("customerID");
                String first = rs.getString("firstName");
                String last = rs.getString("lastName");
                String number = rs.getString("customerNumber");
                Boolean bonus = rs.getBoolean("bonus");
                String str = rs.getString("street");
                String cit = rs.getString("city");
                Customer cu = new Customer(new_id,first,last,number,bonus,str,cit);
                return cu;
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


    public int readCustomerGetIDByName(String firstName, String lastName){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = DriverManager.getConnection(getLogin());

            String query = "select customerID from customer " +
                    "where firstName=? and lastName=?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1,firstName);
            stmt.setString(2,lastName);
            rs = stmt.executeQuery();

            while(rs.next()){
                int new_id = rs.getInt("customerID");
                return new_id;
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
        return 0;
    }


}
