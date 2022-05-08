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

import com.qart.ploticalparties.controller.PoliticalLeaderController;
import com.qart.ploticalparties.dto.PoliticalLeaderDto;
import com.qart.ploticalparties.exceptions.PoliticalLeaderNotFoundException;
import com.qart.ploticalparties.model.exception.ExceptionResponse;
import com.qart.ploticalparties.service.PoliticalLeaderService;
import com.qart.ploticalparties.testutils.MasterData;

@WebMvcTest(PoliticalLeaderController.class)
@AutoConfigureMockMvc
public class PoliticalLeaderExceptionTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PoliticalLeaderService leaderService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testRegisterPoliticalLeaderInvalidDataException() throws Exception {
		PoliticalLeaderDto politicalLeaderDto = MasterData.getPoliticalLeaderDto();
		PoliticalLeaderDto savedPoliticalLeaderDto = MasterData.getPoliticalLeaderDto();
		savedPoliticalLeaderDto.setPoliticalPartyId(1L);

        politicalLeaderDto.setCandidateName("Ab");
               
		when(this.leaderService.registerPoliticalLeader(politicalLeaderDto)).thenReturn(savedPoliticalLeaderDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/leaders/leader/register-leader")
				.content(MasterData.asJsonString(politicalLeaderDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		myAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testUpdatePoliticalLeaderInvalidDataException() throws Exception {
		PoliticalLeaderDto politicalLeaderDto = MasterData.getPoliticalLeaderDto();
		PoliticalLeaderDto savedPoliticalLeaderDto = MasterData.getPoliticalLeaderDto();
		savedPoliticalLeaderDto.setPoliticalPartyId(1L);
		politicalLeaderDto.setCandidateName("Ab");
		when(this.leaderService.registerPoliticalLeader(politicalLeaderDto)).thenReturn(savedPoliticalLeaderDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/leaders/leader/update-leader")
				.content(MasterData.asJsonString(politicalLeaderDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		myAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testDeletePoliticalLeaderNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Political Leader with Id - 2 not Found!",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());

		when(this.leaderService.deletePoliticalLeader(2L))
				.thenThrow(new PoliticalLeaderNotFoundException("Political Leader with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/leaders/party/delete/2")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		myAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testGetPoliticalLeaderByIdPoliticalLeaderNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Political Leader with Id - 2 not Found!",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());

		when(this.leaderService.getPoliticalLeaderById(2L))
				.thenThrow(new PoliticalLeaderNotFoundException("Political Leader with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/leaders/leader/2")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		myAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}


}
