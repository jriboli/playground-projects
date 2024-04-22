package org.example.iterable.demo;

import org.example.iterable.entity.AdvPersonList;
import org.example.iterable.entity.Person;
import org.example.iterable.entity.PersonList;

import java.util.List;

// Problem:
// Extend the Intermediate problem by adding the ability to filter persons based on a certain condition (e.g., age) using a custom iterator.

// Note:
// The choice between filtering in the iterator and using Stream.filter() depends on factors such as efficiency,
// readability, reusability, and the complexity of filtering logic. In many cases, if the dataset is not too large,
// and simplicity and readability are priorities, using Stream.filter() might be a good choice. On the other hand,
// for large datasets with custom or performance-critical filtering logic, filtering within the iterator
// could be a more suitable approach.
public class AdvancedProblem {

    public static void main(String[] args) {
        List<Person> persons = List.of(
                new Person("Alice", 35),
                new Person("Bob", 49),
                new Person("Charlie", 22)
        );

        AdvPersonList personList = new AdvPersonList(persons);

        for (Person person : personList) {
            System.out.println(person);
        }
    }
}
