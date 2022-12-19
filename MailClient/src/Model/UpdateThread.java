/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author phamd
 */
public class UpdateThread extends Thread{
    Socket socket;
    
    public UpdateThread(Socket socket){
        this.socket = socket;
    }
    
    @Override
    public void run(){
        try{
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while(true){
                Request q = new Request(StringUtils.CHECKUPDATE, 0);
                Gson gson = new Gson();
                String s = gson.toJson(q);
                out.writeUTF(s);
                while(true){
                    try{
                        s = in.readUTF();System.out.println(s);
                        if(s.equals(StringUtils.UPTODATE))
                            break;
                        System.out.println(s);
                        MailModel mail = gson.fromJson(s, MailModel.class);
                        //save mail to db
                    }catch(Exception e){
                        System.out.println("GET UPDATE FAIL");
                        break;
                    }
                }
                sleep(1000);
            }
        }catch(Exception e){
        e.printStackTrace();}
    }
    
}
