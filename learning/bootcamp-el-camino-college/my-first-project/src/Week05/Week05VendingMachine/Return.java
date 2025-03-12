package Week05.Week05VendingMachine;

public class Return {

	private Item item;
	private double change;
	
	public Return(Item item, double change) {
		this.item = item;
		this.change = change;
	}
	
	public void describe() {
		System.out.println(String.format("Got %s and %f for change", item.getName(), change));
	}
}
