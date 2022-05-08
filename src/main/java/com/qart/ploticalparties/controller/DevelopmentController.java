package com.qart.ploticalparties.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qart.ploticalparties.dto.DevelopmentDto;
import com.qart.ploticalparties.exceptions.InvalidDataException;
import com.qart.ploticalparties.service.DevelopmentService;

@RestController
@RequestMapping("/development")
public class DevelopmentController {

	@Autowired
	private DevelopmentService developmentService;

	@PostMapping
	public ResponseEntity<DevelopmentDto> addDevelopments(@Valid @RequestBody DevelopmentDto developmentDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("Development Data is not valid");
		}
        developmentService.createDevelopment(developmentDto);
        return ResponseEntity.ok(developmentDto);

	}

	@PutMapping("/update-development")
	public ResponseEntity<DevelopmentDto> updateDevelopments(@Valid @RequestBody DevelopmentDto developmentDto,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("Development Data is not valid");
		}
        developmentService.updateDevelopment(developmentDto);
        return ResponseEntity.ok(developmentDto);


	}

	@DeleteMapping("/{developmentId}")
	public ResponseEntity<Boolean> deleteDevelopment(@PathVariable("developmentId") Long developmentId) {
    developmentService.deleteDevelopment(developmentId);
		return ResponseEntity.ok(true);

	}

	@GetMapping("/{developmentId}")
	public ResponseEntity<DevelopmentDto> getDevelopmentById(@PathVariable("developmentId") Long developmentId) {
    DevelopmentDto developmentById=developmentService.getDevelopmentById(developmentId);
		return ResponseEntity.ok(developmentById);
	}

	@GetMapping("/getAllDevelopmentActivities")
	public ResponseEntity<List<DevelopmentDto>> getAllDevelopments() {
		return new ResponseEntity<List<DevelopmentDto>>( developmentService.getAllDevelopments(), HttpStatus.OK);

	}

	@GetMapping("/get-developments-by-leaderid/{politicalLeaderId}")
	public ResponseEntity<List<DevelopmentDto>> getAllDevelopmentsByPoliticalLeaderId(@PathVariable("politicalLeaderId") Long politicalLeaderId) {
        List<DevelopmentDto> developmentsByPoliticalLearderId=developmentService.getAllDevelopmentsByLeaderId(politicalLeaderId);
		return ResponseEntity.ok(developmentsByPoliticalLearderId);

	}

}
