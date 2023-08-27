package com.gml.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gml.client.model.entity.Client;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTests {

    @Autowired
    private MockMvc mockMvc; 
    
	
    @Test
	public void findNonExistentClient() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders.get("/api/not_client")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
	}
    
    @Test
	public void createClientTest() throws Exception {
    	
    	String jsonRequest = "{\"sharedKey\":\"otro3\",\"businessId\":\"Pedro Perez\",\"email\":\"pedro@prueba.com\",\"phone\":\"3014444555\"}";

        mockMvc.perform(post("/api/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    	    	
    }
}
