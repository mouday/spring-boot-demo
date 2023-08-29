package com.example.demo.service;

/**
 * 加密解密的接口
 */
public interface SecretDataService {
    /**
     * 数据加密
     *
     * @param data 待加密数据
     * @return String 加密结果
     */
    String encrypt(String data);

    /**
     * 数据解密
     *
     * @param data 待解密数据
     * @return String 解密后的数据
     */
    String decrypt(String data);
}
