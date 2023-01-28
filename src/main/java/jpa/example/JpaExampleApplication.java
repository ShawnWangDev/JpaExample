package jpa.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"jpa/example"})
@SpringBootApplication
public class JpaExampleApplication {
	public static void main(String[] args) {
		SpringApplication.run(JpaExampleApplication.class, args);
	}
}
