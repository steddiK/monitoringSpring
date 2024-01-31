package com.example.Letgo.Repository;

import com.example.Letgo.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Letgo.Entity.Marchandises;

import java.util.List;

public interface MarchandiseRepository extends JpaRepository <Marchandises, Integer>{
	
}
