package com.qart.ploticalparties.boundary;

import static com.qart.ploticalparties.testutils.TestUtils.boundaryTestFile;
import static com.qart.ploticalparties.testutils.TestUtils.currentTest;
import static com.qart.ploticalparties.testutils.TestUtils.testReport;
import static com.qart.ploticalparties.testutils.TestUtils.myAssert;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.qart.ploticalparties.dto.DevelopmentDto;
import com.qart.ploticalparties.dto.PoliticalLeaderDto;
import com.qart.ploticalparties.dto.PoliticalPartyDto;
import com.qart.ploticalparties.testutils.MasterData;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {
	private static Validator validator;

	// ----------------------------------------------------------------------------------------------
	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testPoliticalPartyNameNotNull() throws Exception {
		PoliticalPartyDto politicalPartyDto = MasterData.getPartyDto();
		politicalPartyDto.setPartyName("");
		Set<ConstraintViolation<PoliticalPartyDto>> violations = validator.validate(politicalPartyDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPoliticalPartyNameMinThree() throws Exception {
		PoliticalPartyDto politicalPartyDto = MasterData.getPartyDto();
		politicalPartyDto.setPartyName("Ab");
		Set<ConstraintViolation<PoliticalPartyDto>> violations = validator.validate(politicalPartyDto);

		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPoliticalPartyNameMaxHundred() throws Exception {
		PoliticalPartyDto politicalPartyDto = MasterData.getPartyDto();
		String partyName = "";
		for (int i = 0; i < 120; i++) {
			partyName.concat("A");
		}
		politicalPartyDto.setPartyName(partyName);
		Set<ConstraintViolation<PoliticalPartyDto>> violations = validator.validate(politicalPartyDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPartyFounderNameNotNull() throws Exception {
		PoliticalPartyDto politicalPartyDto = MasterData.getPartyDto();
		politicalPartyDto.setFounderName("");
		Set<ConstraintViolation<PoliticalPartyDto>> violations = validator.validate(politicalPartyDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPartyFounderNameMinThree() throws Exception {
		PoliticalPartyDto politicalPartyDto = MasterData.getPartyDto();
		politicalPartyDto.setFounderName("Ab");
		Set<ConstraintViolation<PoliticalPartyDto>> violations = validator.validate(politicalPartyDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPartyFounderNameMaxHundred() throws Exception {
		PoliticalPartyDto politicalPartyDto = MasterData.getPartyDto();
		String founderName = "";
		for (int i = 0; i < 120; i++) {
			founderName.concat("A");
		}
		politicalPartyDto.setFounderName(founderName);
		Set<ConstraintViolation<PoliticalPartyDto>> violations = validator.validate(politicalPartyDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPoliticalLeaderPartyIdNotNull() throws Exception {
		PoliticalLeaderDto politicalLeaderDto = MasterData.getPoliticalLeaderDto();
		politicalLeaderDto.setPoliticalPartyId(null);
		Set<ConstraintViolation<PoliticalLeaderDto>> violations = validator.validate(politicalLeaderDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPoliticalLeaderCandidateNameNotNull() throws Exception {
		PoliticalLeaderDto politicalLeaderDto = MasterData.getPoliticalLeaderDto();
		politicalLeaderDto.setCandidateName("");
		Set<ConstraintViolation<PoliticalLeaderDto>> violations = validator.validate(politicalLeaderDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPoliticalLeaderCandidateNameMinThree() throws Exception {
		PoliticalLeaderDto politicalLeaderDto = MasterData.getPoliticalLeaderDto();
		politicalLeaderDto.setCandidateName("Ab");
		Set<ConstraintViolation<PoliticalLeaderDto>> violations = validator.validate(politicalLeaderDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPoliticalLeaderCandidateNameMaxHundred() throws Exception {
		PoliticalLeaderDto politicalLeaderDto = MasterData.getPoliticalLeaderDto();
		String candidateName = "";
		for (int i = 0; i < 120; i++) {
			candidateName.concat("A");
		}
		politicalLeaderDto.setCandidateName(candidateName);
		Set<ConstraintViolation<PoliticalLeaderDto>> violations = validator.validate(politicalLeaderDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPoliticalLeaderCandidateStateNotNull() throws Exception {
		PoliticalLeaderDto politicalLeaderDto = MasterData.getPoliticalLeaderDto();
		politicalLeaderDto.setCandidateState("");
		Set<ConstraintViolation<PoliticalLeaderDto>> violations = validator.validate(politicalLeaderDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPoliticalLeaderCandidateStateMinThree() throws Exception {
		PoliticalLeaderDto politicalLeaderDto = MasterData.getPoliticalLeaderDto();
		politicalLeaderDto.setCandidateState("Ab");
		Set<ConstraintViolation<PoliticalLeaderDto>> violations = validator.validate(politicalLeaderDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPoliticalLeaderCandidateStateMaxHundred() throws Exception {
		PoliticalLeaderDto politicalLeaderDto = MasterData.getPoliticalLeaderDto();
		String candidateState = "";
		for (int i = 0; i < 120; i++) {
			candidateState.concat("A");
		}
		politicalLeaderDto.setCandidateState(candidateState);
		Set<ConstraintViolation<PoliticalLeaderDto>> violations = validator.validate(politicalLeaderDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentPoliticalLeaderIdNotNull() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setPoliticalLeaderId(null);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentTitleNotNull() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setTitle("");
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentTitleMinThree() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setTitle("Ab");
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentTitleMaxHundred() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		String title = "";
		for (int i = 0; i < 120; i++) {
			title.concat("A");
		}
		developmentDto.setTitle(title);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentActivityNotNull() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setActivity("");
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentActivityMinThree() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setActivity("Ab");
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentActivityMaxHundred() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		String activity = "";
		for (int i = 0; i < 120; i++) {
			activity.concat("A");
		}
		developmentDto.setActivity(activity);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentBudgetNotNull() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setBudget("");
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentBudgetMinThree() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setBudget("Ab");
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentBudgetMaxHundred() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		String budget = "";
		for (int i = 0; i < 120; i++) {
			budget.concat("A");
		}
		developmentDto.setBudget(budget);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentStateNotNull() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setState("");
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentStateMinThree() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setState("Ab");
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentStateMaxHundred() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		String state = "";
		for (int i = 0; i < 120; i++) {
			state.concat("A");
		}
		developmentDto.setState(state);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentActivityMonthNotNull() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setActivityMonth(null);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentActivityMonthMinOne() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setActivityMonth(0);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentActivityMonthMaxTwelve() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();

		developmentDto.setActivityMonth(13);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentActivityYearNotNull() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setActivityYear(null);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentActivityYearMin2021() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setActivityYear(2020);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentActivityYearMax2040() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();

		developmentDto.setActivityYear(2045);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	
	
	@Test
	public void testPartyFoundationYearMin() throws Exception {
		PoliticalPartyDto partyDto = MasterData.getPartyDto();

		partyDto.setFirstYear(1857);
		Set<ConstraintViolation<PoliticalPartyDto>> violations = validator.validate(partyDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	
	@Test
	public void testPartyFoundationYearMax() throws Exception {
		PoliticalPartyDto partyDto = MasterData.getPartyDto();

		partyDto.setFirstYear(2050);
		Set<ConstraintViolation<PoliticalPartyDto>> violations = validator.validate(partyDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	
	@Test
	public void testPartyFoundationYearNotNull() throws Exception {
		PoliticalPartyDto partyDto = MasterData.getPartyDto();

		partyDto.setFirstYear(null);
		Set<ConstraintViolation<PoliticalPartyDto>> violations = validator.validate(partyDto);
		myAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
		
	}



}
