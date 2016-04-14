/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 *
 * @author Roman
 */
public class ConnectToDB {
    
    Statement statement;
    Connection conn;
    URL url = null;
    
    public ConnectToDB()
    {
        try {
            // Step 1: "Load" the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Step 2: Establish the connection to the database 
            String url = "jdbc:mysql://2-stamm.ch:3306/roman";
            
            conn = DriverManager.getConnection(url, "roman", "Go4Roman_");
            System.out.println("Connected!");
        } catch (Exception e) {
            System.err.println("D'oh! Got an exception!");
            System.err.println(e.getMessage());
        }
    }
    
    public void registerUser(String uname, String pword)
    {
        try{
        statement = conn.createStatement();
        String updateString = "INSERT INTO Account VALUES (NULL, '" + uname + "', '"+pword+"')";
        statement.executeUpdate(updateString);
        System.out.println("Register Successful");
        }
        catch(Exception e)
        {
            System.err.println("Insert User not successful");
            System.err.println(e.getMessage());
        }
        
    }
    
    public String userLogin(String uname)
    {
        ResultSet rs = null;
        try {
            statement = conn.createStatement();
            String updateString = "SELECT Passwort FROM Account WHERE Benutzername = '" + uname + "'";
            System.out.println(updateString);
            rs = statement.executeQuery(updateString);
            if (rs != null) {
                System.out.println("Password successfully received");
                rs.next();
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.err.println("Password not received");
            System.err.println(e.getMessage());
        }
    
        return "failure";
    }
}
