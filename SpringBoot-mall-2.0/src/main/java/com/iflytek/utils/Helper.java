package com.iflytek.utils;


public class Helper {
    private static String key = "wow!@#$%";

    public static boolean isStringInArray(String str, String[] array){
        for (String val:array){
            if(str.equals(val)){
                return true;
            }
        }
        return false;
    }

    public static String encode(String str){
        String enStr = "";
        try {
            enStr = DesUtil.encrypt(str, key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return enStr;
    }

    public static String decode(String str) {
        String deStr = "";
        try {
            deStr = DesUtil.decrypt(str, key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return deStr;
    }

    public static String bytesToHex(byte bytes[], int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < start + end; i++) {
            sb.append(byteToHex(bytes[i]));
        }
        return sb.toString();
    }

 
    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String byteToHex(byte bt) {
        return HEX_DIGITS[(bt & 0xf0) >> 4] + "" + HEX_DIGITS[bt & 0xf];
    }
}
