package clinicalstudyconnections.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clinicalstudyconnections.entity.Owner;
import clinicalstudyconnections.model.OwnerData;
import clinicalstudyconnections.repository.OwnerDBRepository;

class testClinicalStudyConnectionService {

	private static ClinicalStudyConnectionService service;
	private static List<Owner> mockOwners = new LinkedList<>();
	private static Owner mockSingleOwner = new Owner(null, "Peter", "Parker", "Spider Co.", null);
	private static Owner mockInvalidId = new Owner((long) 99, "Peter", "Parker", "Spider Co.", null);
	private static OwnerDBRepository myMockRepo = mock(OwnerDBRepository.class);
	
	@BeforeAll
	static void setUp() throws Exception {
		mockOwners.add(new Owner((long) 1, "Rocket", "Raccoon", "The Guardians", null));
		mockOwners.add(new Owner((long) 2, "Peter", "Quill", "The Guardians", null));
		
		service = new ClinicalStudyConnectionService(myMockRepo, null, null, null, null, null);
	}
	
	@Test 
	void getAllOwnersValidation() {
		when(myMockRepo.findAll()).thenReturn(mockOwners);
		List<OwnerData> results = service.getAllOwners();
		
		assertThat(results.get(0).getOwnerFirstName() == "Rocket");
		assertThat(results.get(0).getOwnerLastName() == "Raccoon");
		assertThat(results.get(0).getCompanyName() == "The Guardians");
	}
	
	@Test 
	void addOwnerValidation() {
		when(myMockRepo.save(mockSingleOwner)).thenReturn(mockSingleOwner);
		OwnerData results = service.saveOwner(new OwnerData(mockSingleOwner));
		
		assertThat(results.getOwnerFirstName() == "Peter");
		assertThat(results.getOwnerLastName() == "Parker");
		assertThat(results.getCompanyName() == "Spider Co.");
	}
	
	@Test 
	void saveOwnerWithInvalidId() {
		when(myMockRepo.save(mockInvalidId)).thenReturn(mockInvalidId);
		assertThatThrownBy(() -> ((Assert<?, Boolean>) service.saveOwner(new OwnerData(mockInvalidId))).isInstanceOf(NoSuchElementException.class));
	}

}
