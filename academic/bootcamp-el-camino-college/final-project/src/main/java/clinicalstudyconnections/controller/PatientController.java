package clinicalstudyconnections.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import clinicalstudyconnections.model.PatientData;
import clinicalstudyconnections.service.ClinicalStudyConnectionService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class PatientController {
	
	private ClinicalStudyConnectionService service;
	
	public PatientController(ClinicalStudyConnectionService service) {
		this.service = service;
	}
	
	/*
	 * ---- PATIENT --------------------------------------------------------------------
	 */
	
	@GetMapping("/patients")
	public List<PatientData> getAllPatients() {
		log.info("Grabbing all Patients");
		return service.getAllPatients();
	}
	
	@GetMapping("/patients/{patientId}")
	public PatientData getPatientById(@PathVariable Long patientId) {
		log.info("Grab Patient with ID={}", patientId);
		return service.getPatientById(patientId);		
	}
	
	@PostMapping("/patients")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PatientData createPatient(@RequestBody PatientData patientData) {
		log.info("Create Patient {}", patientData);
		return service.savePatient(patientData);
	}
	
	@PutMapping("/patients/{patientId}")
	public PatientData updatePatient(@PathVariable Long patientId, @RequestBody PatientData patientData) {
		patientData.setPatientId(patientId);
		log.info("Update Patient {}", patientData);
		return service.savePatient(patientData);
	}
	
	@DeleteMapping("/patients/{patientId}")
	public Map<String, String> deletePatient(@PathVariable Long patientId) {
		log.info("Delete Patient with ID={}", patientId);
		service.deletePatient(patientId);
		return Map.of("message", String.format("Deleting Patient with ID=%s was successful", patientId));
	}

}
