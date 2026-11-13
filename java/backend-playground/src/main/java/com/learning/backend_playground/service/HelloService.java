package com.learning.backend_playground.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class HelloService {
    private final String greeting;

    public HelloService(@Value("${app.greeting}") String greeting) {
        System.out.println("HelloService created");
        this.greeting = greeting;
    }

    public String buildHelloMessage(){
        return greeting + "!";
    }
}
