package Week12;

public class Week12Research {
	/*
	 * 1. What is the difference between TDD and BDD?
	 * 
	 * ANSWER: (https://www.geeksforgeeks.org/difference-between-bdd-vs-tdd-in-software-engineering/)
	 * 
	 * 	There are several differences between Test Driven Design and Behavior Driven Design. TDD focuses mainly on the 
	 * implementation of features, while BDD focus on the applications behavior. For BDD the main focus is system requirements,
	 * while with TDD the focus is on unit tests. BDD is a team methodology, compared to TDD which is a development practice. And
	 * so on. 
	 * 
	 * 
	 * 2. What does mocking a class allow you to do? (https://www.baeldung.com/mockito-mock-methods)
	 * 
	 * ANSWER:
	 * 
	 * 	Using something like Mockito to help create a mock version of a class, this allows us to control certain aspects
	 * of the class. Such as controlling the output of a method. This can be useful in creating stable unit tests. Even
	 * when the results could be random, we can control it to be static. 
	 * 
	 * 3. What is the value in separating your code into controller, service, and data access layers rather than keeping it all in the same files?
	 * 
	 * ANSWER:
	 * 
	 * 	This allows use several benefits. One related to unit testing is it allows us to test each aspect separately. 
	 * If we make changes to the service we only need to run tests in the service level. This helps in deployments as well.
	 * Each layer can be deployed, updated, reworked separately. And so on. 
	 * 
	 * 
	 * 4. Why would you want to avoid putting credentials in plaintext in your code?
	 * 
	 * ANSWER:
	 * 
	 * 	Easy, for security purposes. Always assume a hacker may get a hold of your code, html, post request. We need to be
	 * vigilant and protect ourselves from this low hanging fruit. 
	 * 
	 * 
	 * 5. What is one method that can be used to avoid putting plaintext database usernames and passwords into your code?
	 * 
	 * ANSWER: (https://www.twilio.com/blog/working-with-environment-variables-in-java)
	 * 
	 * 	You can create an Environment file. This file would not be upload to GitHub or backup system. It would only
	 * remain on your local PC. 
	 * 
	 */
}
