package com.BlogApp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlogAppApiApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);
	}

    @Bean
    ModelMapper modelMapper()
    {
        return new ModelMapper();

    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    
	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("admin"));
		
	}
    
    
}
