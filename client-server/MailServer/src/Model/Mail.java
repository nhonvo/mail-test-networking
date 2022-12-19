/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author vothu
 */
public class Mail {

  protected String title;
  protected String content;
  protected Date dateCreated;
  protected int sender;
  protected int receiver;

  public Mail() {}

  public Mail(
    String title,
    String content,
    Date dateCreated,
    int sender,
    int receiver
  ) {
    this.title = title;
    this.content = content;
    this.dateCreated = dateCreated;
    this.sender = sender;
    this.receiver = receiver;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public int getSender() {
    return sender;
  }

  public int getReceiver() {
    return receiver;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public void setSender(int sender) {
    this.sender = sender;
  }

  public void setReceiver(int receiver) {
    this.receiver = receiver;
  }
}
