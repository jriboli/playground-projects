package org.example.comparator.entity;

public class Student implements Comparable<Student> {
    private String name;
    private int rollNumber;
    private int grade;

    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNumber=" + rollNumber +
                ", grade=" + grade +
                '}';
    }

    public Student(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return this.name;
    }

    public int getRollNumber() {
        return this.rollNumber;
    }

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public int compareTo(Student that) {
        return this.name.compareTo(that.name);
    }
}
