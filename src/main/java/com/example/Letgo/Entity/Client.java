package com.example.Letgo.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Transactional
@Table(name="CLIENT")
public class Client {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int Id;
	@Setter
	@Getter
	private String Nom;
	@Setter
	@Getter
	private String age;
	
	@Getter
	@ManyToMany(cascade= CascadeType.ALL)
	@JoinTable(
	        name = "RELATION",
	        joinColumns = @JoinColumn(name = "ID_CLIENT"),
	        inverseJoinColumns = @JoinColumn(name = "ID_MARCHANDISE")
	)
	private List<Marchandises> listMarch = new ArrayList<>();
	public Client(String Nom, String age) {
		this.Nom= Nom;
		this.age= age;
	}
	public Client() {}
	public void aboutMerchandise(Marchandises m) {
			listMarch.add(m);
	}

}
