package Week05.Week05People;

public class Week05PeopleLab {
	
	public static void main(String[] args) {

		
		System.out.println("-------------------------");
		System.out.println("Week 05 Object Lab\n    Class Creation: ");
		
		System.out.println("-------------------------");
		/*
		 *  1. Person Class:	
		 *		Create a class called Person with fields firstName, lastName, and age. 
		 * 			Make sure any age assigned must be valid (i.e. non negative and < 120)
		 */
		System.out.println("\nQuestion 1: Create Class & Instantiate\n ");

		/*
		 * Instantiate Person objects here
		 */
		Person p1 = new Person("Rocket", "Raccoon", 32);
		p1.describe();
		
		
		/*
		 *  2. Declare 2 Instances of Person:
		 *		Create 2 different instances of Person with different values for the fields.
		 */
		System.out.println("-------------------------");
		System.out.println("\nQuestion 2: Instantiate & call describe(): \n");

		/*
		 * Instantiate the object & call describe() here
		 */
		Person p2 = new Person("Peter", "Quill", 26);
		p2.describe();
	
		
		
		/*
		 *  3. Who is Older?
		 *
		 *		Create and test a method that takes a two Persons and returns the firstName
		 * 			of the Person who is older.
		 */
		System.out.println("-------------------------");
		System.out.println("\nQuestion 3: Create & call a method  \n");
		System.out.println("The older person is:\n-------------------");

		/*
		 * Call to the new method here
		 */
		System.out.println(whoIsOlder(p1, p2));

		

		
		System.out.println("-------------------------");
	
	}

	/* 
	 * Method -- Question 3:
	 */
	public static String whoIsOlder(Person p1, Person p2) {
		if(p1.getAge() > p2.getAge()) {
			return p1.getFirstName();
		} else {
			return p2.getFirstName();
		}
	}
}
