package br.com.JPAproject.JPAConnection;

import br.com.JPAproject.JPAConnection.Endpoints.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(scanBasePackages = "br.com.JPAproject.JPAConnection")
public class JpaConnectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaConnectionApplication.class, args);
	}

}
