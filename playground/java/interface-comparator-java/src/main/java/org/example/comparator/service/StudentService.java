package org.example.comparator.service;

import org.example.comparator.entity.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentService {
    List<Student> students = new ArrayList<>();

    public void CreateStudents() {
        // Create some studentS
        students.add(new Student("Rocket", 1));
        students.add(new Student("Gamora", 2));
        students.add(new Student("Drax", 3));
        students.add(new Student("Peter", 4));
        students.add(new Student("Groot", 5));
    }

    public void AddGrades() {
        for(var student : students) {
            // Enhanced Switch Statement
            switch (student.getName()) {
                case "Rocket" -> student.setGrade(99);
                case "Gamora" -> student.setGrade(85);
                case "Drax" -> student.setGrade(20);
                case "Peter" -> student.setGrade(72);
                case "Groot" -> student.setGrade(85);
            }
        }
    }

    public void SortByName() {
        // Using the Comparable.compareTo() method within Student
        Collections.sort(students);

        System.out.println("-- SORT BY NAME -------------------------------------------");
        for (var s : students) {
            System.out.println(s);
        }
    }

    public void SortByGrade() {
        // Building a custom comparator
        Comparator<Student> com = new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                if(s1.getGrade() > s2.getGrade()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };

        Collections.sort(students, com);

        System.out.println("-- SORT BY GRADE -------------------------------------------");
        for (var s : students) {
            System.out.println(s);
        }
    }

    public void SortByGradeAndName() {
        // A different custom comparator
        Comparator<Student> com2 = Comparator
                .comparing((Student s) -> s.getGrade())
                .thenComparing(s -> s.getName());

        Collections.sort(students, com2);

        System.out.println("-- SORT BY GRADE THAN NAME -------------------------------------------");
        for (var s : students) {
            System.out.println(s);
        }
    }

    public void SortCleanedUp() {
        // A different custom comparator
        Comparator<Student> com = (i, j) -> i.getGrade() > j.getGrade() ? 1 : -1;

        Collections.sort(students, com);

        System.out.println("-- SORT BY GRADE -------------------------------------------");
        for (var s : students) {
            System.out.println(s);
        }
    }

    public void FilterByName(String containsLetter) {
        var results = students.stream()
                .filter(s -> s.getName().contains(containsLetter))
                .filter(s -> s.getGrade() > 80)
                .toList();

        System.out.println("-- FILTER BY NAME CONTAINS -------------------------------------------");
        for (var s : results) {
            System.out.println(s);
        }
    }
}
