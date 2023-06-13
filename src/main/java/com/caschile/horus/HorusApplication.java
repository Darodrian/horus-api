package com.caschile.horus;

import com.caschile.horus.dao.CajaDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HorusApplication {

	public static void main(String[] args) {
		SpringApplication.run(HorusApplication.class, args);
	}

	@Bean
	public CajaDAO ticketCallDao(){
		return new CajaDAO();
	}

}
