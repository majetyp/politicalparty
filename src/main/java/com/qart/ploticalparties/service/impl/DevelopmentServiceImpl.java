package com.qart.ploticalparties.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qart.ploticalparties.dto.DevelopmentDto;
import com.qart.ploticalparties.entity.Development;
import com.qart.ploticalparties.exceptions.DevelopmentNotFoundException;
import com.qart.ploticalparties.repository.DevelopmentRepository;
import com.qart.ploticalparties.service.DevelopmentService;

@Service
public class DevelopmentServiceImpl implements DevelopmentService {

	@Autowired
	private DevelopmentRepository repository;

	@Override
	public DevelopmentDto createDevelopment(DevelopmentDto developmentDto) {
		if(developmentDto!=null){	
        if(repository.existsById(developmentDto.getDevelopmentId())){throw new DevelopmentNotFoundException("Development with Id "+developmentDto.getDevelopmentId()+"  already exist");}
        Development development=new Development();
       
        BeanUtils.copyProperties(developmentDto,development);
        repository.save(development);
    }
		return developmentDto;
    }
	
    @Transactional 
	@Override
	public DevelopmentDto updateDevelopment(DevelopmentDto developmentDto) {
    if(developmentDto!=null){	
        if(!repository.existsById(developmentDto.getDevelopmentId())){throw new DevelopmentNotFoundException("Development with Id "+developmentDto.getDevelopmentId()+"  Does not exist");}
        Development development=new Development();
        BeanUtils.copyProperties(developmentDto,development);
        repository.save(development);
    }
		return developmentDto;
    }
    
    @Transactional  
	@Override
	public boolean deleteDevelopment(Long developmentId) {
        if(!repository.existsById(developmentId)){
            throw new DevelopmentNotFoundException("Developmentid#"+developmentId+" does not exists");
        }
        DevelopmentDto developmentById=getDevelopmentById(developmentId);
        Development development=new Development();
        BeanUtils.copyProperties(developmentById, development);
        repository.delete(development);
		return true;
	}

	@Override
	public DevelopmentDto getDevelopmentById(Long developmentId) {
         if(!repository.existsById(developmentId)) {
			throw new DevelopmentNotFoundException("Developmentid#"+developmentId+" does not exists");
        }
        Optional<Development> development = repository.findById(developmentId);
        if(development.isPresent()){
           DevelopmentDto developmentDto=new DevelopmentDto(); 
           BeanUtils.copyProperties(development.get(), developmentDto);
           return developmentDto;
        }else{
            throw new DevelopmentNotFoundException("Development with id"+developmentId+" Not found");
        }
		
	}

	@Override
	public List<DevelopmentDto> getAllDevelopments() {
        List<Development> developments = repository.findAll();
		List<DevelopmentDto> developmentDtos = new ArrayList<>();
		for (Development dev : developments) {
			DevelopmentDto developmentDto = new DevelopmentDto();
			BeanUtils.copyProperties(dev, developmentDto);
			developmentDtos.add(developmentDto);
		}
		return developmentDtos;
	}

	@Override
	public List<DevelopmentDto> getAllDevelopmentsByLeaderId(Long politicalLeaderId) {
        List<Development> developments = repository.findByPoliticalLeaderId(politicalLeaderId);
        List<DevelopmentDto> developmentDtos = new ArrayList<>();
        for (Development dev : developments) {
			DevelopmentDto developmentDto = new DevelopmentDto();
            BeanUtils.copyProperties(dev, developmentDto);
            developmentDtos.add(developmentDto);}
		return developmentDtos;
	}

}
