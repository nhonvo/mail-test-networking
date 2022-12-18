package Utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Utility.AES;

public class test {

    public static void main(String[] args) {
        String str = "iam nhon";
        String deStr = AES.encrypt(str);
        System.out.println("decrypt: " + deStr);
        str = AES.decrypt(deStr);
        System.out.println("encrypt: " + str);
    }
}
