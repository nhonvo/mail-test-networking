package BusinessLayer;

import Model.Mail;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author vothu
 */

public interface iMailDTO {
  public ArrayList<Mail> Getlist();

  public Mail GetMailById(int id);

  public Mail GetSingleMailById(int id);

  public boolean send(int sender, int receiver, String title, String content, Date dateSend);
}
