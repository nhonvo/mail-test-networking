/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLayer;

import DataLayer.DBAccess;
import Model.Mail;
import Utility.*;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class MailDTO implements iMailDTO {

    // Validate ip/domain
    public ArrayList<Mail> Getlist() {
        ArrayList<Mail> list = new ArrayList<Mail>();
        DBAccess acc = new DBAccess();
        ResultSet rs = acc.Query("select * from mail order by datecreated");
        try {
            while (rs.next()) {
                Mail mail = new Mail();
                mail.setId(rs.getInt("id"));
                mail.setTitle(rs.getString("title"));
                String sth = rs.getString("content");
                String str = AES.decrypt(sth);
                mail.setContent(str);
                mail.setDateCreated(rs.getTimestamp("datecreated"));
                // thử xem
                mail.setSender(rs.getInt(5));
                mail.setReceiver(rs.getInt(6));
                list.add(mail);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }

    // get mail by id
    public Mail GetMailById(int id) {
        Mail mail = new Mail();
        DBAccess acc = new DBAccess();
        ResultSet rs = acc.Query("select * from mail where id = " + id);
        try {
            while (rs.next()) {
                mail.setTitle(rs.getString("title"));
                //                mail.setContent(rs.getString("content"));
                String sth = rs.getString("content");
                String str = AES.decrypt(sth);
                mail.setContent(str);
                mail.setDateCreated(rs.getDate("datecreated"));
                mail.setSender(rs.getInt(5));
                mail.setReceiver(rs.getInt(6));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return mail;
    }

    public Mail GetSingleMailById(int id) {
        Mail mail = new Mail();
        DBAccess acc = new DBAccess();
        ResultSet rs = acc.Query("select * from mail where id = " + id);
        try {
            while (rs.next()) {
                mail.setTitle(rs.getString("title"));
                mail.setContent(rs.getString("content"));
                mail.setDateCreated(rs.getDate("datecreated"));
                mail.setSender(rs.getInt(5));
                mail.setReceiver(rs.getInt(6));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return mail;
    }

    public boolean send(
            int sender,
            int receiver,
            String title,
            String content,
            Date dateSend
    ) {
        DBAccess acc = new DBAccess();
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
        String strDate = dateFormat.format(dateSend);
        String sql
                = "insert into mail values  (N'" + title + "', N'" + AES.encrypt(content) + "', GETDATE(), " + sender + ", " + receiver + ")";
        int rs = acc.Update(sql);
        if (rs > 0) {
            return true;
//            JOptionPane.showMessageDialog(null, "Send mail successfully!");
        } else {
            return false;
//            JOptionPane.showMessageDialog(null, "Send mail failed!");
        }
    }
}
