package com.promineotech.person.service;

import java.util.NoSuchElementException;

import com.promineotech.person.dao.PersonDao;
import com.promineotech.person.model.Person;

public class PersonService {
	private PersonDao personDao = new PersonDao(); 
	
	public void createPersonTable() {
		personDao.createPersonTable();
		
	}

	public void dropPersonTable() {
		personDao.dropPersonTable();
		
	}

	public void addPerson(String name) {
		personDao.addPerson(name);
	}

	public String findName(String name) {
		Person person = personDao.findPerson(name).orElseThrow(
				() -> new NoSuchElementException("The name " + name + " is not found"));
		
		String resultName = person.getPersonName();
		return resultName.substring(0,1).toUpperCase() + resultName.substring(1);
	}

}
