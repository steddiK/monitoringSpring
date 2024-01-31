package com.example.Letgo.Service;

import com.example.Letgo.Entity.Marchandises;
import com.example.Letgo.Repository.MarchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarchandiseServices {

    @Autowired
    private final MarchandiseRepository marchandiseRepository;

    public MarchandiseServices(MarchandiseRepository marchandiseRepository) {
        this.marchandiseRepository = marchandiseRepository;
    }

    public void creer(Marchandises m) {
        this.marchandiseRepository.save(m);
    }

    public Marchandises rechercher(int id) {
        Optional<Marchandises> optionalMarchandises= this.marchandiseRepository.findById(id);
        return optionalMarchandises.orElse(null);
    }

    public List<Marchandises> rechercherAll() {
        List<Marchandises> m= this.marchandiseRepository.findAll();
        System.out.println("size: "+m.size());
        return m;
    }
}
