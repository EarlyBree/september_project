package com.example.september_project;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class Onlinesale extends Login{


    Customer cusRW = new Customer();
    Article artRW = new Article();
    Employee empRW = new Employee();


    private int id;
    private Customer customer;
    private String payment;
    private String delivery;
    private Article article;
    private int quantity;
    private float price;
    private LocalDate date;

    public Onlinesale(){}

    public Onlinesale(int id, Customer customer, String payment, String delivery, Article article, int quantity, float price, LocalDate date) {
        this.id = id;
        this.customer = customer;
        this.payment = payment;
        this.delivery = delivery;
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

    public String getPayment() {
        return payment;
    }

    public String getDelivery() {
        return delivery;
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

    public LocalDate getDate() {
        return date;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void writeOnlineSale(Onlinesale os){

        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = DriverManager.getConnection(getLogin());

            String sql = "insert into onlinesale values (?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, os.getId());
            stmt.setInt(2, os.getCustomer().getId());
            stmt.setString(3, os.getPayment());
            stmt.setString(4, os.getDelivery());
            stmt.setInt(5,os.getArticle().getId());
            stmt.setInt(6, os.getQuantity());
            stmt.setFloat(7, os.getPrice());
            stmt.setDate(8, java.sql.Date.valueOf(os.getDate()));

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

    public Onlinesale calculateOnlineSale(Onlinesale s1){
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

    public int getMaxID(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(getLogin());
            String sql = "select max(onlinesaleID) from onlinesale";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int high = rs.getInt("max(onlinesaleID)");
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


    public ArrayList<Onlinesale> searchCustomer(int customer, LocalDate start, LocalDate end){

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Onlinesale> list = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(getLogin());
            String sql = "select * from onlinesale where customerID = ? and (date between ? and ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1,customer);
            stmt.setDate(2, java.sql.Date.valueOf(start));
            stmt.setDate(3, java.sql.Date.valueOf(end));


            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("onlinesaleID");
                int cus = rs.getInt("customerID");
                String pay = rs.getString("payment");
                String del = rs.getString("delivery");
                int art = rs.getInt("articleId");
                int qua = rs.getInt("quantity");
                float price = rs.getFloat("price");
                LocalDate date = rs.getDate("date").toLocalDate();
                Onlinesale o1 = new Onlinesale(id,cusRW.readCustomerByID(cus),pay,
                        del,artRW.readArticleByID(art),qua,price,date);
                list.add(o1);
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
