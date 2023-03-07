package com.example.demo.service;

import com.example.demo.entity.EmailDto;

public interface SendEmailService {
    void sendEmail(EmailDto emailDto);
}