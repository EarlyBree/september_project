package com.example.september_project;

import java.sql.*;
import java.util.ArrayList;

public class Employee extends Login{

    private int id;
    private String firstName;
    private String lastName;
    private String employeeNumber;
    private String street;
    private String city;

    public Employee(int id, String firstName, String lastName, String employeeNumber, String street, String city) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeNumber = employeeNumber;
        this.street = street;
        this.city = city;
    }

    public Employee(){}

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public ArrayList<String> readAllEmployees(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<String> allEmp = new ArrayList<>();

        try{
            conn = DriverManager.getConnection(getLogin());
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select firstName, lastName from employee");

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

    public Employee readEmployeeByFullName(String firstName, String lastName){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = DriverManager.getConnection(getLogin());

            String query = "select employeeID, firstName, lastName, employeeNumber, street, city from employee " +
                    "where firstName=? and lastName=?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1,firstName);
            stmt.setString(2,lastName);
            rs = stmt.executeQuery();

            while(rs.next()){
                int new_id = rs.getInt("employeeID");
                String first = rs.getString("firstName");
                String last = rs.getString("lastName");
                String number = rs.getString("employeeNumber");
                String str = rs.getString("street");
                String cit = rs.getString("city");
                Employee em = new Employee(new_id,first,last,number,str,cit);
                return em;
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

    public void writeEmployee(Employee emp){

        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = DriverManager.getConnection(getLogin());

            String sql = "insert into employee values (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1,emp.getId());
            stmt.setString(2,emp.getFirstName());
            stmt.setString(3,emp.getLastName());
            stmt.setString(4,emp.getEmployeeNumber());
            stmt.setString(5,emp.getStreet());
            stmt.setString(6,emp.getCity());

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

    public void updateEmployee(Employee emp){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(getLogin());
            String sql = "update employee set firstName=? , lastName=?, employeeNumber=?, street=?, " +
                    "city=? where employeeID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,emp.getFirstName());
            stmt.setString(2,emp.getLastName());
            stmt.setString(3,emp.getEmployeeNumber());
            stmt.setString(4,emp.getStreet());
            stmt.setString(5,emp.getCity());
            stmt.setInt(6,emp.getId());

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

    public void deleteEmployee(Employee emp){

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(getLogin());
            String sql = "delete from employee where employeeID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,emp.getId());
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

    public int getMaxID(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(getLogin());
            String sql = "select max(employeeID) from employee";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int high = rs.getInt("max(employeeID)");
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

    public Employee readEmployeeByID(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = DriverManager.getConnection(getLogin());

            String query = "select employeeID, firstName, lastName, employeeNumber, street, city from employee " +
                    "where employeeID=?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();

            while(rs.next()){
                int new_id = rs.getInt("employeeID");
                String first = rs.getString("firstName");
                String last = rs.getString("lastName");
                String number = rs.getString("employeeNumber");
                String str = rs.getString("street");
                String cit = rs.getString("city");
                Employee em = new Employee(new_id,first,last,number,str,cit);
                return em;
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


    public int readEmployeeGetIDByName(String firstName, String lastName){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = DriverManager.getConnection(getLogin());

            String query = "select employeeID from employee " +
                    "where firstName=? and lastName=?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1,firstName);
            stmt.setString(2,lastName);
            rs = stmt.executeQuery();

            while(rs.next()){
                int new_id = rs.getInt("employeeID");
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
