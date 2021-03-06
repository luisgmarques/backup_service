package Util;

import java.security.MessageDigest;

public class Util {
    public static String CRLF = "" + (char)0xD + (char)0xA + (char)0xD + (char)0xA;
    
    public static void log(String message) {
        System.out.println(message);
    }

    public static void logerr(String message) {
        System.out.println(message);
    }

    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
    
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
    
            return hexString.toString();
        } catch(Exception ex){
           throw new RuntimeException(ex);
        }
    }
}
