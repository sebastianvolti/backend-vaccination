package uy.edu.fing.tse.vacunasuy.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class MyHashSalt {
	
	
	  public static String cifrar(String password, byte[] salt) {

	        String generatedPassword = null;
	        try {
	            MessageDigest md = MessageDigest.getInstance("SHA-512");
	            md.update(salt);
	            byte[] bytes = md.digest(password.getBytes());
	            StringBuilder sb = new StringBuilder();
	            for (int i = 0; i < bytes.length; i++) {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            generatedPassword = sb.toString();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return generatedPassword;
	    }
	  public static byte[] getSalt()  {
	        SecureRandom random = new SecureRandom();
	        byte[] salt = new byte[16];
	        random.nextBytes(salt);
	        return salt;
	    }
	  public String generateRandomPasswordPlain() {
		    int leftLimit = 48; // numeral '0'
		    int rightLimit = 122; // letter 'z'
		    int targetStringLength = 10;
		    Random random = new Random();

		    String generatedString = random.ints(leftLimit, rightLimit + 1)
		      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
		      .limit(targetStringLength)
		      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		      .toString();

		    //return generatedString;
		    //a modo de testeo fuerzo a que la clave sea siempre 1234
		    return "123456789";
		}
}
