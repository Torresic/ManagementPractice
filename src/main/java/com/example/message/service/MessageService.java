package com.example.message.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

public interface MessageService {
    String getMessage();
    String getProfile();
}
