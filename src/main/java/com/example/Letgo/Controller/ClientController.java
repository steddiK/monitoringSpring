package com.example.Letgo.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.Letgo.Entity.Client;
import com.example.Letgo.Service.ClientService;
import java.util.List;
 
@RestController
@RequestMapping("")
 
public class ClientController {
	@Autowired
	private final ClientService clientService;
	public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
	@PostMapping( "/saveo")
	public String creer(@RequestBody Client client) {
		this.clientService.creer(client);
		return "Client Enregistré avec succes";
	}
	@GetMapping("/rechercher")
	public List<Client> rechercher(){
		return this.clientService.rechercher();
	}
	
}