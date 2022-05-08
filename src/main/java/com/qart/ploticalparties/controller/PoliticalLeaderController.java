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

import com.qart.ploticalparties.dto.PoliticalLeaderDto;
import com.qart.ploticalparties.exceptions.InvalidDataException;
import com.qart.ploticalparties.service.PoliticalLeaderService;

@RestController
@RequestMapping("/leaders")
public class PoliticalLeaderController {

	@Autowired
	private PoliticalLeaderService politicalLeaderService;

	@PostMapping("/leader/register-leader")
	public ResponseEntity<PoliticalLeaderDto> addPoliticalLeader(@Valid @RequestBody PoliticalLeaderDto politicalLeaderDto,
			BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result);
			throw new InvalidDataException("Political Leader Data is not valid");
		}
        politicalLeaderService.registerPoliticalLeader(politicalLeaderDto);    
		return ResponseEntity.ok(politicalLeaderDto);

	}

	@PutMapping("/leader/update-leader")
	public ResponseEntity<PoliticalLeaderDto> updatePoliticalLeader(@Valid @RequestBody PoliticalLeaderDto politicalLeaderDto,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("Political Leader Data is not valid");
		}
		politicalLeaderService.updatePoliticalLeader(politicalLeaderDto);
		return ResponseEntity.ok(politicalLeaderDto);

	}

	@DeleteMapping("/party/delete/{politicalLeaderId}")
	public ResponseEntity<Boolean> deletePoliticalLeader(@PathVariable("politicalLeaderId") Long politicalLeaderId) {

		politicalLeaderService.deletePoliticalLeader(politicalLeaderId);
		return ResponseEntity.ok(true);

	}

	@GetMapping("/leader/{politicalLeaderId}")
	public ResponseEntity<PoliticalLeaderDto> getPoliticalLeaderById(@PathVariable("politicalLeaderId") Long politicalLeaderId) {

		PoliticalLeaderDto politicalLeaderById = politicalLeaderService.getPoliticalLeaderById(politicalLeaderId);
		return ResponseEntity.ok(politicalLeaderById);

	}

	@GetMapping
	public ResponseEntity<List<PoliticalLeaderDto>> getAllPoliticalLeader() {
		List<PoliticalLeaderDto> allPoliticalLeaders = politicalLeaderService.getAllPoliticalLeaders();
		return ResponseEntity.ok(allPoliticalLeaders);

	}

	@GetMapping("by-party-id/{politicalPartyId}")
	public ResponseEntity<List<PoliticalLeaderDto>> getAllPoliticalLeaderByPartyId(@PathVariable("politicalPartyId") Long politicalPartyId) {

		List<PoliticalLeaderDto> politicalLeadersByPartyId = politicalLeaderService
				.getPoliticalLeadersByPartyId(politicalPartyId);
		return ResponseEntity.ok(politicalLeadersByPartyId);

	}

}
