package org.example.iterable.entity;

import java.util.Iterator;
import java.util.List;

public class PersonList implements Iterable<Person> {

    private List<Person> persons;

    public PersonList(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public Iterator<Person> iterator() {
        return persons.iterator();
    }
}