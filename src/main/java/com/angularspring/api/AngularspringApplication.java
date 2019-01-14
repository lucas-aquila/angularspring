package com.angularspring.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@SpringBootApplication
public class AngularspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngularspringApplication.class, args);
	}
	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin").password("admin").roles("ROLE");
	}

}
