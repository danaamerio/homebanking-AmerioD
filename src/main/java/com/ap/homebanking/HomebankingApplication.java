package com.ap.homebanking;

import com.ap.homebanking.models.client;
import com.ap.homebanking.repositories.clientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

@Bean
	public CommandLineRunner initData(clientRepository ClientRepository){
		return (args -> {

			client Client1 = new client();
			Client1.setFirstName("Melba");
			Client1.setLastName("Morel");
			Client1.setEmail("melba@mindhub.com");


			client Client2 = new client("Juan", "sanchez", "juansanchez@gmail.com");

			ClientRepository.save(Client1);
			ClientRepository.save(Client2);

		});
	}
}
