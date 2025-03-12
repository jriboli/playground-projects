package clinicalstudyconnections.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import clinicalstudyconnections.entity.ClinicalStudy;
import clinicalstudyconnections.entity.Patient;
import clinicalstudyconnections.entity.Specialty;
import clinicalstudyconnections.enums.PatientSex;
import clinicalstudyconnections.enums.StudyStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClinicalStudyData extends Model {
	private Long clinicalStudyId;
	private String studyName;
	private String studyDescription;
	private StudyStatus studyStatus;
	// Ignore should remove from RESPONSE - but also seems to remove from REQUEST
	// Property only allows in REQUEST and wont show up in RESPONSE
	//@JsonIgnore
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Specialty specialty;
	private SpecialtyResponse specialtyResponse;
	@JsonIgnore
	private Set<Patient> patients = new HashSet<>();
	private Set<PatientResponse> patientsResponse = new HashSet<>();
	
	public ClinicalStudyData(ClinicalStudy clinicalStudy) {
		clinicalStudyId = clinicalStudy.getClinicalStudyId();
		studyName = clinicalStudy.getStudyName();
		studyDescription = clinicalStudy.getStudyDescription();
		studyStatus = clinicalStudy.getStudyStatus();
		
		// Specialty
		specialty = clinicalStudy.getSpecialty();
		specialtyResponse = new SpecialtyResponse(clinicalStudy.getSpecialty());
		
		// Patients
		boolean hasPatients = !Objects.isNull(clinicalStudy.getPatients());
		
		if(hasPatients) {
			patients = clinicalStudy.getPatients();
			clinicalStudy.getPatients().forEach(patient -> patientsResponse.add(new PatientResponse(patient)));
		}		
	}
	
	
	@Data
	@NoArgsConstructor
	static class SpecialtyResponse {
		private Long specialtyId;
		private String specialtyName;
		
		public SpecialtyResponse(Specialty specialty) {
			specialtyId = specialty.getSpecialtyId();
			specialtyName = specialty.getSpecialtyName();
		}
	}
	
	@Data
	@NoArgsConstructor
	static class PatientResponse {
		private Long patientId;
		private String patientFirstName;
		private String patientLastName;
		private int patientAge;
		private PatientSex patientSex;
		
		public PatientResponse(Patient patient) {
			patientId = patient.getPatientId();
			patientFirstName = patient.getPatientFirstName();
			patientLastName = patient.getPatientLastName();
			patientAge = patient.getPatientAge();
			patientSex = patient.getPatientSex();
		}
	}
}
