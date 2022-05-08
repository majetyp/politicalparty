package com.qart.ploticalparties.functional;

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
import com.qart.ploticalparties.testutils.MasterData;

@WebMvcTest(DevelopmentController.class)
@AutoConfigureMockMvc
public class DevelopmentControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DevelopmentService developmentService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testRegisterDevelopment() throws Exception {
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

	@Test
	public void testRegisterPoliticalLeaderIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		DevelopmentDto savedDevelopmentDto = MasterData.getDevelopmentDto();

		savedDevelopmentDto.setPoliticalLeaderId(1L);

		when(developmentService.createDevelopment(developmentDto)).then(new Answer<DevelopmentDto>() {

			@Override
			public DevelopmentDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedDevelopmentDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/development")
				.content(MasterData.asJsonString(developmentDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		myAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetAllDevelopments() throws Exception {
		List<DevelopmentDto> developmentDtos = MasterData.getDevelopmentDtoList();

		when(this.developmentService.getAllDevelopments()).thenReturn(developmentDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/development/getAllDevelopmentActivities")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		myAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(developmentDtos))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetAllDevelopmentsIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<DevelopmentDto> developmentDtos = MasterData.getDevelopmentDtoList();
		when(this.developmentService.getAllDevelopments()).then(new Answer<List<DevelopmentDto>>() {

			@Override
			public List<DevelopmentDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return developmentDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/development/getAllDevelopmentActivities")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		myAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetDevelopmentById() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();

		developmentDto.setDevelopmentId(1L);
		when(this.developmentService.getDevelopmentById(1L)).thenReturn(developmentDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/development/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		myAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(developmentDto))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetDevelopmentByIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setDevelopmentId(1L);
		when(this.developmentService.getDevelopmentById(1L)).then(new Answer<DevelopmentDto>() {

			@Override
			public DevelopmentDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return developmentDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/development/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		myAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testUpdateDevelopment() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		DevelopmentDto savedDevelopmentDto = MasterData.getDevelopmentDto();

		savedDevelopmentDto.setPoliticalLeaderId(1L);

		when(this.developmentService.updateDevelopment(developmentDto)).thenReturn(savedDevelopmentDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/development/update-development")
				.content(MasterData.asJsonString(developmentDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		myAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedDevelopmentDto))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testUpdatePoliticalLeaderIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		DevelopmentDto savedDevelopmentDto = MasterData.getDevelopmentDto();

		savedDevelopmentDto.setPoliticalLeaderId(1L);

		when(developmentService.updateDevelopment(developmentDto)).then(new Answer<DevelopmentDto>() {

			@Override
			public DevelopmentDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedDevelopmentDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/development/update-development")
				.content(MasterData.asJsonString(developmentDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		myAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testDeleteDevelopment() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setDevelopmentId(1L);

		when(this.developmentService.deleteDevelopment(1L)).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/development/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		myAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(true)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testDeleteDevelopmentIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setDevelopmentId(1L);
		when(developmentService.deleteDevelopment(1L)).then(new Answer<Boolean>() {

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return true;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/development/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		myAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetAllDevelopmentsByPoliticalLeaderId() throws Exception {
		List<DevelopmentDto> developmentDtos = MasterData.getDevelopmentDtoList();

		when(this.developmentService.getAllDevelopmentsByLeaderId(1L)).thenReturn(developmentDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/development/get-developments-by-leaderid/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
                
        myAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(developmentDtos))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetAllDevelopmentsByPoliticalLeaderIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<DevelopmentDto> developmentDtos = MasterData.getDevelopmentDtoList();
		when(this.developmentService.getAllDevelopmentsByLeaderId(1L)).then(new Answer<List<DevelopmentDto>>() {

			@Override
			public List<DevelopmentDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return developmentDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/development/get-developments-by-leaderid/1")
				
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		myAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}
}
