package Week05;

public class Week05CodingProject{

	public static void main(String[] args) {
		Logger aLog = new AstriskLogger();
		Logger bLog = new SpacedLogger();
		
		testOutLog(aLog);
		System.out.println(""); // Adding some space in between
		testOutLog(bLog);
		
	}
	
	public static void testOutLog(Logger log) {
		log.log("Allowing Rocket Raccoon access...");
		log.error("Everything is going to blow!");
	}
}
