package Week05;

public class SpacedLogger implements Logger {

	@Override
	public void log(String message) {		
		System.out.println(addSpaces(message));
		
	}

	@Override
	public void error(String message) {		
		System.out.println("ERROR: "+addSpaces(message));
		
	}
	
	private String addSpaces(String message) { 
		String newString = "";
		for(int i = 0; i < message.length(); i++)
			newString += message.charAt(i) + " ";
		
		return newString;
	}

}
