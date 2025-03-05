package clinicalstudyconnections.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import clinicalstudyconnections.entity.ClinicalStudy;
import clinicalstudyconnections.entity.Doctor;
import clinicalstudyconnections.entity.Owner;
import clinicalstudyconnections.entity.Site;
import clinicalstudyconnections.entity.Specialty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SiteData {
	private Long siteId;
	private String siteName;
	private String siteAddress;
	private String siteCity;
	private String siteState;
	private String siteZip;
	private String sitePhone;
	@JsonIgnore
	private Owner owner;
	private OwnerResponse ownerResponse;
	@JsonIgnore
	private Set<Doctor> doctors = new HashSet<>();
	private Set<DoctorResponse> doctorsResponse = new HashSet<>();
	@JsonIgnore
	private Set<Specialty> specialties = new HashSet<>();
	private Set<SpecialtyResponse> specialtiesResponse = new HashSet<>();
	@JsonIgnore
	private Set<ClinicalStudy> studies = new HashSet<>();
	
	public SiteData(Site site) {
		siteId = site.getSiteId();
		siteName = site.getSiteName();
		siteAddress = site.getSiteAddress();
		siteCity = site.getSiteCity();
		siteState = site.getSiteState();
		siteZip = site.getSiteZip();
		sitePhone = site.getSitePhone();
		
		owner = site.getOwner();
		ownerResponse = new OwnerResponse(site.getOwner());
		
		doctors = site.getDoctors();
		site.getDoctors().forEach(doctor -> doctorsResponse.add(new DoctorResponse(doctor)));
		
		specialties = site.getSpecialties();
		site.getSpecialties().forEach(specialty -> specialtiesResponse.add(new SpecialtyResponse(specialty)));
		
		studies = site.getClinicalStudies();
	}
	
	@Data
	@NoArgsConstructor
	static class OwnerResponse {
		private String ownerName;
		
		public OwnerResponse(Owner owner) {
			ownerName = owner.getOwnerFirstName() + " " + owner.getOwnerLastName();
		}
	}
	
	@Data
	@NoArgsConstructor
	static class DoctorResponse {
		private Long doctorId;	
		private String doctorFirstName;
		private String doctorLastName;
		//private Site site;
		
		public DoctorResponse(Doctor doctor) {
			doctorId = doctor.getDoctorId();
			doctorFirstName = doctor.getDoctorFirstName();
			doctorLastName = doctor.getDoctorLastName();
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
}
