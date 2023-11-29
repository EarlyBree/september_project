package com.example.september_project;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class Login {

    public Login(){getLogin();}

    public Properties readProperties(){
        try{
            FileReader reader = new FileReader("C:\\Users\\sabrina.mueller\\backup\\september_project\\src\\main\\java\\com\\example\\september_project\\login.properties");
            Properties p = new Properties();
            p.load(reader);
            return p;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public String getLogin(){
        String url = "jdbc:mysql://";
        Properties p = readProperties();

        url = url + p.getProperty("address") + "/" + p.getProperty("database") + "?user=" + p.getProperty("user") +
                "&password=" + p.getProperty("password");
        return url;
    }


}
