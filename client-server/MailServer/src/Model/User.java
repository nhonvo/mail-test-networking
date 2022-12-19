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

    protected String username;
    protected String password;
    protected String fullname;
    protected Date dateCreated;

    public User() {
    }

    public User(String username, String password, String fullname, String host, int port, Date dateCreated) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.dateCreated = dateCreated;
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

    public Date getDateCreated() {
        return dateCreated;
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
    
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

}
