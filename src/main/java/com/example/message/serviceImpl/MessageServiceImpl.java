package com.example.message.serviceImpl;

import com.example.message.service.MessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Value("${course.message:course.default}")
    private String message;

    @Value("${profile.active:profile.default}")
    private String profile;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getProfile() {
        return profile;
    }
}
