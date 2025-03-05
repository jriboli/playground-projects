package org.example.iterable.entity;

import java.util.Iterator;
import java.util.List;

public class AdvPersonList implements Iterable<Person> {

    private List<Person> persons;

    public AdvPersonList(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public Iterator<Person> iterator() {
        return new AgeFilterIterator(persons.iterator());
    }

    public static class AgeFilterIterator implements Iterator<Person> {
        private Iterator<Person> iterator;

        public AgeFilterIterator(Iterator<Person> iterator) {
            this.iterator = iterator;
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Person next() {
            while (iterator.hasNext()) {
                Person person = iterator.next();
                // Add filter condition
                if(person.getAge() > 25) {
                    return person;
                }
            }
            return null;
        }
    }
}
