package com.qart.ploticalparties.service;

import java.util.List;

import com.qart.ploticalparties.dto.PoliticalLeaderDto;
import com.qart.ploticalparties.dto.PoliticalPartyDto;

public interface PoliticalPartyService {

	public PoliticalPartyDto registerParty(PoliticalPartyDto politicalPartyDto);

	public PoliticalPartyDto updateParty(PoliticalPartyDto politicalPartyDto);

	public boolean deleteParty(Long partyId);

	public PoliticalPartyDto getPartyById(Long partyId);

	public List<PoliticalPartyDto> getAllParties();


}
