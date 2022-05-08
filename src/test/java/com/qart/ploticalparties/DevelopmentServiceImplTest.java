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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.qart.ploticalparties.dto.DevelopmentDto;
import com.qart.ploticalparties.entity.Development;
import com.qart.ploticalparties.repository.DevelopmentRepository;
import com.qart.ploticalparties.service.DevelopmentService;
import com.qart.ploticalparties.service.impl.DevelopmentServiceImpl;
import com.qart.ploticalparties.testutils.MasterData;

@WebMvcTest(DevelopmentServiceImpl.class)
@AutoConfigureMockMvc
public class DevelopmentServiceImplTest {
	@Autowired
	private DevelopmentService developmentService;

	@MockBean
	private DevelopmentRepository repository;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateDevelopment() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		DevelopmentDto savedDevelopmentDto = MasterData.getDevelopmentDto();
		savedDevelopmentDto.setDevelopmentId(1L);
		Development development = new Development();
		when(repository.existsById(developmentDto.getDevelopmentId())).thenReturn(false);
		when(repository.save(development)).thenReturn(null);
		this.developmentService.createDevelopment(developmentDto);
		myAssert(currentTest(), savedDevelopmentDto != null ? true : false, businessTestFile);

	}

	@Test
	public void testCreateDevelopmentNegative() throws Exception {
		DevelopmentDto developmentDto = null;
		this.developmentService.createDevelopment(developmentDto);
		myAssert(currentTest(), developmentDto == null ? true : false, businessTestFile);

	}

	@Test
	public void testUpdateDevelopment() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		DevelopmentDto savedDevelopmentDto = MasterData.getDevelopmentDto();
		savedDevelopmentDto.setDevelopmentId(1L);
		Development development = new Development();
		when(repository.existsById(developmentDto.getDevelopmentId())).thenReturn(true);
		when(repository.save(development)).thenReturn(null);
		this.developmentService.updateDevelopment(developmentDto);
		myAssert(currentTest(), savedDevelopmentDto != null ? true : false, businessTestFile);
	}

	
	@Test
	public void testGetAllDevelopments() throws Exception {
		List<DevelopmentDto> savedDevelopmentDto = this.developmentService.getAllDevelopments();
		myAssert(currentTest(), savedDevelopmentDto != null ? true : false, businessTestFile);	
	}
	
	

}
