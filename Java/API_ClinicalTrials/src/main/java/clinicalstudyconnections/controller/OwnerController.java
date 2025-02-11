package clinicalstudyconnections.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import clinicalstudyconnections.model.ClinicalStudyData;
import clinicalstudyconnections.model.DoctorData;
import clinicalstudyconnections.model.OwnerData;
import clinicalstudyconnections.model.SiteData;
import clinicalstudyconnections.service.ClinicalStudyConnectionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
// Annotation for Swagger
@Tag(name = "Owners", description = "Operations on Owners.")
public class OwnerController {
	
	private ClinicalStudyConnectionService service;
	
	public OwnerController(ClinicalStudyConnectionService service) {
		this.service = service;
	}
	
	/*
	 * ---- OWNER --------------------------------------------------------------------
	 */

	@GetMapping("/owners")
	public List<OwnerData> getAllOwners() {
		log.info("Grabbing all owners");
		return service.getAllOwners();		
	}
	
	@GetMapping("/owners/{ownerId}")
	public OwnerData getOwnerById(@PathVariable Long ownerId) {
		log.info("Find owner with ID={}", ownerId);
		return service.getOwner(ownerId);
	}
	
	@PostMapping("/owners")
	//@ResponseStatus(code = HttpStatus.CREATED)
	// Trying something new called ResponseEntity
	// https://www.baeldung.com/spring-response-entity
	public ResponseEntity<String> createOwner(@RequestBody OwnerData ownerData) {
		// Cool function to have base Model support toJson
		log.info("Create owner {}", ownerData.toJson());
		
		// Headers
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		
		// Added func in Data to check if is valid before proceeding
		if(!ownerData.isValid()) {
			return new ResponseEntity<>("Invalid Owner Data provided. Please review and try again.", headers, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(service.saveOwner(ownerData).toJson(), headers, HttpStatus.CREATED);
	}

	@PutMapping("/owners/{ownerId}")
	public OwnerData updateOwner(@PathVariable Long ownerId, @RequestBody OwnerData ownerData) {
		ownerData.setOwnerId(ownerId);
		log.info("Update owner {}", ownerData);
		return service.saveOwner(ownerData);
	}
	
	// We have this just to show an Error - Not Allowed
	// Return HTTP Code 405 
	@DeleteMapping("/owners")
	// Not Needed - handled in GlobalError class
	//@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	public Map<String, String> deleteAllOwners() {
		log.info("Trying to delete all Owners");
		throw new UnsupportedOperationException("Deleting all contributors is not allowed.");
	}
	
	@DeleteMapping("/owners/{ownerId}")
	public Map<String, String> deleteOwnerById(@PathVariable Long ownerId) {
		log.info("Delete owner with ID={}", ownerId);
		service.deleteOwner(ownerId);
		return Map.of("message", String.format("Deleting Owner with ID=%s was successful", ownerId));
	}
	
	@GetMapping("/owners/{ownerId}/studies")
	public List<ClinicalStudyData> getStudiesHandledByOwnerSites(@PathVariable Long ownerId){
		log.info("Finding studies for sites associated with Owner");
		return service.GetStudiesForOwner(ownerId);
	}
	/*
	 * ---- SITE --------------------------------------------------------------------
	 */
	
	@GetMapping("/owners/{ownerId}/sites")
	public List<SiteData> getAllSitesForOwner(@PathVariable Long ownerId) {
		log.info("Grabbing all sites for Owner ID={}", ownerId);
		// NICE TO HAVE 
		// Return message no sites setup yet. Please add through API call
		return service.getAllSites(ownerId);
	}
	
	@GetMapping("/owners/{ownerId}/site/{siteId}")
	public SiteData getSiteById(@PathVariable Long ownerId, @PathVariable Long siteId) {
		log.info("Find Site with ID={} for Owner with ID={}", siteId, ownerId);
		return service.getSiteById(ownerId, siteId);
	}
	
	@PostMapping("/owners/{ownerId}/sites")
	@ResponseStatus(code = HttpStatus.CREATED)
	public SiteData createSite(@PathVariable Long ownerId, @RequestBody SiteData siteData) {
		log.info("Create Site {}", siteData);
		// NEED TO FIX - DONE
		// THIS SHOULD ASSOCIATED SITE WITH THE OWNER
		// Fix - Added code in Service to find OwnerById and add to Site Obj before saving. 
		return service.saveSite(ownerId, siteData);
	}
	
	@PutMapping("/owners/{ownerId}/sites/{siteId}")
	public SiteData updateSite(@PathVariable Long ownerId, @PathVariable Long siteId, @RequestBody SiteData siteData) {
		siteData.setSiteId(siteId);
		log.info("Update Site {}", siteData); 
		// THIS FAILS BECUASE THE CREATE DIDNT ASSOCIATE AN OWNER ID - DONE
		// ERROR - "Cannot invoke \"clinicalstudyconnections.entity.Owner.getOwnerId()\" because the return value of \"clinicalstudyconnections.entity.Site.getOwner()\" is null"
		// Fix - Was fixed with above fix for createSite();
		return service.saveSite(ownerId, siteData);
	}
	
	@DeleteMapping("/owners/{ownerId}/sites/{siteId}")
	public Map<String, String> deleteSite(@PathVariable Long ownerId, @PathVariable Long siteId) {
		log.info("Delete Site with ID={}", siteId);
		int deleteResult = service.deleteSite(ownerId, siteId);
		// SAME AS ABOVE - DONE
		// Fix - Was fixed with above fix for createSite();
		if(deleteResult == 0)
			return Map.of("message", String.format("Deleting Site with ID=%s was successful", siteId));
		else 
			return Map.of("message", String.format("Deleting Site with ID=%s failed, check if Site has active studies.", siteId));
	}
	
	// Adding Doctor to Site
	@PostMapping("/owners/{ownerId}/sites/{siteId}/doctors/{doctorId}")
	public Map<String, String> addDoctorToSite(@PathVariable Long ownerId, @PathVariable Long siteId, @PathVariable Long doctorId) {
		log.info("Adding Doctor with ID={} to Site with ID={}", doctorId, siteId);
		int doctorResult = service.addDoctorToSite(ownerId, siteId, doctorId);
		
		if(doctorResult == 0)
			return Map.of("message", "Added Doctor to Site successfully.");
		else
			return Map.of("message", "Failed to add Doctor to Site, check if Doctor already associated with Site.");
		
	}
	
	@DeleteMapping("/owners/{ownerId}/sites/{siteId}/doctors/{doctorId}")
	public Map<String, String> deleteDoctorFromSite(@PathVariable Long ownerId, @PathVariable Long siteId, @PathVariable Long doctorId) {
		log.info("Deleting Doctor with ID={} to Site with ID={}", doctorId, siteId);
		int doctorResult = service.deleteDoctorFromSite(ownerId, siteId, doctorId);
		
		if(doctorResult == 0)
			return Map.of("message", "Removed Doctor from Site successfully.");
		else
			return Map.of("message", "Failed to remove Doctor from Site, please confirm at least one Doctor associated with Site.");
	}
	
	/*
	 * ---- DOCTOR --------------------------------------------------------------------
	 */
	
	@GetMapping("/owners/{ownerId}/doctors")
	public List<DoctorData> getAllDoctors(@PathVariable Long ownerId) {
		log.info("Grabbing all Doctors for Owner with ID={}", ownerId);
		return service.getAllDoctors(ownerId);
	}
	
	@GetMapping("/owners/{ownerId}/doctors/{doctorId}")
	public DoctorData getDoctorById(@PathVariable Long ownerId, @PathVariable Long doctorId) {
		log.info("Grab Doctor with ID={}", doctorId);
		return service.getDoctorById(ownerId, doctorId);
	}
	
	@PostMapping("/owners/{ownerId}/doctors")
	@ResponseStatus(code = HttpStatus.CREATED)
	public DoctorData createDoctor(@PathVariable Long ownerId, @RequestBody DoctorData doctorData) {
		log.info("Create Doctor {}", doctorData);
		// NEED TO FIX - DONE
		// ERROR - "not-null property references a null or transient value : clinicalstudyconnections.entity.Doctor.owner"
		// Fix - Added code in Service to find OwnerById and add to Doctor Obj before saving. 
		return service.saveDoctor(ownerId, doctorData);
	}
	
	@PutMapping("/owners/{ownerId}/doctors/{doctorId}")
	public DoctorData updateDoctor(@PathVariable Long ownerId, @PathVariable Long doctorId, @RequestBody DoctorData doctorData) {
		doctorData.setDoctorId(doctorId);
		log.info("Update Doctor {}", doctorData);
		return service.saveDoctor(ownerId, doctorData);
	}
	
	@DeleteMapping("/owners/{ownerId}/doctors/{doctorId}")
	public Map<String, String> deleteDoctor(@PathVariable Long ownerId, @PathVariable Long doctorId) {
		log.info("Delete Doctor with ID={}", doctorId);
		int doctorResult = service.deleteDoctor(ownerId, doctorId);
		// NEED TO FIX - DONE
		// SHOULD HAVE FAILED WITH INVALID ID, GOT MESSAGE - "Deleting Doctor with ID=2 was successful"
		// Fix - Not a real issue, User Error
		if(doctorResult == 0)
			return Map.of("message", String.format("Deleting Doctor with ID=%s was successful", doctorId));
		else
			return Map.of("message", String.format("Deleting Doctor with ID=%s blocked, if Doctor associated with SITE, please remove SITE first.", doctorId));
	}
}
