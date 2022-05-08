package com.qart.ploticalparties.dto;

import java.util.Objects;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class PoliticalPartyDto {
     @NotNull 
     @Min(value=1,message="politicalPartyId can not be negative")
    private Long politicalPartyId;
    
    @NotEmpty
    @NotNull(message="partyName is mandate")
	@Length(min = 5, max = 100)
    private String partyName;
    
    @NotEmpty
    @NotNull(message="founderName is mandate")
	@Length(min = 5, max = 100)
    private String founderName;
    
    
    @NotNull(message="foundation Year is mandate")
     @Min(value=1900)    
    @Max(value=2040)
    private Integer firstYear;
    


	public Long getPoliticalPartyId() {
		return politicalPartyId;
	}

	public void setPoliticalPartyId(Long partyId) {
		this.politicalPartyId = partyId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public Integer getFirstYear() {
		return firstYear;
	}

	public void setFirstYear(Integer foundationYear) {
		this.firstYear = foundationYear;
	}

	public String getFounderName() {
		return founderName;
	}

	public void setFounderName(String founderName) {
		this.founderName = founderName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(founderName, politicalPartyId, partyName,firstYear);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PoliticalPartyDto other = (PoliticalPartyDto) obj;
		return Objects.equals(founderName, other.founderName) && Objects.equals(politicalPartyId, other.politicalPartyId)
				&& Objects.equals(partyName, other.partyName)&& Objects.equals(firstYear,other.firstYear);
	}

}
