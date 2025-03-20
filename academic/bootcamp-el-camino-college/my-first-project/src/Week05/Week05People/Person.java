package Week05.Week05People;

public class Person {
	private String firstName;
	private String lastName;
	private int age;
	
	public Person(String first, String last, int age) {
		firstName = first;
		lastName = last;
		setAge(age);
	}
	
	public void describe() {
		System.out.println(String.format("Name: %s %s | Age: %d", firstName, lastName, age));
	}
	
	public int getAge() {
		return age;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	private void setAge(int age) {
		if(age > 0 && age < 120) {
			this.age = age;
		} else {
			System.out.println("Invalid Age.");
		}		
	}
}
