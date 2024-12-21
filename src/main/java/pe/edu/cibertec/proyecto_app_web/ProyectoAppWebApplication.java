package pe.edu.cibertec.proyecto_app_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing

public class ProyectoAppWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoAppWebApplication.class, args);
	}

}
