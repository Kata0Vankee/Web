/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.database;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
  
// This class can be used to initialize the database connection 
public class DatabaseConnection { 
    public static Connection initializeDatabase() 
        throws SQLException, ClassNotFoundException 
    { 
        // Initialize all the information regarding 
        // Database Connection 
        String dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
        String dbURL = "jdbc:sqlserver://localhost:1433"; 
        // Database name to access 

        String dbUsername = "sa"; 
        String dbPassword = "123"; 
  
        Class.forName(dbDriver); 
        Connection con = DriverManager.getConnection(dbURL, 
                                                     dbUsername,  
                                                     dbPassword); 
        return con; 
    } 
} 
