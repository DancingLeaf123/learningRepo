package com.learning.backend_playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.learning.backend_playground.mapper")
public class BackendPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendPlaygroundApplication.class, args);
	}

}
