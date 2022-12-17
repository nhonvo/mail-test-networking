package BusinessLayer;

import java.util.ArrayList;

import Model.Mail;

/**
 *
 * @author vothu
 */

public interface iMailDTO  {
  public ArrayList<Mail> Getlist();

  public Mail GetMailById(int id);

  public Mail GetSingleMailById(int id);

  public void send(int sender, int receiver, String title, String content);
}