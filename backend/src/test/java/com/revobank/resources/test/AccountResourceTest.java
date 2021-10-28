package com.revobank.resources.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revobank.dto.AccountDTO;
import com.revobank.factory.AccountFactory;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AccountResourceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private String invalidDocument;
	private Long validId;
	private Long invalidId;
	
	@BeforeEach
	void setUp() throws Exception {
		invalidDocument = "125454844";
		validId = 1l;
		invalidId = 70l;
	}

	@Test
	public void createShouldReturnIsCreatedWhenValidData() throws Exception {
		
		AccountDTO dto = AccountFactory.createAccountDTO();
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions result =
				mockMvc.perform(post("/accounts/create")					
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.message").value("Created account with ID 1"));
		
	}
	
	@Test
	public void createShouldReturnIsUnprocessableEntityWhenDocumentIsInvalid() throws Exception {
		
		AccountDTO dto = AccountFactory.createAccountDTO();
		dto.setDocument(invalidDocument);
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions result =
				mockMvc.perform(post("/accounts/create")					
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isUnprocessableEntity());
		result.andExpect(jsonPath("$.errors[0].fieldName").value("document"));		
	}
	
	@Test
	public void createShouldReturnIsUnprocessableEntityWhenRequiredFieIsNull() throws Exception {
		
		AccountDTO dto = AccountFactory.createAccountDTO();
		dto.setBirthDate(null);;
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions result =
				mockMvc.perform(post("/accounts/create")					
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isUnprocessableEntity());
		result.andExpect(jsonPath("$.error").value("Validation exception"));
		result.andExpect(jsonPath("$.errors[0].fieldName").value("birthDate"));
		
	}
	
	@Test
	public void getAllShouldReturnCorrectData() throws Exception {
			
		ResultActions result =
				mockMvc.perform(get("/accounts")
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$[0].id").value("1"));
		result.andExpect(jsonPath("$[1].id").value("2"));
		result.andExpect(jsonPath("$[2].id").value("3"));
		
		result.andExpect(jsonPath("$[0].name").value("Carlos Albuquerque"));
		result.andExpect(jsonPath("$[1].name").value("Maria Candida"));
		result.andExpect(jsonPath("$[2].name").value("João Augusto"));
		
	}
	
	@Test
	public void findByIdShouldReturnCorrectDataWhenValidId() throws Exception {
			
		ResultActions result =
				mockMvc.perform(get("/accounts/{accounts}", validId)
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").value("1"));
		result.andExpect(jsonPath("$.name").value("Carlos Albuquerque"));
		result.andExpect(jsonPath("$.birthDate").value("12/07/1990"));	
		
	}
	
	@Test
	public void findByIdShouldReturnIsNotFoundWhenInvalidId() throws Exception {
		
		ResultActions result =
				mockMvc.perform(get("/accounts/{accounts}", invalidId)
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isNotFound());
		result.andExpect(jsonPath("$.error").value("Entity not found"));			
		
	}
	
	public void updateShouldReturnIsOkWhenValidId() throws Exception {
		
		AccountDTO dto = AccountFactory.createAccountDTO();
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions result =
				mockMvc.perform(put("/accounts/{id}", validId)					
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.message").value("Updated account with ID 1"));
		
	}
}
