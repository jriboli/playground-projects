package Week01;

public class OpenClassWk01Part01 {

public static void main(String[] args) {
		
		// WRITE YOUR CODE HERE
		boolean a = true;
	
	
		
    /*****DO NOT CHANGE THE CODE BELOW THIS LINE*****/		
		checkType(a);
	}
	
	static void checkType(Object o) {
	    if (o instanceof String) {
	        System.out.println("String");
	    } else if (o instanceof Integer) {
	        System.out.println("int");
	    } else if (o instanceof Double) {
	        System.out.println("double");
	    } else if (o instanceof Character) {
	        System.out.println("char");
	    } else if (o instanceof Boolean) {
	        System.out.println("boolean");
	    }
	}

}
