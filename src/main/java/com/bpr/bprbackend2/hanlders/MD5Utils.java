package com.bpr.bprbackend2.hanlders;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class MD5Utils {

    /**
     * @Description Generate a standard MD5 password
     */
    public static String MD5(String input) {
        MessageDigest md5 = null;
        try {
            // Generate a standard MD5 password
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return "check jdk";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = input.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * @Description Generate a salted MD5 password and mix the salt into the MD5 code to strengthen the MD5 password
     **/
    public static String generateSaltPassword(String password) {
        Random random = new Random();
        // Generate a 16-digit random number, which is the so-called salt
        /**
         * The salt here can also be defined as a system constant that is more complex, rather than being randomly generated.
         * Either way can be chosen. For example, the following line of code:
         * Salt encryption: The SALT string is randomly typed to make the MD5 encryption more complex.
         * public static final String SALT = "fskdhfiuhjfshfjhsad4354%@!@#%3";
         **/
        StringBuilder stringBuilder = new StringBuilder(16);
        stringBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        int len = stringBuilder.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                stringBuilder.append("0");
            }
        }
        // generate salt
        String salt = stringBuilder.toString();
        // Add the salt to the plaintext and generate a new MD5 code
        password = md5Hex(password + salt);
        // Mix the salt into the newly generated MD5 code. This is done to facilitate the verification of plaintext and ciphertext later.
        // Alternatively, the salt can be stored separately, but this is not recommended.
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }

    /**
     * @Description Verify whether the plaintext matches the salted MD5 code
     **/
    public static boolean verifySaltPassword(String password, String md5) {
        // Extract the previously added salt and the salted MD5 code from the MD5 code
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5.charAt(i);
            cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
            cs2[i / 3] = md5.charAt(i + 1);
        }
        String salt = new String(cs2);
        // Compare the two to see if they are the same
        return md5Hex(password + salt).equals(new String(cs1));
    }

    /**
     * @Description generate MD5 password
     **/
    private static String md5Hex(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }
}
