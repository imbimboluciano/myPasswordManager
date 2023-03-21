package com.code.passwordmanager;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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



    public static String generateSecurePassword(int lunghezza, boolean uppercase, boolean numbers, boolean specials) {

        StringBuilder password = new StringBuilder();

        while (password.length() != lunghezza){
            int rand = (int)(4 * Math.random());

            switch (rand){
                case 0:
                    rand = (int)(lowerCase.length() * Math.random());
                    password.append(lowerCase.charAt(rand));
                    break;
                case 1:
                    if(uppercase) {
                        rand = (int) (upperCase.length() * Math.random());
                        password.append(upperCase.charAt(rand));
                    }
                    break;
                case 2:
                    if(numbers) {
                        rand = (int) (number.length() * Math.random());
                        password.append(number.charAt(rand));
                    }
                    break;
                case 3:
                    if(specials) {
                        rand = (int) (special.length() * Math.random());
                        password.append(special.charAt(rand));
                    }
                    break;
            }

        }

        return password.toString();
    }

    public static void copyFile(File from, File to ){
        try {
            Files.copy( from.toPath(), to.toPath() );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
