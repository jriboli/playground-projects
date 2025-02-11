package clinicalstudyconnections.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Specialty {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long specialtyId;
	
	private String specialtyName;
	
	//ManyToMany Site
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "specialties")
	private Set<Site> sites = new HashSet<>();
	
	//OneToOne Clinical Study
	// FIX THIS ------------------------------------------------
	// Dont need any of this - WHY ???
	// Answer: Because Specialty does not link back to Study
	//@EqualsAndHashCode.Exclude
	//@ToString.Exclude
	//@OneToOne(mappedBy = "specialty")
	//private ClinicalStudy clinicalStudy;
}
