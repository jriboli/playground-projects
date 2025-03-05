package clinicalstudyconnections.service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Service;

import clinicalstudyconnections.controller.StudyController;
import clinicalstudyconnections.entity.ClinicalStudy;
import clinicalstudyconnections.entity.Doctor;
import clinicalstudyconnections.entity.Owner;
import clinicalstudyconnections.entity.Patient;
import clinicalstudyconnections.entity.Site;
import clinicalstudyconnections.entity.Specialty;
import clinicalstudyconnections.enums.StudyStatus;
import clinicalstudyconnections.model.ClinicalStudyData;
import clinicalstudyconnections.model.DoctorData;
import clinicalstudyconnections.model.OwnerData;
import clinicalstudyconnections.model.PatientData;
import clinicalstudyconnections.model.SiteData;
import clinicalstudyconnections.repository.ClinicalStudyDBRepository;
import clinicalstudyconnections.repository.DoctorDBRepository;
import clinicalstudyconnections.repository.OwnerDBRepository;
import clinicalstudyconnections.repository.PatientDBRepository;
import clinicalstudyconnections.repository.SiteDBRepository;
import clinicalstudyconnections.repository.SpecialtyDBRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClinicalStudyConnectionService {

	// TESTING OUT SOME DEPENDENCY INJECTION 
	//private OwnerRepository ownerRepo;
	private OwnerDBRepository ownerDbRepo;
	private SiteDBRepository siteDbRepo;
	private DoctorDBRepository doctorDbRepo;
	private ClinicalStudyDBRepository clinicalDbRepo;
	private PatientDBRepository patientDbRepo;
	private SpecialtyDBRepository specialtyDbRepo;
	
	//public ClinicalStudyConnectionService(OwnerRepository ownerRepo) {
	//	this.ownerRepo = ownerRepo;
	//}
	public ClinicalStudyConnectionService(OwnerDBRepository ownerDbRepo, SiteDBRepository siteDbRepo, 
			DoctorDBRepository doctorDbRepo, PatientDBRepository patientDbRepo, 
			ClinicalStudyDBRepository clinicalDbRepo, SpecialtyDBRepository specialtyDbRepo) {
		this.ownerDbRepo = ownerDbRepo;
		this.siteDbRepo = siteDbRepo;
		this.doctorDbRepo = doctorDbRepo;
		this.clinicalDbRepo = clinicalDbRepo;
		this.patientDbRepo = patientDbRepo;
		this.specialtyDbRepo = specialtyDbRepo;
	}
	
	/*
	 * ---- OWNER --------------------------------------------------
	 */
	public List<OwnerData> getAllOwners() {
		//List<Owner> owners = ownerRepo.all();
		List<Owner> owners = ownerDbRepo.findAll();
		List<OwnerData> ownersResponse = new LinkedList<>();
		
		//owners.forEach(owner -> ownersResponse.add(new OwnerData(owner)));
		
		// Try using a Stream
		// https://www.baeldung.com/java-use-remove-item-stream
		// https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#method.summary
		// https://howtodoinjava.com/java8/stream-map-example/
		return owners.stream()
				.map(o -> new OwnerData(o))
				.toList();
		
		//return ownersResponse;
	}

	public OwnerData getOwner(Long ownerId) {
		Owner owner = findOrCreateOwner(ownerId);
		return new OwnerData(owner);
	}

	public OwnerData saveOwner(OwnerData ownerData) {
		Long ownerId = ownerData.getOwnerId();
		Owner owner = findOrCreateOwner(ownerId);
		
		setFieldsInOwner(owner, ownerData);
		return new OwnerData(ownerDbRepo.save(owner));
	}

	public void deleteOwner(Long ownerId) {
		// This will find and check if OwnerId is valid, else throw error
		Owner owner = findOrCreateOwner(ownerId);
		
		ownerDbRepo.delete(owner);
	}

	public List<ClinicalStudyData> GetStudiesForOwner(Long ownerId) {
		List<ClinicalStudyData> results = new LinkedList<>();
		
		// First verify owner
		Owner owner = findOrCreateOwner(ownerId);
		
		// Find owner sites
		Set<Site> sites = owner.getSites();
		
		// Find studies assocaited with sites
		Set<ClinicalStudy> studies = new HashSet<>();
		sites.forEach(site -> studies.addAll(site.getClinicalStudies()) );
		
		
		// return list of studies
		studies.forEach(study -> results.add(new ClinicalStudyData(study)));
		return results;
	}

	private Owner findOrCreateOwner(Long ownerId) {
		Owner owner;
		if(Objects.isNull(ownerId)) {
			owner = new Owner();
		}
		else {
			owner = findOwnerById(ownerId);
		}
		
		return owner;
	}
	
	private Owner findOwnerById(Long ownerId) {
		//return ownerRepo.get(ownerId).orElseThrow(() -> new NoSuchElementException("No matching Owner Id."));
		return ownerDbRepo.findById(ownerId).orElseThrow(() -> new NoSuchElementException("No matching Owner Id."));
	}

	private void setFieldsInOwner(Owner owner, OwnerData ownerData) {
		owner.setOwnerId(ownerData.getOwnerId());
		owner.setOwnerFirstName(ownerData.getOwnerFirstName());
		owner.setOwnerLastName(ownerData.getOwnerLastName());
		owner.setCompanyName(ownerData.getCompanyName());
		
		// Need to Fix - DONE
		owner.setSites(ownerData.getSites());
		
		// Why does Owner have access to Sites - DONE
		// Fix - By Design
		owner.setDoctors(ownerData.getDoctors());
		
	}
	
	/*
	 * ---- SITE --------------------------------------------------
	 */

	public List<SiteData> getAllSites(Long ownerId) {
		//List<Site> sites = siteDbRepo.findAll();
		List<Site> sites = siteDbRepo.findSitesByOwner_OwnerId(ownerId);
		List<SiteData> sitesResponse = new LinkedList<>();
		// Want to throw a custom response if no Sites setup
		if(sites.isEmpty()) {
			throw new NoSuchElementException("No sites setup yet. Look into setting up sites.");
		}
		
		sites.forEach(site -> sitesResponse.add(new SiteData(site)));
		return sitesResponse;
	}

	public SiteData getSiteById(Long ownerId, Long siteId) {
		Site site = findOrCreateSite(ownerId, siteId);
		return new SiteData(site);
	}

	public SiteData saveSite(Long ownerId, SiteData siteData) {
		Owner owner = findOrCreateOwner(ownerId);
		Long siteId = siteData.getSiteId();
		Site site = findOrCreateSite(ownerId, siteId);
		
		setFieldsInSite(site, siteData);
		site.setOwner(owner);
		return new SiteData(siteDbRepo.save(site));
	}

	public int deleteSite(Long ownerId, Long siteId) {
		Site site = findOrCreateSite(ownerId, siteId);
		// Check if the SITE has any active Studies
		Set<ClinicalStudy> studies = site.getClinicalStudies();
		
		boolean anyActiveStudy = studies.stream()
		.anyMatch(study -> StudyStatus.IN_PROGRESS.equals(study.getStudyStatus()));
		
		if(anyActiveStudy) {
			return -1;
		}		
		
		// Remove Doctors
		site.getDoctors().forEach(doctor -> deleteDoctorFromSite(ownerId, siteId, doctor.getDoctorId()));
		
		// Refresh Site - no longer have doctors
		site = findOrCreateSite(ownerId, siteId);
		
		siteDbRepo.delete(site);	
		
		return 0;
	}

	public int addDoctorToSite(Long ownerId, Long siteId, Long doctorId) {
		Doctor doctor = findOrCreateDoctor(ownerId, doctorId);
		Site site = findOrCreateSite(ownerId, siteId);
		
		// Check doctor not already associated with Site
		boolean isDoctorWithSite = site.getDoctors().stream().anyMatch(doc -> doctorId.equals(doc.getDoctorId()));
		if(isDoctorWithSite) {
			return -1;
		}
		
		// Might need to Fix this - Or more so test it - DONE
		site.enrollDoctor(doctor);
		siteDbRepo.save(site);
		
		return 0;
	}

	public int deleteDoctorFromSite(Long ownerId, Long siteId, Long doctorId) {
		Doctor doctor = findOrCreateDoctor(ownerId, doctorId);
		Site site = findOrCreateSite(ownerId, siteId);
		
		// Check if last Doctor associated with Site, and prevent removal
		boolean isLastDoctor = site.getDoctors().size() < 2;
		
		if(isLastDoctor) {
			return -1;
		}
		
		// Might need to Fix this - Or more so test it - DONE
		//siteDbRepo.DeleteDoctor(site, doctor);
		site.removeDoctor(doctor);
		siteDbRepo.save(site);
		
		return 0;
	}

	private Site findOrCreateSite(Long ownerId, Long siteId) {
		Site site;
		if(Objects.isNull(siteId)) {
			site = new Site();
		}
		else {
			site = findSiteById(ownerId, siteId);
		}
		
		return site;
	}

	private Site findSiteById(Long ownerId, Long siteId) {
		Site site = siteDbRepo.findById(siteId).orElseThrow(() -> new NoSuchElementException("Site ID does not exist."));
		
		if(site.getOwner().getOwnerId() != ownerId) {
			throw new NoSuchElementException("Site does not exist for Owner.");
		}

		return site;
	}
	
	private Site basicFindSiteById(Long siteId) {
		return siteDbRepo.findById(siteId).orElseThrow(() -> new NoSuchElementException("Site ID does not exist."));
	}

	private void setFieldsInSite(Site site, SiteData siteData) {
		site.setSiteId(siteData.getSiteId());
		site.setSiteName(siteData.getSiteName());
		site.setSiteAddress(siteData.getSiteAddress());
		site.setSiteCity(siteData.getSiteCity());
		site.setSiteState(siteData.getSiteState());
		site.setSiteZip(siteData.getSiteZip());
		// SITE PHONE NOT BEING RECORDED - FIXED - DONE
		site.setSitePhone(siteData.getSitePhone());
		
		//site.setOwner(siteData.getOwner());
		
		//Doctor
		
		// Specialty
		
	}
	
	/*
	 * ---- DOCTOR --------------------------------------------------
	 */

	public List<DoctorData> getAllDoctors(Long ownerId) {
		// THIS NEED TO BE MODIFIED TO TAKE THE OWNER ID parameter - DONE
		//List<Doctor> doctors = doctorDbRepo.findAll();
		List<Doctor> doctors = doctorDbRepo.findDoctorsByOwner_OwnerId(ownerId);
		List<DoctorData> doctorsResponse = new LinkedList<>();
		
		doctors.forEach(doctor -> doctorsResponse.add(new DoctorData(doctor)));
		return doctorsResponse;
	}

	public DoctorData getDoctorById(Long ownerId, Long doctorId) {
		Doctor doctor = findOrCreateDoctor(ownerId, doctorId);
		
		return new DoctorData(doctor);
	}

	public DoctorData saveDoctor(Long ownerId, DoctorData doctorData) {
		Owner owner = findOrCreateOwner(ownerId);
		Long doctorId = doctorData.getDoctorId();
		Doctor doctor = findOrCreateDoctor(ownerId, doctorId);
		
		setFieldsInDoctor(doctor, doctorData);
		doctor.setOwner(owner);
		return new DoctorData(doctorDbRepo.save(doctor));
	}

	public int deleteDoctor(Long ownerId, Long doctorId) {
		Doctor doctor = findOrCreateDoctor(ownerId, doctorId);
		
		// Check if doctor associated with SITE, and block
		boolean isAssociatedWithSite = Objects.nonNull(doctor.getSite());
		
		if(isAssociatedWithSite) {
			return -1;
		}
		
		doctorDbRepo.delete(doctor);	
		
		return 0;
	}

	private Doctor findOrCreateDoctor(Long ownerId, Long doctorId) {
		Doctor doctor;
		if(Objects.isNull(doctorId)) {
			doctor = new Doctor();
		}
		else {
			doctor = findDoctorById(ownerId, doctorId);
		}
		
		return doctor;
	}

	private Doctor findDoctorById(Long ownerId, Long doctorId) {
		Doctor doctor = doctorDbRepo.findById(doctorId).orElseThrow(() -> new NoSuchElementException("No such Doctor exists."));
		
		if(doctor.getOwner().getOwnerId() != ownerId) {
			throw new NoSuchElementException("No Doctor exists for Owner.");
		}
		
		return doctor;
	}

	private void setFieldsInDoctor(Doctor doctor, DoctorData doctorData) {
		doctor.setDoctorId(doctorData.getDoctorId());
		doctor.setDoctorFirstName(doctorData.getDoctorFirstName());
		doctor.setDoctorLastName(doctorData.getDoctorLastName());
		
		// Owner
		
		// Sites		
	}
	
	/*
	 * ---- STUDY --------------------------------------------------
	 */

	public List<ClinicalStudyData> getAllStudies() {
		List<ClinicalStudy> studies = clinicalDbRepo.findAll();
		List<ClinicalStudyData> studiesResponse = new LinkedList<>();
		
		studies.forEach(study -> studiesResponse.add(new ClinicalStudyData(study)));
		return studiesResponse;
	}
	
	public List<ClinicalStudyData> getStudiesBySpecialty(String specialty) {
		List<Specialty> existingSpecialties = specialtyDbRepo.findAll();
		
		Specialty selectedSpecialty = existingSpecialties.stream()
				.filter(s -> s.getSpecialtyName().equals(specialty))
				.findFirst()
				.orElse(null);
		
		if(Objects.isNull(selectedSpecialty)) {
			throw new NoSuchElementException("There is not matching specialty for - " + specialty);
		}
		// Can try IllegalArgumentException
		
		List<ClinicalStudy> studies = clinicalDbRepo.findBySpecialty(selectedSpecialty);
		List<ClinicalStudyData> studiesResponse = new LinkedList<>();
		
		studies.forEach(study -> studiesResponse.add(new ClinicalStudyData(study)));
		return studiesResponse;
	}
	
	public List<ClinicalStudyData> getStudiesBySpecialtyAndStatus(String specialty, String status) {
		List<ClinicalStudyData> results = getStudiesByStatus(status);
		
		log.info("Mid processing results: " + results);
		log.info("Searching for: -" + specialty.toUpperCase() + "-");
		
		results.forEach(r -> log.info("Result: -" + r.getSpecialty().getSpecialtyName().toUpperCase() + "-"));
		
		// Filter the list of Studies by Status with Specialty
		return results.stream()
				// Why did .equals verse ++ work in matching ??? 
				.filter(r -> r.getSpecialty().getSpecialtyName().equals(specialty))
				.toList();
				
	}

	public List<ClinicalStudyData> getStudiesByStatus(String status) {
		// Check Status is valid
		try {
			StudyStatus enumValue = StudyStatus.valueOf(status.toUpperCase());
			
			// Filter on the DB side with a hand written query
			List<ClinicalStudy> studies = clinicalDbRepo.findByStatus(enumValue.getValue());
			
			return studies.stream()
					.map(s -> new ClinicalStudyData(s))
					.toList();
			
		} catch (Exception ex) {
			log.info("Error match StudyStatus ENUM - " + ex.getMessage());
			return null;
		}		
	}

	public ClinicalStudyData getStudyById(Long studyId) {
		ClinicalStudy clinicalStudy = findOrCreateStudy(studyId);
		
		return new ClinicalStudyData(clinicalStudy);
	}

	public ClinicalStudyData saveStudy(ClinicalStudyData clinicalStudyData) {
		Long clinicalStudyId = clinicalStudyData.getClinicalStudyId();
		ClinicalStudy clinicalStudy = findOrCreateStudy(clinicalStudyId);
		
		// This will need to be updated later
		//Specialty specialty = specialtyDbRepo.findById(clinicalStudy.getSpecialty().getSpecialtyId()).orElse(null);
		
		setFieldsInStudy(clinicalStudy, clinicalStudyData);
		return new ClinicalStudyData(clinicalDbRepo.save(clinicalStudy));
	}

	public void deleteStudy(Long studyId) {
		ClinicalStudy clinicalStudy = findOrCreateStudy(studyId);
		
		// Removing Patients
		if(!clinicalStudy.getPatients().isEmpty()) {
			clinicalStudy.getPatients().forEach(patient -> withdrawalPatient(studyId, patient.getPatientId()));
		}
		
		// Removing Sites
		if(!clinicalStudy.getSites().isEmpty()) {
			clinicalStudy.getSites().forEach(site -> removeSite(studyId, site.getSiteId()));
		}
		
		//Refresh Site info and Patients and Sites should be removed
		clinicalStudy = findOrCreateStudy(studyId);
		
		clinicalDbRepo.delete(clinicalStudy);		
	}

	public void enrollSite(Long studyId, Long siteId) {
		Site site = basicFindSiteById(siteId);
		ClinicalStudy clinicalStudy = findOrCreateStudy(studyId);
		
		clinicalStudy.enrollSite(site);
		clinicalDbRepo.save(clinicalStudy);	
	}

	public void removeSite(Long studyId, Long siteId) {
		ClinicalStudy clinicalStudy = findOrCreateStudy(studyId);
		//Site site = basicFindSiteById(siteId);
		Site site = clinicalStudy.getSites().stream()
				.filter(s -> s.getSiteId().equals(siteId))
				.findFirst()
				.orElseThrow(() -> new EntityNotFoundException("Site not find in the Study"));
		
		clinicalStudy.removeSite(site);
		clinicalDbRepo.save(clinicalStudy);
		
	}

	public void enrollPatient(Long studyId, Long patientId) {
		Patient patient = findOrCreatePatient(patientId);
		ClinicalStudy clinicalStudy = findOrCreateStudy(studyId);
		
		clinicalStudy.enrollPatient(patient);		
		clinicalDbRepo.save(clinicalStudy);
		
	}

	public void withdrawalPatient(Long studyId, Long patientId) {
		ClinicalStudy clinicalStudy = findOrCreateStudy(studyId);
		//Patient patient = findOrCreatePatient(patientId);
		Patient patient = clinicalStudy.getPatients().stream()
				.filter(p -> p.getPatientId().equals(patientId))
				.findFirst()
				.orElseThrow(() -> new EntityNotFoundException("Patient not found in the Study"));
		
		clinicalStudy.removePatient(patient);
		clinicalDbRepo.save(clinicalStudy);
		
	}

	private ClinicalStudy findOrCreateStudy(Long studyId) {
		ClinicalStudy clinicalStudy;
		if(Objects.isNull(studyId)) {
			clinicalStudy = new ClinicalStudy();
		}
		else {
			clinicalStudy = findStudyById(studyId);
		}
		
		return clinicalStudy;
	}

	private ClinicalStudy findStudyById(Long studyId) {
		return clinicalDbRepo.findById(studyId).orElseThrow(() -> new NoSuchElementException("No such Study exists."));
	}

	private void setFieldsInStudy(ClinicalStudy clinicalStudy, ClinicalStudyData clinicalStudyData) {
		clinicalStudy.setClinicalStudyId(clinicalStudyData.getClinicalStudyId());
		clinicalStudy.setStudyName(clinicalStudyData.getStudyName());
		clinicalStudy.setStudyDescription(clinicalStudyData.getStudyDescription());
		clinicalStudy.setStudyStatus(clinicalStudyData.getStudyStatus());
		clinicalStudy.setSpecialty(clinicalStudyData.getSpecialty());
		
		// Sites - Why SET but no GET
		//clinicalStudy.setSites();
		
		// Patients
		//clinicalStudy.setPatients(clinicalStudyData.getPatients());
		
	}
	
	/*
	 * ---- PATIENT --------------------------------------------------
	 */

	public List<PatientData> getAllPatients() {
		List<Patient> patients = patientDbRepo.findAll();
		List<PatientData> patientsResponse = new LinkedList<>();
		
		patients.forEach(patient -> patientsResponse.add(new PatientData(patient)));
		return patientsResponse;
	}

	public PatientData getPatientById(Long patientId) {
		Patient patient = findOrCreatePatient(patientId);
		
		return new PatientData(patient);
	}

	public PatientData savePatient(PatientData patientData) {
		Long patientId = patientData.getPatientId();
		Patient patient = findOrCreatePatient(patientId);
		
		setFieldsInPatient(patient, patientData);
		return new PatientData(patientDbRepo.save(patient));
	}

	public void deletePatient(Long patientId) {
		Patient patient = findOrCreatePatient(patientId);
		
		patientDbRepo.delete(patient);		
	}

	private Patient findOrCreatePatient(Long patientId) {
		Patient patient;
		if(Objects.isNull(patientId)) {
			patient = new Patient();
		}
		else {
			patient = findPatientById(patientId);
		}
		
		return patient;
	}

	private Patient findPatientById(Long patientId) {
		return patientDbRepo.findById(patientId).orElseThrow(() -> new NoSuchElementException("No such Patient exists."));
	}

	private void setFieldsInPatient(Patient patient, PatientData patientData) {
		patient.setPatientId(patientData.getPatientId());
		patient.setPatientFirstName(patientData.getPatientFirstName());
		patient.setPatientLastName(patientData.getPatientLastName());
		patient.setPatientAge(patientData.getPatientAge());
		patient.setPatientSex(patientData.getPatientSex());
		
		// Studies	
		patient.setClinicalStudies(patientData.getClincialStudies());
	}
}
