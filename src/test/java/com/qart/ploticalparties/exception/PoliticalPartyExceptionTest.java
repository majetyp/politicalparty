package com.qart.ploticalparties.exception;

import static com.qart.ploticalparties.testutils.TestUtils.currentTest;
import static com.qart.ploticalparties.testutils.TestUtils.exceptionTestFile;
import static com.qart.ploticalparties.testutils.TestUtils.testReport;
import static com.qart.ploticalparties.testutils.TestUtils.myAssert;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.qart.ploticalparties.controller.PoliticalPartyController;
import com.qart.ploticalparties.dto.PoliticalPartyDto;
import com.qart.ploticalparties.exceptions.PoliticalPartyNotFoundException;
import com.qart.ploticalparties.model.exception.ExceptionResponse;
import com.qart.ploticalparties.service.PoliticalPartyService;
import com.qart.ploticalparties.testutils.MasterData;

@WebMvcTest(PoliticalPartyController.class)
@AutoConfigureMockMvc
public class PoliticalPartyExceptionTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PoliticalPartyService partyService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testRegisterPoliticalPartyInvalidDataException() throws Exception {
		PoliticalPartyDto politicalPartyDto = MasterData.getPartyDto();
		PoliticalPartyDto savedPoliticalPartyDto = MasterData.getPartyDto();
		savedPoliticalPartyDto.setPoliticalPartyId(1L);

		politicalPartyDto.setPartyName("Ab");

		when(this.partyService.registerParty(politicalPartyDto)).thenReturn(savedPoliticalPartyDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/party")
				.content(MasterData.asJsonString(politicalPartyDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		myAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}
	
	@Test
	public void testUpdatePoliticalPartyInvalidDataException() throws Exception {
		PoliticalPartyDto politicalPartyDto = MasterData.getPartyDto();
		PoliticalPartyDto savedPoliticalPartyDto = MasterData.getPartyDto();
		savedPoliticalPartyDto.setPoliticalPartyId(1L);

		politicalPartyDto.setPartyName("Ab");

		when(this.partyService.registerParty(politicalPartyDto)).thenReturn(savedPoliticalPartyDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/party")
				.content(MasterData.asJsonString(politicalPartyDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		myAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}


	@Test
	public void testDeletePoliticalPartyNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Political Party with Id - 2 not Found!", System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value());

		when(this.partyService.deleteParty(2L)).thenThrow(new PoliticalPartyNotFoundException("Political Party with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/party/2")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		myAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}
	@Test
	public void testGetByIdPoliticalPartyNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Political Party with Id - 2 not Found!", System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value());
		
		when(this.partyService.getPartyById(2L)).thenThrow(new PoliticalPartyNotFoundException("Political Party with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/party/2")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		myAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
		
	}

}
