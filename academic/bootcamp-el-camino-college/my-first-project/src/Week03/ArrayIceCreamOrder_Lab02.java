package Week03;

public class ArrayIceCreamOrder_Lab02 {
	String user;
	String size;
	String cupOrCone;
	String[] flavors = new String[3];

	// Default Constructor
	public ArrayIceCreamOrder_Lab02() { 
		this.user = "";
		this.size = "";
		this.cupOrCone = "";
	}
	// Additional Constructors
	public ArrayIceCreamOrder_Lab02(String user, String[] flavors) {
		this.user = user;
		this.flavors = flavors;
	}
	public ArrayIceCreamOrder_Lab02(String user, String[] flavors, String size, String cupOrCone) {
		this.user = user;
		this.flavors = flavors;
		this.size = size;
		this.cupOrCone = cupOrCone;
	}

	//Getters & Setters
	public String getUser() { return this.user; }
	public void setUser(String user) { this.user = user; }
	public String[] getFlavors() { return this.flavors; }
	public void setFlavors(String[] flavors) { this.flavors = flavors; }
	public String getSize() { return this.size; } 
	public void setSize(String size) { this.size = size; } 
	public String getCupOrCone() { return this.cupOrCone; } 
	public void setCupOrCone(String cupOrCone) { this.cupOrCone = cupOrCone; }

	// Describe Method
	public void describe(int iceCreamCount) {
		int printFlavors = 0;
		int boxSize = 40;
		char star = '*';
	
		for (int i = 0; i < boxSize; i++) {
			System.out.print(star);
		}
		System.out.println("\nIce Cream Order:\n----------------");
		System.out.print("Name:\n\t" + this.user + "\nOrder: \n\t");
		System.out.println(this.size + " " + this.cupOrCone);
	
		System.out.print("Flavors:\n\t");
		while (printFlavors < iceCreamCount) {
			System.out.print(this.flavors[printFlavors++]);
			if (printFlavors == iceCreamCount-1) {
				System.out.print (" & \n\t");
			} else if (printFlavors < iceCreamCount-1) {
				System.out.print (", \n\t");
			}
		}
	
		System.out.println();
		for (int i = 0; i < boxSize; i++) {
			System.out.print(star);
		}
	}
}
