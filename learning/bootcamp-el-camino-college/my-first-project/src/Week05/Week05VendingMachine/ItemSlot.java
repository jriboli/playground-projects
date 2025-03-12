package Week05.Week05VendingMachine;

public class ItemSlot {

	private Item item;
	private double price;
	private int quantity;
	
	public ItemSlot(Item item, double price, int quantity) {
		this.item = item;
		this.price = price;
		this.quantity = quantity;
	}
	
	public void describe() {
		System.out.println(String.format("%s is %f and there are %d left", item.getName(), price, quantity));
	}
	
	public void changePrice(double price) { 
		if(price < 0.00)
			System.out.println("Price can not be less than 0.00");
		else 
			price = price;
	}
	
	public void addQuantity(int addHowMany) {
		quantity += addHowMany;
	}
	
	public Item getItem() {
		return item;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getQuantity() {
		return quantity;
	}
}
