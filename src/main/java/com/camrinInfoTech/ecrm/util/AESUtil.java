package com.camrinInfoTech.ecrm.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class AESUtil {
    private static final String AES = "AES";
    private static final String SECRET_KEY = "0124202412345678901234567890123456789012"; // Any length key

    // Generate a valid 256-bit (32-byte) AES key from the given string
    private static SecretKeySpec getValidKey(String key) throws Exception {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        keyBytes = sha.digest(keyBytes);
        keyBytes = Arrays.copyOf(keyBytes, 32); // Ensure 256-bit key
        return new SecretKeySpec(keyBytes, AES);
    }

    public static String encrypt(String data) throws Exception {
        SecretKeySpec secretKey = getValidKey(SECRET_KEY);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec secretKey = getValidKey(SECRET_KEY);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] originalData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(originalData, StandardCharsets.UTF_8);
    }
}
