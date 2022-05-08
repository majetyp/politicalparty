package com.qart.ploticalparties.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qart.ploticalparties.dto.PoliticalPartyDto;
import com.qart.ploticalparties.entity.PoliticalParty;
import com.qart.ploticalparties.exceptions.InvalidDataException;
import com.qart.ploticalparties.exceptions.PoliticalPartyNotFoundException;
import com.qart.ploticalparties.repository.PoliticalPartyRepository;
import com.qart.ploticalparties.service.PoliticalPartyService;

@Service
public class PoliticalPartyServiceImpl implements PoliticalPartyService {

	@Autowired
	private PoliticalPartyRepository politicalPartyRepository;

    @Transactional
	@Override
	public PoliticalPartyDto registerParty(PoliticalPartyDto politicalPartyDto) {
       	PoliticalParty politicalParty = new PoliticalParty(); 
    
      
		BeanUtils.copyProperties(politicalPartyDto, politicalParty);
	   
        politicalPartyRepository.save(politicalParty);
      
      		return politicalPartyDto;
	}

    @Transactional
	@Override
	public PoliticalPartyDto updateParty(PoliticalPartyDto politicalPartyDto) {
       	PoliticalParty politicalParty = new PoliticalParty();
		BeanUtils.copyProperties(politicalPartyDto, politicalParty);
		politicalPartyRepository.save(politicalParty);
		return politicalPartyDto;
	}

    @Transactional
	@Override
	public boolean deleteParty(Long partyId) {
        if(!politicalPartyRepository.existsById(partyId)){
            throw new PoliticalPartyNotFoundException("Partid does not exist")  ;  }
		PoliticalPartyDto politicalPartyDto = getPartyById(partyId);
		PoliticalParty politicalParty = new PoliticalParty();
		BeanUtils.copyProperties(politicalPartyDto, politicalParty);
		politicalPartyRepository.delete(politicalParty);
		return true;
	}

	@Override
	public PoliticalPartyDto getPartyById(Long partyId) {
          if(!politicalPartyRepository.existsById(partyId)){throw new PoliticalPartyNotFoundException("Partid does not exist")  ;  }
		
		Optional<PoliticalParty> politicalParty = politicalPartyRepository.findById(partyId);
		if (politicalParty.isPresent()) {
			PoliticalPartyDto politicalPartyDto = new PoliticalPartyDto();
			BeanUtils.copyProperties(politicalParty.get(), politicalPartyDto);
			return politicalPartyDto;
		} else {
			throw new PoliticalPartyNotFoundException("Party with Id " + partyId + " not found");
		}
	}

	@Override
	public List<PoliticalPartyDto> getAllParties() {
		List<PoliticalParty> politicalParties = politicalPartyRepository.findAll();
		List<PoliticalPartyDto> politicalPartyDtos = new ArrayList<>();
		for (PoliticalParty politicalParty : politicalParties) {
			PoliticalPartyDto politicalPartyDto = new PoliticalPartyDto();
			BeanUtils.copyProperties(politicalParty, politicalPartyDto);
			politicalPartyDtos.add(politicalPartyDto);
		}
		return politicalPartyDtos;
	}


}
