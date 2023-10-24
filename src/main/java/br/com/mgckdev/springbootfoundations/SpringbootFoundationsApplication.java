package br.com.mgckdev.springbootfoundations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.mgckdev")
public class SpringbootFoundationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootFoundationsApplication.class, args);
	}

}
