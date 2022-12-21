/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author vothu
 */
public class IsValid {

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

    public static boolean IPAndDomain(String domainIp) {
        if (IsValidIpAddress(domainIp) || IsValidDomain(domainIp)) {
            return true;
        } else {
            return false;
        }
    }
}
