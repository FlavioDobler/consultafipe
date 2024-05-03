package br.com.flavio.consultafipe;

import br.com.flavio.consultafipe.principal.Main;
import br.com.flavio.consultafipe.service.ConsultaApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsultafipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConsultafipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Main main = new Main();
		main.exibeMenu();
	}
}
