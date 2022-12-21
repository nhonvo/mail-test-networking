/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Application.frmMain;
import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author phamd
 */
public class UpdateThread extends Thread {

    Socket socket;
    public frmMain main;

    public UpdateThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            sleep(1000);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                Request q = new Request(StringUtils.CHECKUPDATE, main.listMail.size());
                Gson gson = new Gson();
                String s = gson.toJson(q);
                out.writeUTF(s);
                boolean changed = false;
                while (true) {
                    try {
                        s = in.readUTF();
                        System.out.println(s);
                        if (s.equals(StringUtils.UPTODATE)) {
                            break;
                        }
                        changed = true;
//                        System.out.println(s);
                        MailModel mail = gson.fromJson(s, MailModel.class);
                        main.listMail.add(mail);
                        //save mail to db
                    } catch (Exception e) {
//                        System.out.println("GET UPDATE FAIL");
                        e.printStackTrace();
                        break;
                    }

                }
                if (changed) {
                    main.showNoti();
                }

                sleep(6000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
