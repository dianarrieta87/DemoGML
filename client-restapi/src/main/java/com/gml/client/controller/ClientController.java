package com.gml.client.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gml.client.model.entity.Client;
import com.gml.client.service.ClientService;

import jakarta.validation.Valid;

/**
 * Clase controller para la administracion de clientes
 */
/**
 * 
 */
@RestController
@RequestMapping("/api/clients")
public class ClientController {

	Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
    private ClientService clientService;
	
	@GetMapping
    public List<Client> getAllClients() {
        return clientService.findAll();
    }
	
	
	/**
	 * Busca un cliente por shared key
	 * @param id shared key del cliente
	 * @return ResponseEntity con objeto Client si lo encontró o un Map<String, Object> en caso de error
	 */
	@GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable String id) {
		
		Client client = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			client = clientService.findById(id);
		} catch(Exception e) {
            response.put("message", "Error consulting the clients .Please try again");
			response.put("error", e.getMessage());
			logger.error("Error in findById: "+e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(client == null) {
			response.put("message", "The client with shared key: ".concat(id.toString().concat(" doesn't exist")));
			logger.debug("Client not found: "+id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }
	
	/**
	 * Crea un nuevo cliente validando que no exista otro con el mismo shared key
	 * @param client objeto Client a crear
	 * @param result Objeto BindingResult para consultar los erroes de validacion
	 * @return ResponseEntity con un Map<String, Object> en caso de error o vacio si esta ok (HttpStatus.OK)
	 */
	@PostMapping
    public ResponseEntity<?> createClient(@Valid @RequestBody Client client, BindingResult result) {
		Map<String, Object> response = new HashMap<>();		
		
		//@Valid valida los datos recibidos y si hay error los regresa
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "Error in field '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			logger.debug("Validation failed for: "+client.getSharedKey());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		} 
		
		//si paso la validacion guarda el cliente
        try {
        	//primero valida que no exista
        	if (clientService.findById(client.getSharedKey())==null) {
				clientService.save(client);
				logger.info("Client saved successfully: "+client.getSharedKey());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        	} else {
        		logger.debug("Tried to save a client that already exists: "+client.getSharedKey());
        		response.put("message", "The client already exists");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        	}
		} catch (Exception e) {
			logger.error("Error saving the client: "+e.getMessage());
			response.put("message", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
    }
}
