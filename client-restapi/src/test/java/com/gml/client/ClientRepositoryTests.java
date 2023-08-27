package com.gml.client;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.gml.client.model.entity.Client;
import com.gml.client.model.repository.ClientRepository;


@DataJpaTest
public class ClientRepositoryTests {

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private ClientRepository cr;
	
	@Test
    public void findClientTest(){
        Client c = new Client();
        c.setSharedKey("darrieta");
        c.setBusinessId("Diana Arrieta");
        c.setEmail("diana@prueba.com");
        c.setPhone(3115689900L);
        
        entityManager.persist(c);
        entityManager.flush();
        
        Client foundClient = cr.findById("darrieta").orElse(null);
        Assertions.assertThat(foundClient).isNotNull();
    }
	
	@Test
    public void findNonExistentClientTest(){
		Client foundClient = cr.findById("not_a_client").orElse(null);
        Assertions.assertThat(foundClient).isNull();
	}
}
