package com.promineotech.person.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonServiceTest {
	private PersonService personService;

	@BeforeEach
	void setUp() throws Exception {
		personService = new PersonService();
		personService.createPersonTable(); 
	}
	
	@AfterEach
	void tearDown() throws Exception {
		personService.dropPersonTable();
	}

	@Test
	void assertThatNamesAreCorrectlyAddedToTheDatabase() {
		// Given : a list of names
		List<String> names = List.of("sarah", "susan", "stephanie");
				
		// When : the names are retrieved from the database
		names.forEach(name -> personService.addPerson(name));
		
		// Then : the names are capitalized correctly
		assertThat(personService.findName("sarah")).isEqualTo("Sarah");
		assertThat(personService.findName("susan")).isEqualTo("Susan");
		assertThat(personService.findName("stephanie")).isEqualTo("Stephanie");
		
		assertThatThrownBy(() -> personService.findName("maria")).isInstanceOf(NoSuchElementException.class);
	}

}
