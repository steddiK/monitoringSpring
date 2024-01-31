package com.example.Letgo.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Transactional
@Table(name="MARCHANDISE")
public class Marchandises {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id_march;
	@Setter
	@Getter
	private String nom_march;
	
	@ManyToMany
	@JoinTable(
	        name = "RELATION",
			joinColumns = @JoinColumn(name = "ID_MARCHANDISE"),
			inverseJoinColumns = @JoinColumn(name = "ID_CLIENT")
	)
	private List<Client> listCli= new ArrayList<>();
	
	//Constructor
	public Marchandises(String name) {
		this.nom_march= name;
	}

	public Marchandises() {
		
	}

	//Ajout de client
	public void ajoutCli(Client client) {
		listCli.add(client);
	}
	public List<Client> getClients() {
		return listCli;
	}
	
}
