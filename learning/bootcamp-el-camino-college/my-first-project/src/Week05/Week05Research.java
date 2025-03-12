package Week05;

public class Week05Research {
	
	/*OOP:

		1. What are the four pillars of Object-Oriented Programming? Explain each pillar.

		2. What is the relationship between a Class and an Object?
	 */
	
	/*
	 * Answer (https://www.geeksforgeeks.org/introduction-of-object-oriented-programming/): 
	 * 
	 * 	The four pillars of OOP are: Encapsulation, Abstraction, Inheritance, and Polymorphism. 
	 * 
	 *	Abstraction, is where we hide the inner implementation away. As the user should not care about it. Like a remote, we do 
	 *	not care about the circuit board underneath. We only interact with the buttons on top. These buttons are our methods. This allows
	 *	us to change the implementation without affecting the user buttons/public methods.
	 *
	 *	Encapsulation, is the process of keeping our data clean and hidden. We only expose the data a user may need, but even then we have gates 
	 *	for them. These are called getters and setters. They allow the user data, but only what we want them to have. And we can do internal 
	 *	validations where needed. Say age, can never be less than 0. 
	 *
	 *	Inheritance, is the ability of a class to pull attributes from another class/interface. This allows us to do two things, one is reduce
	 *	code if we find same method/code block used in several classes and are similar. 
	 *
	 *	Polymorphism, this allows a class to show up in many forms. Take for example a interface vehicle, where several classes implement this interface.
	 *	The interface will have basic functionality of a vehicle, drive(), honk(), etc. But these may be different for each vehicle. Polymorphism allows 
	 *	us to code against the vehicle contract, and not worry which class we are using ( say Car, Truck, Boat, etc. ). 
	 *
	 *	------------------------------------------------------------------------------------
	 *
	 *	The relationship between Class and Object is this. Class is the blueprint/contract, and Object is the tangible item like a house or car. Object is
	 *	the instantiation of a Class. Were we can store information about the object. Example a car class, may have a constructor that takes "amountOfWheels". 
	 *	We can not interact with the car class by default we would need to create a car. Say new Car(2), motorcycle, and new Car(4) truck. Once we create the 
	 *	tangible object we can interact with it. Like motorcycle.move() and truck.honk(). 
	 */

	/*Advanced OOP:

		3. What are the differences between abstract classes and interfaces? When should you use one over the other?

		4. Research the SOLID principles of Object-Oriented Programming (OOP) as introduced by Robert Martin. List the principles, and give a description of each one.
	 */
	
	/*
	 * Answer (https://www.geeksforgeeks.org/solid-principle-in-programming-understand-with-real-life-examples/): 
	 * 
	 * 	There are several differences between an abstract class and an interface. One big one is that a class can only inherit from one abstract class, 
	 * 	but from multiple interfaces. Separately an abstract class can have some implementation and use public/private/protected/final modifiers. While a
	 * 	interface is only public and has no implementation. Abstract class can define variables, a interface only supports final or static. You can choose
	 * 	one over the other with a few thoughts:
	 * 	- Similar classes with same code/duplication - An abstract class may help
	 * 	- Also similar classes with similar variables/methods - might look into abstract class
	 * 	- For multi-inheritance - Use interface
	 * 	- If you want to define the actions, but now how they are done - Use interface
	 * 
	 * ---------------------------------------------------------------------------------------------
	 * 
	 * 	SOLID, which states from Single Responsibility Principle, Open/Closed Principle, Liskov's Substitution Principle, Interface Segregation Principle, and 
	 * 	lastly Dependency Inversion Principle. 
	 * 
	 * 	Single Responsibility Principle, states that a class should only have one reason to change. Meaning a class should only have one job/single purpose. 
	 * 	And if that purpose changes the class would need to change. It shouldn't be impacted by anything else. 
	 * 
	 *  Open/Close Principle, states software entities should be open for extension but closed for modification. Which means you should be able to extend
	 *  a class behavior without modifying it. 
	 *  
	 *  Liskov's Substitution Principle, child classes must be substitutable for their base or parent classes. This mean we should be able to use a child class
	 *  in place of it's parent without any issue. 
	 *  
	 *  Interface Segregation Principle, states do not force a client to implement an interface which is irrelevant to them. Similar to 'S' we should not have
	 *  an interface with a bunch of purposes - fat interface. Instead we should have many small interfaces for single purposes. 
	 *  
	 *  Dependency Inversion Principle, this is a hard one to explain. But in short high level classes should not directly depend on low level classes, and
	 *  vise versa. This would cause tight coupling. Where changing one class could have a cascading affect on other classes that use it. Instead we should 
	 *  abstract.
	 */

	/*Exceptions:

		5. What is an exception?

		6. What are the differences between checked and unchecked exceptions?
	 */
	
	/*
	 * Answer (https://www.geeksforgeeks.org/checked-vs-unchecked-exceptions-in-java/):
	 * 
	 * 	An exception is when the code has encountered an unhandled error - say NullPointerException. 
	 * 
	 * ---------------------------------------------------------------------------------------
	 * 
	 * 	The difference between a checked vs unchecked exception is this. Checked exceptions are found at compile time. An unchecked exception is the opposite. 
	 * 	Unchecked are not found at compile time and usually consist of logically/coding errors - like NullPointerException. Where we did not check to make
	 * 	sure a variable had a found before performing some action on it. 
	 */

	/*Testing:

		7. What is unit testing and why is it important?
	 */
	
	/*
	 * Answer: 
	 * 
	 * 	Unit testing is the testing of a single unit of a software application. This would typically be at the method level. These tests would be designed 
	 * 	to be run in isolation. Meaning they should not rely/depend on other parts of the application to work. They test a single use case. Say for example
	 * 	we have an add method that takes two integers. We could create several unit tests for this method. Checking both happy path and negative path outcomes.
	 * 	Unit testing is important in that it helps ensure an application is running the same before and after some changes. Say you make some changes to a
	 * 	method and want to confirm with high confidence your changes didnt change behavior in any unexpected way. You can run the unit tests. 
	 * 
	 * 	If after your change unit tests start failing, this is typically because your change had a ill effect on the method behavior. This give you a quick
	 * 	feedback cycle. 
	 */
}
