package com.qart.ploticalparties;

import static com.qart.ploticalparties.testutils.TestUtils.businessTestFile;
import static com.qart.ploticalparties.testutils.TestUtils.currentTest;
import static com.qart.ploticalparties.testutils.TestUtils.testReport;
import static com.qart.ploticalparties.testutils.TestUtils.myAssert;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.qart.ploticalparties.controller.DevelopmentController;
import com.qart.ploticalparties.dto.DevelopmentDto;
import com.qart.ploticalparties.service.DevelopmentService;
import com.qart.ploticalparties.service.impl.DevelopmentServiceImpl;
import com.qart.ploticalparties.testutils.MasterData;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


@WebMvcTest(DevelopmentServiceImpl.class)
@AutoConfigureMockMvc
public class DevelopmentServiceImplTest{
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DevelopmentService developmentService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testcreateDevelopment() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		DevelopmentDto savedDevelopmentDto = MasterData.getDevelopmentDto();

		savedDevelopmentDto.setPoliticalLeaderId(1L);

		when(this.developmentService.createDevelopment(developmentDto)).thenReturn(savedDevelopmentDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/development")
				.content(MasterData.asJsonString(developmentDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		myAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedDevelopmentDto))
						? "true"
						: "false"),
				businessTestFile);

	}

	
	
}
