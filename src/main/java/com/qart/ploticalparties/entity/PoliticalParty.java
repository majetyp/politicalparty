package com.qart.ploticalparties.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "politicalparty")
public class PoliticalParty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long politicalPartyId;

	@Column
	private String partyName;

	@Column
	private String founderName;

	@Column
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

	public String getFounderName() {
		return founderName;
	}

	public void setFounderName(String founderName) {
		this.founderName = founderName;
	}

	public void setFirstYear(Integer firstYear) {
		this.firstYear = firstYear;
	}

	public Integer getFirstYear() {
		return firstYear;
	}

}
