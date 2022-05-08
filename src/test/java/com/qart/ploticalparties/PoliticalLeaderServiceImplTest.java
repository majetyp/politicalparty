package com.qart.ploticalparties;

import static com.qart.ploticalparties.testutils.TestUtils.businessTestFile;
import static com.qart.ploticalparties.testutils.TestUtils.currentTest;
import static com.qart.ploticalparties.testutils.TestUtils.testReport;
import static com.qart.ploticalparties.testutils.TestUtils.myAssert;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qart.ploticalparties.dto.DevelopmentDto;
import com.qart.ploticalparties.dto.PoliticalLeaderDto;
import com.qart.ploticalparties.entity.PoliticalLeader;
import com.qart.ploticalparties.repository.PoliticalLeaderRepository;
import com.qart.ploticalparties.service.PoliticalLeaderService;
import com.qart.ploticalparties.service.impl.PoliticalLeaderServiceImpl;
import com.qart.ploticalparties.testutils.MasterData;

@WebMvcTest(PoliticalLeaderServiceImpl.class)
@AutoConfigureMockMvc
public class PoliticalLeaderServiceImplTest {
	@Autowired
	private PoliticalLeaderService politicalLeaderServie;
	@MockBean
	private PoliticalLeaderRepository repository;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testRegisterPoliticalLeader() throws Exception 
	{
		PoliticalLeaderDto politicalLeaderDto = MasterData.getPoliticalLeaderDto();
		PoliticalLeaderDto savedpoliticalLeaderDto = MasterData.getPoliticalLeaderDto();
		savedpoliticalLeaderDto.setPoliticalLeaderId(1L);
		PoliticalLeader politicalLeader = new PoliticalLeader();
		
		when(repository.save(politicalLeader)).thenReturn(null);
		this.politicalLeaderServie.registerPoliticalLeader(politicalLeaderDto);
		myAssert(currentTest(), savedpoliticalLeaderDto != null ? true : false, businessTestFile);

	}
	
	@Test
	public void testUpdatePoliticalLeader() throws Exception 
	{
		PoliticalLeaderDto politicalLeaderDto = MasterData.getPoliticalLeaderDto();
		PoliticalLeaderDto savedpoliticalLeaderDto = MasterData.getPoliticalLeaderDto();
		savedpoliticalLeaderDto.setPoliticalLeaderId(1L);
		PoliticalLeader politicalLeader = new PoliticalLeader();
		when(repository.existsById(politicalLeaderDto.getPoliticalLeaderId())).thenReturn(true);
		when(repository.save(politicalLeader)).thenReturn(null);
		this.politicalLeaderServie.updatePoliticalLeader(politicalLeaderDto);
		myAssert(currentTest(), savedpoliticalLeaderDto != null ? true : false, businessTestFile);
		
		
	}
	
	@Test
	public void testGetAllPoliticalLeaders() throws Exception {
		//PoliticalLeaderDto politicalLeaderDto = MasterData.getPoliticalLeaderDtoList();
		List<PoliticalLeaderDto> politicalLeaderDto = this.politicalLeaderServie.getAllPoliticalLeaders();
		myAssert(currentTest(), politicalLeaderDto != null ? true : false, businessTestFile);	
	}
	

}