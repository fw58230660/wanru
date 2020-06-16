package com.wanru;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wanru.mapper")
public class WanruApplication {

	public static void main(String[] args) {
		SpringApplication.run(WanruApplication.class, args);
	}

}
