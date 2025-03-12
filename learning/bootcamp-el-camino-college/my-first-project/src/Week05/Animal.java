package Week05;

public class Animal {
	String name;
	String type;
	double weight;
	double height;
	String location;

	public Animal() {
	}

	public Animal(String name, String type, double weight, double height, String location) {
		this.name = name;
		this.type = type;
		this.weight = weight;
		this.height = height;
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return this.height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void describe() {
		System.out.println("Animal Description");
		System.out.println("------------------");
		System.out.println("\tName: " + name);
		System.out.println("\tType: " + type);
		System.out.println("\tWeight: " + weight + " lb.");
		System.out.println("\tHeight: " + height + " in.");
		System.out.println("\tLocated at: " + location);
	}
}
