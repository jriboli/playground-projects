package Week05.Week05Interface;

public class Week05MayOperationsLab {
	public static void main(String[] args) {
		/*
		 *  MathOperation Interface:
		 *  
		 *
		 *		1. Create an Interface called MathOperation with a method called evaluate that
		 * 			takes two doubles and returns a double.
		 *
		 * 		2. Create four implementing classes:
		 *					a. AdditionOperation
		 *					b. SubtractionOperation
		 * 					c. MultiplicationOperation
		 *					d. DivisionOperation
		 *
		 * 		3. Create a method that takes a MathOperation and two doubles as parameters 
		 *					and returns the result of calling the evaluate method on 
		 *					the MathOperation with the two doubles
		 *
		 *		4. Test your code
		 *
		 */
		System.out.println("-------------------------");
		System.out.println("Week 05 Interface Lab\n      Math Operations: ");
		
		System.out.println("-------------------------");
		System.out.println("Question 1: Create Interface");
		System.out.println("-------------------------");
		System.out.println("Question 2: Create Implementing Classes");
		/*
		 * Instantiate your implementing classes here
		 */
		AdditionOperation add = new AdditionOperation();
		SubtractionOperation minus = new SubtractionOperation();
		MultiplicationOperation multiply = new MultiplicationOperation();
		DivisionOperation divide = new DivisionOperation();
		
		
		
		
		

		System.out.println("-------------------------");
		System.out.println("Question 3: Create Method");
		System.out.println("-------------------------");
		System.out.println("Question 4: Test Your Code");
		/*
		 * Test your code here:
		 * 		- Declare two double variables
		 *      - Call your new method, with all 4 implementing classes
		 *      					  and the two variables.
		 *      - Don't forget to print out the result returned by the method
		 */
		
		

		System.out.println("   Parameters:");
		System.out.println("\tParameter 1: " );
		System.out.println("\tParameter 2: " );

		/* 
		 * Calls to the method using implementing classes
		 */
		System.out.println("   Results:");
		
		System.out.println("\t2a - Addition:  ");
		/*
		 * Put call to method using addition here
		 */
		double addResult = DoMath(add, 2, 2);
		System.out.println("Add 2 + 2 = " + addResult);
		
		
		System.out.println("\t2b - Subtraction:  ");
		/*
		 * Put call to method using subtraction here
		 */
		double minusResult = DoMath(minus, 57, 82);
		System.out.println("Add 57 - 82 = " + minusResult);
		
		System.out.println("\t2c - Multiplication:  ");
		/*
		 * Put call to method using multiplication here
		 */
		double multiplyResult = DoMath(multiply, 18, 27);
		System.out.println("Add 18 * 27 = " + multiplyResult);
		
		
		System.out.println("\t2d - Division:  ");
		/*
		 * Put call to method using division here
		 */
		double divisionResult = DoMath(divide, 3564, 22);
		System.out.println("Add 3564 / 22 = " + divisionResult);
		

		
		System.out.println("-------------------------");
	}
	
	/*
	 * Method -- Question 3:
	 */
	public static double DoMath(MathOperation operation, double x, double y) {
		return operation.evaluate(x, y);
	}

}
