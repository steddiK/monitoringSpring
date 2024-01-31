package com.example.Letgo;

import java.util.Arrays;
import java.util.List;

import com.example.Letgo.Service.ClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.example.Letgo.Entity.Client;
import com.example.Letgo.Entity.Marchandises;
import com.example.Letgo.Repository.ClientRepository;
import com.example.Letgo.Repository.MarchandiseRepository;

@SpringBootApplication
public class LetgoApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext con= SpringApplication.run(LetgoApplication.class, args);
		ClientRepository CR= con.getBean(ClientRepository.class);
		MarchandiseRepository MR= con.getBean(MarchandiseRepository.class);
		ClientService CS= con.getBean(ClientService.class);

		Client Steddi= new Client("Steddi","23");
		Client Nombana= new Client("Nombana","20");
		Client Rakoto= new Client("Rakoto","29");
		
		//Liste des clients
		List<Client> clients= Arrays.asList(Steddi,Nombana,Rakoto);
		CS.creer(Steddi);
		CS.creer(Nombana);
		CS.creer(Rakoto);
		
		Marchandises ovy= new Marchandises("ovy");
		Marchandises karoty= new Marchandises("karoty");
		Marchandises vary= new Marchandises("vary");
		Marchandises atody= new Marchandises("atody");
		
		List<Marchandises> legumes = Arrays.asList(ovy,karoty,vary,atody);
		MR.saveAll(legumes);

		//Achat de marchandise
		CS.Achat(Steddi,ovy);
		CS.Achat(Nombana,atody);
		CS.Achat(Steddi,karoty);
		CS.Achat(Steddi,vary);
		CS.Achat(Rakoto,ovy);

		//Validation des achats:
		CR.saveAll(clients);

	}

}
