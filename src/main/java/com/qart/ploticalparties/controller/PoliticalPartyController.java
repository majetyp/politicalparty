package com.qart.ploticalparties.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qart.ploticalparties.dto.PoliticalPartyDto;
import com.qart.ploticalparties.exceptions.InvalidDataException;
import com.qart.ploticalparties.service.PoliticalPartyService;

@RestController
@RequestMapping("/party")
public class PoliticalPartyController {

	@Autowired
	private PoliticalPartyService politicalPartyService;

	@PostMapping
	public ResponseEntity<PoliticalPartyDto> createParty(@Valid @RequestBody PoliticalPartyDto politicalPartyDto, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result);
			throw new InvalidDataException("Party Data is not Valid");
		}
		politicalPartyService.registerParty(politicalPartyDto);
		return ResponseEntity.ok(politicalPartyDto);

	}

	@PutMapping
	public ResponseEntity<PoliticalPartyDto> updateParty(@Valid @RequestBody PoliticalPartyDto politicalPartyDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("Party Data is not Valid");
		}
		politicalPartyService.updateParty(politicalPartyDto);
		return ResponseEntity.ok(politicalPartyDto);

	}

	@DeleteMapping("/{partyId}")
	public ResponseEntity<Boolean> deleteParty(@PathVariable("partyId") Long partyid) {
		boolean result = politicalPartyService.deleteParty(partyid);
		return ResponseEntity.ok(result);

	}

	
	  @GetMapping("/{partyId}") public ResponseEntity<PoliticalPartyDto>
	  getPartyById(@PathVariable("partyId") Long partyId) { PoliticalPartyDto
	  politicalPartyDto = politicalPartyService.getPartyById(partyId); return
	  ResponseEntity.ok(politicalPartyDto); }
	  
	  @GetMapping public ResponseEntity<List<PoliticalPartyDto>> getAllParties() {
	  List<PoliticalPartyDto> politicalPartyDtos =
	  politicalPartyService.getAllParties(); return
	  ResponseEntity.ok(politicalPartyDtos); }
	 
}
