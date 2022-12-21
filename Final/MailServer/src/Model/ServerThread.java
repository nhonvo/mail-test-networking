/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import BusinessLayer.MailDTO;
import BusinessLayer.UserDTO;
import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author phamd
 */
public class ServerThread extends Thread {

    Socket socket;
    String username;
    DataInputStream in;
    DataOutputStream out;
    Gson gson;

    public ServerThread(Socket socket) {
        this.socket = socket;
        System.out.println(
                "connect to " + socket.getInetAddress().getHostAddress()
        );
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            gson = new Gson();
        } catch (Exception e) {
        }
    }

    public void sendUpdate(int num) {
        try {
            MailDTO obj = new MailDTO();
            UserDTO objUser = new UserDTO();

            ArrayList<Mail> lst = obj.Getlist();
//      ArrayList<MailModel> lstMailModels = new ArrayList<>();
//      System.out.println(lst.size());
//      // Parse data from Mail to MailModel
//      for (Mail mail : lst) {
//        User to = objUser.GetUserById(mail.getSender());
//        User from = objUser.GetUserById(mail.getReceiver());
//        MailModel mailModel = new MailModel(mail.getId(),mail.getTitle(), mail.getContent(), mail.getDateCreated(), to.getUsername(), from.getUsername());
//        
//        lstMailModels.add(mailModel);
//          System.out.println(mailModel.dateCreated.toString());
//      }
//     
//      // Send data to client
//
            int totalMail = lst.size();
            if (num < totalMail) {
                Gson gson = new Gson();
                System.out.println("send update to" + username);
                for (int i = num; i < totalMail; i++) {
                    Mail mail = lst.get(i);
                    User to = objUser.GetUserById(mail.getSender());
                    User from = objUser.GetUserById(mail.getReceiver());
                    MailModel mailModel = new MailModel(mail.getId(), mail.getTitle(), mail.getContent(), mail.getDateCreated(), to.getUsername(), from.getUsername());
                    String s = gson.toJson(mailModel);
                    out.writeUTF(s);
                }
            }
            out.writeUTF(StringUtils.UPTODATE);
        } catch (Exception e) {
        }
    }

    public boolean login(LoginModel user) throws Exception {
        UserDTO obj = new UserDTO();
        return obj.Login(user.username, user.password);
    }

    public void sendMail(MailModel mail) throws Exception {
        try {
            MailDTO obj = new MailDTO();
            UserDTO user = new UserDTO();
            // check receiver not exist
            if (!user.CheckUser(mail.receiver)) {
                User sender = user.GetUserByUserName(mail.sender);

                User receiver = user.GetUserByUserName(mail.receiver);
                boolean sendStatus = obj.send(
                        sender.getId(),
                        sender.getId(),
                        mail.title,
                        "Ten nguoi gui khong ton tai trong db",
                        mail.dateCreated
                );
                if (sendStatus) {
                    out.writeUTF(StringUtils.SENDED);
                } else {
                    out.writeUTF(StringUtils.SENDEDFAIL);
                }
            } else if (mail.getReceiver().equals(mail.getSender())) {
                out.writeUTF(StringUtils.SAMEUSERNAME);
            } else {
                User sender = user.GetUserByUserName(mail.sender);

                User receiver = user.GetUserByUserName(mail.receiver);
                boolean sendStatus = obj.send(
                        sender.getId(),
                        receiver.getId(),
                        mail.title,
                        mail.content,
                        mail.dateCreated
                );
                if (sendStatus) {
                    out.writeUTF(StringUtils.SENDED);
                } else {
                    out.writeUTF(StringUtils.SENDEDFAIL);
                }
            }
        } catch (Exception e) {
            out.writeUTF(StringUtils.SENDEDFAIL);
        }
    }

    @Override
    public void run() {
        try {
            String s = in.readUTF();
            Request q = gson.fromJson(s, Request.class);
            username = q.getUser().getUsername();
            if (q.getRequestHeader().equals(StringUtils.LOGIN) && login(q.getUser())) {
                out.writeUTF(StringUtils.LOGINOK);
                System.out.println(StringUtils.LOGINOK + username);
                while (true) {
                    String onlRequest = in.readUTF();

                    try {
                        q = gson.fromJson(onlRequest, Request.class);
                        switch (q.getRequestHeader()) {
                            case StringUtils.CHECKUPDATE:
                                sendUpdate(q.getQuantity());
                                break;
                            case StringUtils.SENDMAIL:
                                sendMail(q.getMail());
                                break;
                        }
                    } catch (Exception e) {
                        out.writeUTF(StringUtils.REQUESTFAIL);
                    }
                }
            } else {
                out.writeUTF(StringUtils.LOGINFAIL);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception e) {
            }
        }
    }
}
