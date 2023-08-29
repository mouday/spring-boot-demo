package com.example.demo.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * 数据加密解密工具类
 * 加密后返回base64
 */
public class CipherUtil {
    /**
     * 定义加密算法
     */
    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 解密
     *
     * @param secretKey
     * @param cipherText base64
     * @return
     */
    public static String decrypt(String secretKey, String cipherText) {
        // 将Base64编码的密文解码
        byte[] encrypted = Base64.getDecoder().decode(cipherText);

        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(encrypted));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密
     *
     * @param secretKey
     * @param plainText base64
     * @return
     */
    public static String encrypt(String secretKey, String plainText) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes(Charset.forName("UTF-8"))));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
