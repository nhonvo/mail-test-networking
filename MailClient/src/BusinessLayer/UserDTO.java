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
      frmMain main = new frmMain();
      main.show();
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
      user.setUsername(rs.getString("username"));
      user.setPassword(rs.getString("password"));
      user.setFullname(rs.getString("fullname"));
      user.setHost(rs.getString("host"));
      user.setPort(rs.getInt("port"));
      user.setDateCreated(rs.getDate("datecreated"));
    }
    return user;
  }
}
