package clinicalstudyconnections.entity;

import java.util.HashSet;
import java.util.Set;

import clinicalstudyconnections.enums.PatientSex;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long patientId;
	
	private String patientFirstName;
	private String patientLastName;
	private int patientAge;
	private PatientSex patientSex;
	
	// OLD CODE
	
		/*
		 * //ManyToMany Study
		 * @EqualsAndHashCode.Exclude
		 * @ToString.Exclude
		 * @ManyToMany
		 * @JoinTable( name = "study_patient", 
		 * joinColumns = @JoinColumn(name = "patient_id"), 
		 * inverseJoinColumns = @JoinColumn(name = "study_id")) 
		 * private Set<StudyPatient> clinicalStudies = new HashSet<>();
		 */
	
	/*
	 * --- BEGINNGING of EXPERIMENTATION 001 -----------------------------------------------------------------
	 */
	
	//@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
	//private Set<StudyPatient> clinicalStudies = new HashSet<>();
	
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
	@ManyToMany(mappedBy = "patients")
    private Set<ClinicalStudy> clinicalStudies = new HashSet<>();
	
	/*
	 * --- END of EXPERIMENTATION 002 -----------------------------------------------------------------
	 */
}
