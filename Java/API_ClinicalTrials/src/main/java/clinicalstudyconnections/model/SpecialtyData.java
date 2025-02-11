package clinicalstudyconnections.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import clinicalstudyconnections.entity.ClinicalStudy;
import clinicalstudyconnections.entity.Site;
import clinicalstudyconnections.entity.Specialty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SpecialtyData {
	private Long specialtyId;
	private String specialtyName;
	@JsonIgnore
	private Set<Site> sites = new HashSet<>();
	private Set<ClinicalStudy> clinicalStudies = new HashSet<>();
	
	public SpecialtyData(Specialty specialty) {
		specialtyId = specialty.getSpecialtyId();
		specialtyName = specialty.getSpecialtyName();
		
		// Dont think I need Sites OR CliniclaStudies
	}
}
