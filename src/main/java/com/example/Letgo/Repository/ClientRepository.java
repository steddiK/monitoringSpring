package com.example.Letgo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Letgo.Entity.Client;


public interface ClientRepository extends JpaRepository <Client, Integer>{

	List<Client> findByAge(String age);

	Client findByage(String age);

}