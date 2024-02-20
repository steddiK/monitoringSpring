package com.example.Letgo.Service;

import com.example.Letgo.Entity.Marchandises;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import io.prometheus.client.Counter;

//import io.prometheus.client.spring.web.PrometheusTimeMethod;
import io.micrometer.core.instrument.Counter;

import io.micrometer.core.instrument.MeterRegistry;
import com.example.Letgo.Entity.Client;
import com.example.Letgo.Repository.ClientRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Service
@RestController
public class ClientService {

	@Autowired
	MeterRegistry meterRegistry;
	private final ClientRepository clientRepository;
	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@GetMapping("/erreur_400")
	public void test(@RequestBody String requestBody){
		if (requestBody == null || requestBody.isEmpty()) {
			throw new IllegalArgumentException("Request body is required");
		}
	}
	public void creer(Client client) {
			this.clientRepository.save(client);
	}
	public List<Client> rechercher(){
		return this.clientRepository.findAll();
	}

	@Transactional
	public void Achat(Client client, Marchandises marchandise) {
			System.out.println("ACHAT EFFECTUE");
			client.aboutMerchandise(marchandise);
	}
}