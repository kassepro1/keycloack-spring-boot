package com.kp;

import com.kp.domaine.Product;
import com.kp.repository.ProductRepository;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcomAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomAppApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository){

		return args -> {
			 productRepository.save(new Product(null,"HP",2000.0));
			 productRepository.save(new Product(null,"MAC BOOK PRO",6000.0));
			 productRepository.save(new Product(null,"IPHONE 11 PRO MAX ",12000.0));

			 productRepository.findAll().forEach(p->{
			 	System.out.println(p);
			 });
		};

	}

	@Bean
	KeycloakRestTemplate keycloakRestTemplate(KeycloakClientRequestFactory keycloakClientRequestFactory){
		return new KeycloakRestTemplate(keycloakClientRequestFactory);
	}

}
