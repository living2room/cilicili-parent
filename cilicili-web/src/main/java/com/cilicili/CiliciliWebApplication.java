package com.cilicili;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableTransactionManagement
@MapperScan("com.cilicili")
public class CiliciliWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CiliciliWebApplication.class, args);
	}

}
