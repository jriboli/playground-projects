package Week05;

public class Student {
	static int numberOfStudent; 
	
	private String firstName;
	private String lastName;
	private String phoneNumber;
	int gradeLevel;
	
	public Student(String first, String last, String phone) {
		numberOfStudent++;
		
		firstName = first;
		lastName = last;
		phoneNumber = phone;
	}
	
	public void introduce() {
		System.out.println("Hello, my name is " + firstName + " " + lastName);
		System.out.println("I am in grade " + gradeLevel);
		System.out.println("Phone number is " + phoneNumber);
	}
	

}
