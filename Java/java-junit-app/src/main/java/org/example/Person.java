package org.example;

public class Person {
    private String firstName;
    private String lastName;
    private byte age;

    public Person(String fName, String lName){
        this.firstName = fName;
        this.lastName = lName;
        age = 1;
    }

    public Person(String fName, String lName, byte age){
        // Interesting note, overriding Constructor must be first line in code block
        this(fName, lName);
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public byte getAge() {
        return age;
    }

}
