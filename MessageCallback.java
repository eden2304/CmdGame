package com.company;

public interface MessageCallback {
    void send(String message);
}

// Initialize(..)  messageCallback = (s) -> System.out.println(s);