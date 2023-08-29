package com.example.demo.service.impl;


import com.example.demo.config.SecretConfig;
import com.example.demo.service.SecretDataService;
import com.example.demo.utils.CipherUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 具体的加解密实现
 */
@Service
public class SecretDataServiceImpl implements SecretDataService {
    @Resource
    private SecretConfig secretConfig;

    @Override
    public String decrypt(String data) {
        return CipherUtil.decrypt(secretConfig.getKey(), data);
    }

    @Override
    public String encrypt(String data) {
        return CipherUtil.encrypt(secretConfig.getKey(), data);
    }
}
