package com.learning.backend_playground.controller;

import com.learning.backend_playground.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.learning.backend_playground.common.Result;

@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
        System.out.println("HelloController created");
    }

    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.success(helloService.buildHelloMessage());
    }

}
