package com.gml.client.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gml.client.model.entity.Client;
import com.gml.client.model.repository.ClientRepository;
import com.gml.client.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository cRep;
	
	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll(){
		return (List<Client>)cRep.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Client findById(String id){
		return cRep.findById(id).orElse(null);
	}	
	
	@Override
	public Client save(Client client) {
		return cRep.save(client);
	}
	
}
