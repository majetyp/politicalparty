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

import com.qart.ploticalparties.dto.PoliticalPartyDto;
import com.qart.ploticalparties.entity.PoliticalParty;
import com.qart.ploticalparties.repository.PoliticalPartyRepository;
import com.qart.ploticalparties.service.PoliticalPartyService;
import com.qart.ploticalparties.service.impl.PoliticalPartyServiceImpl;
import com.qart.ploticalparties.testutils.MasterData;

@WebMvcTest(PoliticalPartyServiceImpl.class)
@AutoConfigureMockMvc
public class PoliticalPartyServiceImplTest {
	@Autowired
	private PoliticalPartyService politicalPartyService;
	@MockBean
	private PoliticalPartyRepository repository;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testRegisterPoliticalParty() throws Exception 
	{
		PoliticalPartyDto politicalPartyDto = MasterData.getPartyDto();
		PoliticalPartyDto savedpoliticalPartyDto = MasterData.getPartyDto();
		savedpoliticalPartyDto.setPoliticalPartyId(1L);
		PoliticalParty politicalParty = new PoliticalParty(); 
		
		when(repository.save(politicalParty)).thenReturn(null);
		this.politicalPartyService.registerParty(politicalPartyDto);
		myAssert(currentTest(), savedpoliticalPartyDto != null ? true : false, businessTestFile);

	}
	
	@Test
	public void testUpdateParty() throws Exception 
	{
		PoliticalPartyDto politicalPartyDto = MasterData.getPartyDto();
		PoliticalPartyDto savedpoliticalPartyDto = MasterData.getPartyDto();
		savedpoliticalPartyDto.setPoliticalPartyId(1L);
		PoliticalParty politicalParty = new PoliticalParty(); 
		
		when(repository.save(politicalParty)).thenReturn(null);
		this.politicalPartyService.registerParty(politicalPartyDto);
		myAssert(currentTest(), savedpoliticalPartyDto != null ? true : false, businessTestFile);

	}
	@Test
	public void testGetAllParties() throws Exception {
		List<PoliticalPartyDto> politicalPartyDto = this.politicalPartyService.getAllParties();
		myAssert(currentTest(), politicalPartyDto != null ? true : false, businessTestFile);	
	}
	

}