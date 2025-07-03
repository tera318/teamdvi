package com.example.zikosyoukaiWeb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.zikosyoukaiWeb.dao.mapper")
public class TeamappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamappApplication.class, args);
	}
}