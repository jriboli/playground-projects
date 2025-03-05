package clinicalstudyconnections.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import clinicalstudyconnections.entity.ClinicalStudy;
import clinicalstudyconnections.entity.Patient;
import clinicalstudyconnections.entity.Specialty;
import clinicalstudyconnections.enums.PatientSex;
import clinicalstudyconnections.enums.StudyStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientData {
	private Long patientId;
	private String patientFirstName;
	private String patientLastName;
	private int patientAge;
	private PatientSex patientSex;
	@JsonIgnore
	private Set<ClinicalStudy> clincialStudies = new HashSet<>();
	private Set<StudyResponse> studiesResponse = new HashSet<>();
	
	public PatientData(Patient patient) {
		patientId = patient.getPatientId();
		patientFirstName = patient.getPatientFirstName();
		patientLastName = patient.getPatientLastName();
		patientAge = patient.getPatientAge();
		patientSex = patient.getPatientSex();
		clincialStudies = patient.getClinicalStudies();
		
		// Clincial Studies
		patient.getClinicalStudies().forEach(study -> studiesResponse.add(new StudyResponse(study)));
	}
	
	@Data
	@NoArgsConstructor
	static class StudyResponse {
		private Long clinicalStudyId;
		private String studyName;
		private String studyDescription;
		private StudyStatus studyStatus;
		// REMOVED TO fix the cyclically dependency  - DONE 
		//private Specialty specialty;
		
		public StudyResponse(ClinicalStudy clinicalStudy) {
			clinicalStudyId = clinicalStudy.getClinicalStudyId();
			studyName = clinicalStudy.getStudyName();
			studyDescription = clinicalStudy.getStudyDescription();
			studyStatus = clinicalStudy.getStudyStatus();
			//specialty = clinicalStudy.getSpecialty();
		}
	}
}
