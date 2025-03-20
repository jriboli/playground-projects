package clinicalstudyconnections;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;

import clinicalstudyconnections.model.ClinicalStudyData;
import clinicalstudyconnections.model.DoctorData;
import clinicalstudyconnections.model.OwnerData;
import clinicalstudyconnections.model.PatientData;
import clinicalstudyconnections.model.SiteData;

@SpringBootApplication
public class Application {
	private static String secretUser = "admin";
	private static String secretPass = "admin";
	private static String port = "9000";
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		// Writing some code to call the API to simulate usage
		Gson gson = new Gson();
		// ----------------------------------------------------------------------------------------
		// Create a New Owner
		// ----------------------------------------------------------------------------------------
		String ownerBody = "{\r\n"
				+ "    \"ownerFirstName\" : \"Kryus\",\r\n"
				+ "    \"ownerLastName\" : \"Lagoon\",\r\n"
				+ "    \"companyName\" : \"Friendship Industries\"\r\n"
				+ "}";
		
		String ownersUrl = "http://localhost:"+port+"/api/owners";
		
		//Map<String, String> headers = new HashMap<>();
		//headers.put("Content-Type", "application/json");
		//headers.put("Host", "localhost:8080");
		
//		HttpRequest createOwnerReq = HttpRequest.newBuilder()
//				.uri(URI.create("http://localhost:8080/clinical-study-connection/owner"))
//				.POST(BodyPublishers.ofString(ownerBody))
//				//.headers(headers.entrySet().stream()
//				//		.map(e -> e.getKey() + ":" + e.getValue())
//				//		.toArray(String[]::new))
//				.header("Content-Type", "application/json" )
//				.build();
//		HttpResponse<String> createOwnerRes = null;
//		
//		try {
//			createOwnerRes = HttpClient.newHttpClient().send(createOwnerReq, HttpResponse.BodyHandlers.ofString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println(createOwnerRes.body());
		
		String createOwnerResponse = HttpPOST(ownersUrl, ownerBody);		
		OwnerData ownerObj = gson.fromJson(createOwnerResponse, OwnerData.class);
		System.out.println("New Owner ID is : " + ownerObj.getOwnerId());
		
		// ----------------------------------------------------------------------------------------
		// Create a Site for New Owner
		// ----------------------------------------------------------------------------------------
		String siteBody = "{\r\n"
				+ "    \"siteName\" : \"Testing 001\",\r\n"
				+ "    \"siteAddress\" : \"Somewhere\",\r\n"
				+ "    \"siteCity\" : \"Somehow\",\r\n"
				+ "    \"siteState\" : \"CA\",\r\n"
				+ "    \"siteZip\" : \"11111\",\r\n"
				+ "    \"sitePhone\" : \"8885554444\"\r\n"
				+ "}";
		
		String sitesUrl = ownersUrl+"/"+ownerObj.getOwnerId()+"/sites";
	
		String createSiteResponse = HttpPOST(sitesUrl, siteBody); 
		SiteData siteObj = gson.fromJson(createSiteResponse, SiteData.class);	
		System.out.println("New Site ID is : " + siteObj.getSiteId());
		
		// ----------------------------------------------------------------------------------------
		// Create Doctors
		// ----------------------------------------------------------------------------------------
		String doctorBody = "{\r\n"
				+ "    \"doctorFirstName\" : \"James\",\r\n"
				+ "    \"doctorLastName\" : \"Gunn\"\r\n"
				+ "}";
		
		String doctorsUrl = ownersUrl+"/"+ownerObj.getOwnerId()+"/doctors";
		
		String createDoctorResponse = HttpPOST(doctorsUrl, doctorBody);
		DoctorData doctorObj = gson.fromJson(createDoctorResponse, DoctorData.class);
		System.out.println("New Doctor ID is : " + doctorObj.getDoctorId());
		
		// ----------------------------------------------------------------------------------------
		// Enroll Doctor to Site
		// ----------------------------------------------------------------------------------------
		String enrollDoctor = "";
		String enrollDoctorUrl = ownersUrl+"/"+ownerObj.getOwnerId()+"/sites/"+siteObj.getSiteId()+"/doctors/"+doctorObj.getDoctorId();
		
		String enrollDoctorResponse = HttpPOST(enrollDoctorUrl, enrollDoctor);
		System.out.println(enrollDoctorResponse);
		// Check for 200 HTTP CODE
		
		// ----------------------------------------------------------------------------------------
		// Find Study
		// ----------------------------------------------------------------------------------------
		String studyBody = "";
		String studiesUrl = "http://localhost:"+port+"/api/studies/4";
		
		String studyResponse = HttpGET(studiesUrl);
		ClinicalStudyData studyObj = gson.fromJson(studyResponse, ClinicalStudyData.class);
		System.out.println("Found Study ID is : " + studyObj.getClinicalStudyId());
		
		// ----------------------------------------------------------------------------------------
		// Apply to Study
		// ----------------------------------------------------------------------------------------
		String enrollSite4StudyBody = "";
		String enrollSite4StudyUrl = "http://localhost:"+port+"/api/studies/"+studyObj.getClinicalStudyId()+"/sites/"+siteObj.getSiteId();
		
		String enrollSiteResponse = HttpPOST(enrollSite4StudyUrl, enrollSite4StudyBody);
		System.out.println(enrollSiteResponse);
		// Check for 200 HTTP CODE
		
		// ----------------------------------------------------------------------------------------
		// Create Patient
		// ----------------------------------------------------------------------------------------
		String patientBody = "{\r\n"
				+ "    \"patientFirstName\" : \"Sonic\",\r\n"
				+ "    \"patientLastName\" : \"The Hedgehog\",\r\n"
				+ "    \"patientAge\" : 109,\r\n"
				+ "    \"patientSex\" : \"MALE\"\r\n"
				+ "}";
		
		String patientUrl = "http://localhost:"+port+"/api/patients";
		
		String createPatientResponse = HttpPOST(patientUrl, patientBody);
		PatientData patientObj = gson.fromJson(createPatientResponse, PatientData.class);
		System.out.println("New Patient ID is : " + patientObj.getPatientId());
		
		// ----------------------------------------------------------------------------------------
		// Enroll Patient for Study
		// ----------------------------------------------------------------------------------------
		String enrollPatient4StudyBody = "";
		String enrollPatient4StudyUrl = "http://localhost:"+port+"/api/studies/"+studyObj.getClinicalStudyId()+"/patients/"+patientObj.getPatientId();
		
		String enrollPatientResponse = HttpPOST(enrollPatient4StudyUrl, enrollPatient4StudyBody);
		System.out.println(enrollPatientResponse);
		// Check for 200 HTTP CODE

	}
	
	public static String HttpPOST(String url, String body) {
		// Create the credentials string for Basic Authentication
        String credentials = secretUser + ":" + secretPass;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.POST(BodyPublishers.ofString(body))
				.header("Content-Type", "application/json" )
				.header("Authorization", "Basic " + encodedCredentials)
				.build();
		HttpResponse<String> response = null;
		
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return response.body();
	}
	
	public static String HttpGET(String url) {
		// Create the credentials string for Basic Authentication
		String credentials = secretUser + ":" + secretPass;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.header("Authorization", "Basic " + encodedCredentials)
				.build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return response.body();
	}

}
