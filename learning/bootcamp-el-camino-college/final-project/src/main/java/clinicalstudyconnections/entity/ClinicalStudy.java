package clinicalstudyconnections.entity;

import java.util.HashSet;
import java.util.Set;

import clinicalstudyconnections.enums.StudyStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class ClinicalStudy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clinicalStudyId;
	
	private String studyName;
	private String studyDescription;
	private StudyStatus studyStatus;
	
	//ManyToOne Specialty
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "specialty_id")
	private Specialty specialty;
	
	//ManyToMany Sites
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "clinicalStudies")
	private Set<Site> sites = new HashSet<>();
	
	public void removeSite(Site site) {
		sites.remove(site);
		site.getClinicalStudies().remove(this);
	}
	
	public void enrollSite(Site site) {
		sites.add(site);
		site.getClinicalStudies().add(this);
	}
	
	// OLD CODE 
		/*
		 * //ManyToMany Patient
		 * @EqualsAndHashCode.Exclude
		 * @ToString.Exclude
		 * @ManyToMany(mappedBy = "clinicalStudies", cascade = CascadeType.ALL)
		 * private Set<Patient> patients = new HashSet<>();
		 */
	
	/*
	 * --- BEGINNING of EXPERIMENTATION 001 -----------------------------------------------------------------
	 */	
	
	//@EqualsAndHashCode.Exclude
	//@ToString.Exclude
	//@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //@JoinTable(
    //    name = "study_patient",
    //    joinColumns = @JoinColumn(name = "study_id"),
    //    inverseJoinColumns = @JoinColumn(name = "patient_id")
    //)
	//private Set<StudyPatient> enrolledPatients = new HashSet<>();
	//
	//public void addPatient(Patient patient, Date enrollmentDate) { // Maybe add an enrollment Data 
	//	StudyPatient studyPatient = new StudyPatient(this, patient, enrollmentDate);
	//	enrolledPatients.add(studyPatient);
	//	patient.getClinicalStudies().add(studyPatient);
	//}
	//
	//public void removePatient(Patient patient) {
	//	enrolledPatients.removeIf(studyPatient -> studyPatient.getPatient().equals(patient));
	//	patient.getClinicalStudies().removeIf(studyPatient -> studyPatient.getClinicalStudy().equals(this));
	//}
	
	/*
	 * --- END of EXPERIMENTATION 001 -----------------------------------------------------------------
	 */
	
	/*
	 * --- BEGINNGING of EXPERIMENTATION 002 -----------------------------------------------------------------
	 */
	
	// More Knowledge: 
	// The mappedBy attribute is used to declare the owning side of the relationship. In this case, it's declared on the Study entity. 
	// It specifies that the Study entity is not the owner of the relationship, and the patients set is mapped by the study property 
	// in the Patient entity.	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "study_patient",
        joinColumns = @JoinColumn(name = "study_id"),
        inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private Set<Patient> patients = new HashSet<>();
	
	public void removePatient(Patient patient) {
		patients.remove(patient);
		patient.getClinicalStudies().remove(this);
	}
	
	public void enrollPatient(Patient patient) {
		patients.add(patient);
		patient.getClinicalStudies().add(this);
	}
	/*
	 * --- END of EXPERIMENTATION 002 -----------------------------------------------------------------
	 */
}
