package org.example.iterable.demo;

import org.example.iterable.entity.Person;
import org.example.iterable.entity.PersonList;

import java.util.List;

// Problem:
// You have a custom class Person with a list of persons.
// Implement an iterable for the class to iterate over the persons and print their names.
public class IntermdiateProblem {

    public static void main(String[] args) {
        List<Person> persons = List.of(
                new Person("Alice", 35),
                new Person("Bob", 49),
                new Person("Charlie", 22)
        );

        PersonList personList = new PersonList(persons);

        for (Person person : personList) {
            System.out.println(person);
        }
    }
}
