package com.clipboar.util.aes;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtil {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    private static SecretKeySpec getSecretKey(String key) {
        return new SecretKeySpec(key.getBytes(), ALGORITHM);
    }

    public static String encrypt(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec secretKey = getSecretKey(key);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decrypt(String encryptedData, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec secretKey = getSecretKey(key);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }

    public static String repeatToLength(String str, int length) {
        // Repeat the string enough times and then substring to get the desired length
        String repeated = str.repeat((length / str.length()) + 1);
        return repeated.substring(0, length);
    }
}
