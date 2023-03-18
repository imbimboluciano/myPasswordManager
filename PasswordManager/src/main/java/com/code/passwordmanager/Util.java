package com.code.passwordmanager;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class Util {

    private static final String lowerCase = "qwertyuiopasdfghjklzxcvbnm";
    private static final String upperCase = "QWERTYUIOPASDFGHJKLZXCVBNM";
    private static final String special = "!@#$%&*()_-?";

    private static final String number = "1234567890";

    public static void copyStringToClipboard(String str){
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(str);
        clipboard.setContent(content);
    }

    public static boolean checkSecurePassword(String str){

        return containsCharacter(str,lowerCase)  && containsCharacter(str,upperCase) && containsCharacter(str, number) && containsCharacter(str, special);
    }

    private static boolean containsCharacter(String str, String set){

        for (Character lowercase:
             set.toCharArray()) {
            if (str.contains(lowercase.toString())) {
                return true;
            }
        }
        return false;
    }

}
