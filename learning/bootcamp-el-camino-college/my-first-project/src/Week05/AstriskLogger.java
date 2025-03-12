package Week05;

public class AstriskLogger implements Logger {

	@Override
	public void log(String message) {
		System.out.println(String.format("***%s***", message));
		
	}

	@Override
	public void error(String message) {
		String correctAstriskLengthStr = "";
		for(int i = 0; i < message.length() + 12; i++) 
			correctAstriskLengthStr += "*";
		
		System.out.println(correctAstriskLengthStr);
		System.out.println(String.format("***Error: %s***", message));
		System.out.println(correctAstriskLengthStr);
		
	}

}
