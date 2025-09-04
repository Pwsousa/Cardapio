package br.com.saboresconectados.cardapio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CardapioApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardapioApplication.class, args);
	}

}
