/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coa;
import java.sql.*;
/**
 *
 * @author Navjot kaur
 */
public class ConnectionDB {
    private Connection con;
    
    public ConnectionDB() throws ClassNotFoundException, SQLException
    {
      // adding the url of the driver along with the useername and password of the MySql(Connector/Jdricer)
        String url = "jdbc:mysql://localhost:3306/coadb";
        String uname = "root";
        String pass = "";
        
        // loading and registering the jdbc driver using Class.forName(....)
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // getting the connection using getConnection method of DriverManager because getConnection is an interface and we cannot use it directly
        con = DriverManager.getConnection(url,uname,pass);
    }
    Connection getConnection()
    {
        return con;
    }
}
