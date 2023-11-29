package com.example.september_project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class Shopsale extends Login{

    Customer cusRW = new Customer();
    Article artRW = new Article();
    Employee empRW = new Employee();

    private int id;
    private Customer customer;
    private Employee employee;
    private Article article;
    private int quantity;
    private float price;
    private LocalDate date;

    public Shopsale(){}

    public Shopsale(int id, Customer customer, Employee employee, Article article, int quantity, float price, LocalDate date) {
        this.id = id;
        this.customer = customer;
        this.employee = employee;
        this.article = article;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Article getArticle() {
        return article;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public Shopsale calculateShopSale(Shopsale s1){
        float newPrice;
        if(s1.getCustomer().getBonus()){
            if(s1.getArticle().getPrice()*s1.getQuantity() > 100){
                newPrice = s1.getArticle().getPrice()*s1.getQuantity() * 85 / 100;
            }
            else{
                newPrice = s1.getArticle().getPrice()*s1.getQuantity() * 90 / 100;
            }
        }
        else {
            if(s1.getArticle().getPrice()*s1.getQuantity() > 100){
                newPrice = s1.getArticle().getPrice()*s1.getQuantity() * 95 / 100;

            }
            else{
                newPrice = s1.getArticle().getPrice()*s1.getQuantity();
            }
        }
        BigDecimal bd = new BigDecimal(newPrice).setScale(2, RoundingMode.HALF_UP);
        newPrice = bd.floatValue();
        s1.setPrice(newPrice);
        return s1;
    }

    public void writeShopSale(Shopsale ss){

        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = DriverManager.getConnection(getLogin());

            String sql = "insert into shopsale values (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, ss.getId());
            stmt.setInt(2, ss.getCustomer().getId());
            stmt.setInt(3, ss.getEmployee().getId());
            stmt.setInt(4, ss.getArticle().getId());
            stmt.setInt(5, ss.getQuantity());
            stmt.setFloat(6, ss.getPrice());
            stmt.setDate(7, java.sql.Date.valueOf(ss.getDate()));

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
            String sql = "select max(shopsaleID) from shopsale";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int high = rs.getInt("max(shopsaleID)");
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

    public void printReceipt(Shopsale ss){
        try{
            FileWriter writer = new FileWriter("receipt.txt", false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write("Receipt ID: " + ss.getId());
            bufferedWriter.newLine();
            bufferedWriter.write("Customer:  " + ss.getCustomer().getFirstName() + " " + ss.getCustomer().getLastName());
            bufferedWriter.newLine();
            bufferedWriter.write("Employee: " + ss.getEmployee().getFirstName() + " " + ss.getEmployee().getLastName());
            bufferedWriter.newLine();
            bufferedWriter.write("Article: " + ss.getArticle().getName());
            bufferedWriter.newLine();
            bufferedWriter.write("Quantity: " + ss.getQuantity());
            bufferedWriter.newLine();
            bufferedWriter.write("Price: " + ss.getPrice());
            bufferedWriter.newLine();
            bufferedWriter.write("Date: " + ss.getDate());
            bufferedWriter.newLine();

            bufferedWriter.close();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    public ArrayList<Shopsale> searchEmployee(int employee, LocalDate start, LocalDate end){

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Shopsale> list = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(getLogin());
            String sql = "select * from shopsale where employeeID = ? and (date between ? and ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1,employee);
            stmt.setDate(2, java.sql.Date.valueOf(start));
            stmt.setDate(3, java.sql.Date.valueOf(end));


            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("shopsaleID");
                int cus = rs.getInt("customerID");
                int emp = rs.getInt("employeeID");
                int art = rs.getInt("articleID");
                int qua = rs.getInt("quantity");
                float price = rs.getFloat("price");
                LocalDate date = rs.getDate("date").toLocalDate();
                Shopsale s1 = new Shopsale(id,cusRW.readCustomerByID(cus),empRW.readEmployeeByID(emp),
                        artRW.readArticleByID(art),qua,price,date);
                list.add(s1);
            }
            return list;
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



        return null;
    }



    public ArrayList<Shopsale> searchCustomer(int customer, LocalDate start, LocalDate end){

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Shopsale> list = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(getLogin());
            String sql = "select * from shopsale where customerID = ? and (date between ? and ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1,customer);
            stmt.setDate(2, java.sql.Date.valueOf(start));
            stmt.setDate(3, java.sql.Date.valueOf(end));


            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("shopsaleID");
                int cus = rs.getInt("customerID");
                int emp = rs.getInt("employeeID");
                int art = rs.getInt("articleID");
                int qua = rs.getInt("quantity");
                float price = rs.getFloat("price");
                LocalDate date = rs.getDate("date").toLocalDate();
                Shopsale s1 = new Shopsale(id,cusRW.readCustomerByID(cus),empRW.readEmployeeByID(emp),
                        artRW.readArticleByID(art),qua,price,date);
                list.add(s1);
            }
            return list;
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



        return null;
    }



}
