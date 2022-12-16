/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author vothu
 */
public class User {

  protected int id;
  protected String username;
  protected String password;
  protected String fullname;
  protected String host;
  protected int port;
  protected Date dateCreated;

  public User() {}

  public User(
    int id,
    String username,
    String password,
    String fullname,
    String host,
    int port,
    Date dateCreated
  ) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.fullname = fullname;
    this.host = host;
    this.port = port;
    this.dateCreated = dateCreated;
  }

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getFullname() {
    return fullname;
  }

  public String getHost() {
    return host;
  }

  public int getPort() {
    return port;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }
}
