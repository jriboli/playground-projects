package clinicalstudyconnections.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import clinicalstudyconnections.entity.Doctor;
import clinicalstudyconnections.entity.Owner;
import clinicalstudyconnections.entity.Site;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OwnerData extends Model{
	private Long ownerId;
	private String ownerFirstName;
	private String ownerLastName;
	private String companyName;
	@JsonIgnore
	private Set<Site> sites = new HashSet<>();
	private Set<SiteResponse> sitesResponse = new HashSet<>();
	@JsonIgnore
	private Set<Doctor> doctors = new HashSet<>();
	private Set<DoctorResponse> doctorsResponse = new HashSet<>();
	
	public OwnerData(Owner owner) {
		this.ownerId = owner.getOwnerId();
		this.ownerFirstName = owner.getOwnerFirstName();
		this.ownerLastName = owner.getOwnerLastName();
		this.companyName = owner.getCompanyName();
		
		boolean hasSites = sites.isEmpty();
		if(hasSites) {
			owner.getSites().forEach(site -> sitesResponse.add(new SiteResponse(site)));
		}
		
		boolean hasDoctors = doctors.isEmpty();
		if(hasDoctors) {
			owner.getDoctors().forEach(doctor -> doctorsResponse.add(new DoctorResponse(doctor)));
		}
	}
	
	public boolean isValid() {
		return (this.companyName != null) && (!this.getCompanyName().isEmpty());
	}
	// This should help with the Infinite Recursion error 
	@Data
	@NoArgsConstructor
	static class SiteResponse {
		private Long siteId;
		private String siteName;
		private String siteAddress;
		private String siteCity;
		private String siteState;
		private String siteZip;
		private String sitePhone;
		
		public SiteResponse(Site site) {
			siteId = site.getSiteId();
			siteName = site.getSiteName();
			siteAddress = site.getSiteAddress();
			siteCity = site.getSiteCity();
			siteState = site.getSiteState();
			siteZip = site.getSiteZip();
			sitePhone = site.getSitePhone();
		}
	}
	
	@Data
	@NoArgsConstructor
	static class DoctorResponse {
		private Long doctorId;
		private String doctorFirstName;
		private String doctorLastName;
		
		public DoctorResponse(Doctor doctor) {
			doctorId = doctor.getDoctorId();
			doctorFirstName = doctor.getDoctorFirstName();
			doctorLastName = doctor.getDoctorLastName();
		}
	}
}
