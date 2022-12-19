/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author phamd
 */
public class Request {
    private String requestHeader;
    private int quantity;

    private LoginModel user;
    private MailModel mail;
    public Request(String requestHeader, LoginModel user) {
        this.requestHeader = requestHeader;
        this.user = user;
    }

    public Request(String requestHeader, int quantity) {
        this.requestHeader = requestHeader;
        this.quantity = quantity;
    }

    public Request(String requestHeader, MailModel mail) {
        this.requestHeader = requestHeader;
        this.mail = mail;
    }

    public Request(String requestHeader, int quantity, LoginModel user, MailModel mail) {
        this.requestHeader = requestHeader;
        this.quantity = quantity;
        this.user = user;
        this.mail = mail;
    }
    
    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LoginModel getUser() {
        return user;
    }

    public void setUser(LoginModel user) {
        this.user = user;
    }

    public MailModel getMail() {
        return mail;
    }

    public void setMail(MailModel mail) {
        this.mail = mail;
    }
    
    
    
}
