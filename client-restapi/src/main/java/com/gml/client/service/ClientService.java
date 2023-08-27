package com.gml.client.service;

import java.util.List;

import com.gml.client.model.entity.Client;


public interface ClientService {

	public List<Client> findAll();
	public Client findById(String id);	
	public Client save(Client cliente);
	
}
