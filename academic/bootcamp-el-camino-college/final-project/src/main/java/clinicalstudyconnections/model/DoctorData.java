package clinicalstudyconnections.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import clinicalstudyconnections.entity.Doctor;
import clinicalstudyconnections.entity.Site;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DoctorData {
	private Long doctorId;	
	private String doctorFirstName;
	private String doctorLastName;
	@JsonIgnore
	private Site site;
	private SiteResponse siteResponse;
	
	public DoctorData(Doctor doctor) {
		doctorId = doctor.getDoctorId();
		doctorFirstName = doctor.getDoctorFirstName();
		doctorLastName = doctor.getDoctorLastName();
		boolean hasSite = !Objects.isNull(doctor.getSite());
		
		// Because Doctor can exist without being added/associated to a Site
		if(hasSite) {
			site = doctor.getSite();
			siteResponse = new SiteResponse(doctor.getSite());
		}
		
	}
	
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
}
