/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author chakri
 */


/**
 * This class is used to establish connection of current program to MySQL database
 * 
 * @author chakri
 */
public class dbase
{
    /**
     * this method is used for establishing connection to database
     */
public void dbase_connect()
    {
        try
        {
          Class.forName("com.mysql.jdbc.Driver");
          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunny","root","2113");
          System.out.println("Connected to datbase");                   
        }
        catch(SQLException e1)
        {
            System.out.println("SQLException something wrong with username or password or port number");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Class not found!! MYSQL connector J is not configured, add it to project library");
        } 
               
    }
}