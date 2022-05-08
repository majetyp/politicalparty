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

import com.qart.ploticalparties.controller.DevelopmentController;
import com.qart.ploticalparties.dto.DevelopmentDto;
import com.qart.ploticalparties.exceptions.PoliticalLeaderNotFoundException;
import com.qart.ploticalparties.model.exception.ExceptionResponse;
import com.qart.ploticalparties.service.DevelopmentService;
import com.qart.ploticalparties.testutils.MasterData;

@WebMvcTest(DevelopmentController.class)
@AutoConfigureMockMvc
public class DevelopmentExceptionTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DevelopmentService developmentService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateDevelopmentInvalidDataException() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		DevelopmentDto savedDevelopmentDto = MasterData.getDevelopmentDto();
		savedDevelopmentDto.setDevelopmentId(1L);

		developmentDto.setActivity("Ab");

		when(this.developmentService.createDevelopment(developmentDto)).thenReturn(savedDevelopmentDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/development")
				.content(MasterData.asJsonString(developmentDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
       
		myAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testUpdateDevelopmentInvalidDataException() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		DevelopmentDto savedDevelopmentDto = MasterData.getDevelopmentDto();
		savedDevelopmentDto.setDevelopmentId(1L);

		developmentDto.setActivity("Ab");

		when(this.developmentService.createDevelopment(developmentDto)).thenReturn(savedDevelopmentDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/development/update-development")
				.content(MasterData.asJsonString(developmentDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		myAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testDeleteDevelopmentNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Development with Id - 2 not Found!",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());

		when(this.developmentService.deleteDevelopment(2L))
				.thenThrow(new PoliticalLeaderNotFoundException("Development with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/development/2")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		myAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}
	@Test
	public void testGetDevelopmentByIdDevelopmentNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Development with Id - 2 not Found!",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.developmentService.getDevelopmentById(2L))
		.thenThrow(new PoliticalLeaderNotFoundException("Development with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/development/2")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		myAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
		
	}

	

}
