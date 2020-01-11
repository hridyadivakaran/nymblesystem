/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nymble.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author staff
 */
public class DecryptEncryptString {

    static String hexStr = "";
    byte[] encryptContent = null;

    
    public String encrypt(String content, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] key = generateKey(password);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encrContent = cipher.doFinal(content.getBytes());
        encryptContent = encrContent;
        hexStr = getHexString(encrContent);
        return hexStr;

    }

    public String getHexString(byte[] bytes) {
        final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);

    }

    private byte[] generateKey(String password) throws UnsupportedEncodingException,
            NoSuchAlgorithmException {
        StringBuffer sb = new StringBuffer(password);
        while (sb.length() < 128) {
            sb.append(password);
        }
        byte[] key = sb.toString().getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        return key;
    }

    public String decrypt(String hexStr, String password)
            throws UnsupportedEncodingException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] key = generateKey(password);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        byte[] encrData = hexStringToByteArray(hexStr);

        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] content = cipher.doFinal(encrData);
        System.out.println("content : " + new String(content));
        return new String(content);

    }

    public byte[] hexStringToByteArray(String data) {
        int k = 0;
        byte[] results = new byte[data.length() / 2];
        for (int i = 0; i < data.length();) {
            results[k] = (byte) (Character.digit(data.charAt(i++), 16) << 4);
            results[k] += (byte) (Character.digit(data.charAt(i++), 16));
            k++;
        }
        return results;
    }
}
