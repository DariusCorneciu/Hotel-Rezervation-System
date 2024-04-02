package service;

import model.user.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class HelpService {


    public static String hashPassword(String password){
        try {
            // Creează un obiect de tip MessageDigest pentru SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Aplică hashing-ul la parola
            byte[] hashedBytes = digest.digest(password.getBytes());

            // Convertirea rezultatului in format hexazecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }


}
