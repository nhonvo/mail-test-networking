/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
/**
 *
 * @author Administrator
 */
import java.sql.*;
import javax.swing.*;
public class MyConnection {
    public Connection getConnection(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://localhost:1433;Database=mailserver;user=truongnhon;password=123";
            Connection con = DriverManager.getConnection(URL);
            return con;
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString(),"Loi",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
