package com.promineotech.person.model;

public class Person {

	private int personPK;
	private String personName;
	
	public Person(int personPK, String personName) {
		this.personPK = personPK;
		this.personName = personName;
	}

	public int getPersonPK() {
		return personPK;
	}

	public String getPersonName() {
		return personName;
	}
}
