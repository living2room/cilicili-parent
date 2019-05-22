package com.cilicili;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cilicili.advertisement.mapper")
public class CiliciliWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CiliciliWebApplication.class, args);
	}

}
