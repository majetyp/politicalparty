package com.qart.ploticalparties.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import javax.activity.InvalidActivityException;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.qart.ploticalparties.dto.PoliticalLeaderDto;
import com.qart.ploticalparties.entity.PoliticalLeader;
import com.qart.ploticalparties.exceptions.InvalidDataException;
import com.qart.ploticalparties.exceptions.PoliticalLeaderNotFoundException;
import com.qart.ploticalparties.repository.PoliticalLeaderRepository;
import com.qart.ploticalparties.service.PoliticalLeaderService;

@Service
public class PoliticalLeaderServiceImpl implements PoliticalLeaderService {

	@Autowired
	private PoliticalLeaderRepository repository;

    
	@Override
	public PoliticalLeaderDto registerPoliticalLeader(PoliticalLeaderDto leaderDto) { 
         if(leaderDto!=null) {
			//if(!repository.existsById(leaderDto.getPoliticalLeaderId())) {
			//	throw new PoliticalLeaderNotFoundException("PoliticalLeaderId#"+leaderDto.getPoliticalLeaderId()+" does not exists");
          //  }               
		PoliticalLeader politicalLeader = new PoliticalLeader();
		BeanUtils.copyProperties(leaderDto, politicalLeader);
        repository.save(politicalLeader);
    }
		return leaderDto;
	}

    @Transactional
	@Override
	public PoliticalLeaderDto updatePoliticalLeader(PoliticalLeaderDto leaderDto) {
        if(leaderDto!=null) {
			if(!repository.existsById(leaderDto.getPoliticalLeaderId())) {
				throw new InvalidDataException("getPoliticalLeaderId#"+leaderDto.getPoliticalLeaderId()+" does not exists");
            }
		PoliticalLeader politicalLeader = new PoliticalLeader();
		BeanUtils.copyProperties(leaderDto, politicalLeader);
		repository.save(politicalLeader);}
		return leaderDto;
	}

    @Transactional
	@Override
	public boolean deletePoliticalLeader(Long politicalLeaderId) {
        if(!repository.existsById(politicalLeaderId)) {
			throw new PoliticalLeaderNotFoundException("politicalLeaderid#"+politicalLeaderId+" does not exists");
		}
		PoliticalLeaderDto politicalLeaderById = getPoliticalLeaderById(politicalLeaderId);
		PoliticalLeader politicalLeader = new PoliticalLeader();
		BeanUtils.copyProperties(politicalLeaderById, politicalLeader);
		repository.delete(politicalLeader);
		return true;
	}

	@Override
	public PoliticalLeaderDto getPoliticalLeaderById(Long politicalLeaderId) {
        if(!repository.existsById(politicalLeaderId)) {
			throw new PoliticalLeaderNotFoundException("politicalLeaderid#"+politicalLeaderId+" does not exists");
		}
		Optional<PoliticalLeader> politicalLeader = repository.findById(politicalLeaderId);
		if (politicalLeader.isPresent()) {
			PoliticalLeaderDto politicalLeaderDto = new PoliticalLeaderDto();
			BeanUtils.copyProperties(politicalLeader.get(), politicalLeaderDto);
			return politicalLeaderDto;
		} else {
			throw new PoliticalLeaderNotFoundException("Political Leader with id " + politicalLeaderId + " not found");
		}

	}

	@Override
	public List<PoliticalLeaderDto> getAllPoliticalLeaders() {
		List<PoliticalLeader> politicalLeaders = repository.findAll();
		List<PoliticalLeaderDto> politicalLeaderDtos = new ArrayList<>();
		for (PoliticalLeader leader : politicalLeaders) {
			PoliticalLeaderDto politicalLeaderDto = new PoliticalLeaderDto();
			BeanUtils.copyProperties(leader, politicalLeaderDto);
			politicalLeaderDtos.add(politicalLeaderDto);
		}
		return politicalLeaderDtos;
	}

	@Override
	public List<PoliticalLeaderDto> getPoliticalLeadersByPartyId(Long politicalLeaderId) {
		List<PoliticalLeader> politicalLeaders = repository.findByPoliticalPartyId(politicalLeaderId);
		List<PoliticalLeaderDto> politicalLeaderDtos = new ArrayList<>();
		for (PoliticalLeader leader : politicalLeaders) {
			PoliticalLeaderDto politicalLeaderDto = new PoliticalLeaderDto();
			BeanUtils.copyProperties(leader, politicalLeaderDto);
			politicalLeaderDtos.add(politicalLeaderDto);
		}
		return politicalLeaderDtos;

	}

}
