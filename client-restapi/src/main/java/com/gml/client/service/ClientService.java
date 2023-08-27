package com.gml.client.service;

import java.util.List;

import com.gml.client.model.entity.Client;


/**
 * Service para administracion de clientes
 */
public interface ClientService {

	/** 
	 * Obtiene una lista de todos los clientes
	 * @return List de objetos Client
	 */
	public List<Client> findAll();
	
	/**
	 * Busca un client por Id (Shared key)
	 * @param id shared key del cliente
	 * @return Objet cliente encontrado o null si no lo encontro
	 */
	public Client findById(String id);	
	
	/**
	 * Guarda un cliente
	 * @param client objeto client con los datos del cliente a guardar
	 * @return el cliente (entity) guardado
	 */
	public Client save(Client client);
	
}
