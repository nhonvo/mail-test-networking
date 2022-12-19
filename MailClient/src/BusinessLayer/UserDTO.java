/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLayer;

import Application.frmMain;
import DataLayer.DBAccess;
import Model.User;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author vothu
 */
public class UserDTO {

  public void Login(String username, String password) throws Exception {
    DBAccess acc = new DBAccess();
    ResultSet rs = acc.Query(
      "select * from [user] where username = '" +
      username +
      "'and password ='" +
      password +
      "'"
    );
    if (rs.next()) {
      JOptionPane.showMessageDialog(null, "Login successfully!");
//      frmMain main = new frmMain();
//      main.setVisible(true);
    } else {
      JOptionPane.showMessageDialog(null, "Login failed!");
    }
  }

  public User GetUserByUserName(String username) throws Exception {
    DBAccess acc = new DBAccess();
    ResultSet rs = acc.Query(
      "select * from [user] where username = '" + username + "'"
    );
    User user = new User();
    if (rs.next()) {
      user.setId(rs.getInt("id"));
      user.setUsername(rs.getString("username"));
      user.setPassword(rs.getString("password"));
      user.setFullname(rs.getString("fullname"));
      user.setDateCreated(rs.getDate("datecreated"));
    }
    return user;
  }

  public boolean CheckUser(String username) {
    DBAccess acc = new DBAccess();
    ResultSet rs = acc.Query(
      "select * from [user] where username = '" + username + "'"
    );
    try {
      while (rs.next()) {
        return true;
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    }
    return false;
  }
  public User GetUserById(int id){
    DBAccess acc = new DBAccess();
    ResultSet rs = acc.Query(
      "select * from [user] where id = '" + id + "'"
    );
    User user = new User();
    try {
      while (rs.next()) {
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setFullname(rs.getString("fullname"));
        user.setDateCreated(rs.getDate("datecreated"));
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    }
    return user;
  }
}
