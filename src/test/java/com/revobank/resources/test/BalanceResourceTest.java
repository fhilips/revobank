package com.revobank.resources.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.revobank.dto.DebitDTO;
import com.revobank.factory.BalanceFactory;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BalanceResourceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private Double invalidAmount;
	private Long validId;
	private Long invalidId;
	private Long directorAccountId;
	private Long notDirectorAccountId;
	
	
	@BeforeEach
	void setUp() throws Exception {
		invalidAmount = 60000.0;
		validId = 1l;
		invalidId = 70l;
		directorAccountId = 2l;
		notDirectorAccountId = 3l;
	}

	@Test
	public void addDebitShouldReturnIdOkWhenValidIdAndAmount() throws Exception {
		
		DebitDTO dto = BalanceFactory.createDebit();
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions result =
				mockMvc.perform(post("/balances/debit")					
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.message").value("Debit created on balance ID 1"));
		
	}
		
	@Test
	public void addDebitShouldReturnIsForbiddenWhenAmountIsBiggerThanBalanceAndJobTitleIsDIRECTOR() throws Exception {
		
		DebitDTO dto = BalanceFactory.createDebit();
		dto.setAmount(invalidAmount);
		dto.setAccountId(directorAccountId);
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions result =
				mockMvc.perform(post("/balances/debit")					
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isForbidden());
		result.andExpect(jsonPath("$.message").value("Not enough balance!"));
		
	}
	
	@Test
	public void addDebitShouldReturnIsForbiddenWhenAmountIsBiggerThanBalanceAndJobTitleIsNotDIRECTOR() throws Exception {
		
		DebitDTO dto = BalanceFactory.createDebit();
		dto.setAmount(invalidAmount);
		dto.setAccountId(notDirectorAccountId);
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions result =
				mockMvc.perform(post("/balances/debit")					
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isForbidden());
		result.andExpect(jsonPath("$.message").value("Not enough balance! Account blocked"));
		
	}
	
	@Test
	public void findByAccountIdShouldReturnIsOkWhenValidId() throws Exception {
		
		ResultActions result =
				mockMvc.perform(get("/balances/{id}", validId)
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.balance").value(5000.0));				
		
	}

	@Test
	public void findByIdShouldReturnIsNotFoundWhenInvalidId() throws Exception {
		
		ResultActions result =
				mockMvc.perform(get("/balances/{accounts}", invalidId)
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isNotFound());
		result.andExpect(jsonPath("$.error").value("Resource not found"));			
		
	}
}
