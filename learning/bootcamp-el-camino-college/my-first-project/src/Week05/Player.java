package Week05;

public class Player {
	
	private String name;
	private String position; 
	private String specialty;
	
	public Player(String name, String position, String specialty) {
		this.name = name;
		this.position = position;
		this.specialty = specialty;
	}
	
	public void describe() {
		System.out.println(String.format("Player: %s | Position: %s | Specialty: %s", name, position, specialty));
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	private void setPosition(String position) {
		this.position = position;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

}
