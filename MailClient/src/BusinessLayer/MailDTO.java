/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLayer;

import DataLayer.DBAccess;
import Model.Mail;
import Utility.*;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class MailDTO implements iMailDTO {

    // Validate ip/domain
    private static final Pattern PATTERN = Pattern.compile(
            "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$"
    );

    public static boolean IsValidIpAddress(final String ip) {
        return PATTERN.matcher(ip).matches();
    }

    public static boolean IsValidDomain(String str) {
        // Regex to check valid domain name.
        String regex
                = "^((?!-)[A-Za-z0-9-]" + "{1,63}(?<!-)\\.)" + "+[A-Za-z]{2,6}";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the string is empty
        // return false
        if (str == null) {
            return false;
        }

        // Pattern class contains matcher()
        // method to find the matching
        // between the given string and
        // regular expression.
        Matcher m = p.matcher(str);

        // Return if the string
        // matched the ReGex
        return m.matches();
    }

    public boolean checkDomainIp(String domainIp) {
        if (IsValidIpAddress(domainIp) || IsValidDomain(domainIp)) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Mail> Getlist() {
        ArrayList<Mail> list = new ArrayList<Mail>();
        DBAccess acc = new DBAccess();
        ResultSet rs = acc.Query("select * from mail");
        try {
            while (rs.next()) {
                Mail mail = new Mail();
                mail.setId(rs.getInt("id"));
                mail.setTitle(rs.getString("title"));
                String sth = rs.getString("content");
                String str = AES.decrypt(sth);
                mail.setContent(str);
                mail.setDateCreated(rs.getDate("datecreated"));
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

    public void send(int sender, int receiver, String title, String content) {
        DBAccess acc = new DBAccess();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        String sql
                = "insert into mail values  (N'" + title + "', N'" + AES.encrypt(content) + "', 20/10/2022, " + sender + ", " + receiver + ")";
        int rs = acc.Update(sql);
        if (rs > 0) {
            JOptionPane.showMessageDialog(null, "Send mail successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Send mail failed!");
        }
    }
}
