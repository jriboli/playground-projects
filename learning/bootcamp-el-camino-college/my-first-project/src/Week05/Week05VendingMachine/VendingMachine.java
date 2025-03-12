package Week05.Week05VendingMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {
	
	private Map<String, ItemSlot> spaces = new HashMap<String, ItemSlot>();
	private double money = 0.00;
	private final int ROWS = 8; // these will be A - Z
	private final int COLUMNS = 6;
	
	public VendingMachine() {
		populate();
	}
	
	private void populate() {
		List<String> items = new ArrayList<String>(Arrays.asList("Doritos", "Cheetos", "Snickers", "Butterfinger", "MnMs", "Oreos", "Mt. Dew", "Coke"));
		for(String item : items) {
			switch(item) {
			case "Doritos":
			case "Cheetos":
				Item i1 = new Item(item);
				ItemSlot itemSlot = new ItemSlot(i1, 1.00, 8);
				populateRow(itemSlot, items.indexOf(item));
				break;
			case "Snickers":
			case "Butterfinger":
			case "MnMs":
			case "Oreos":
				Item i2 = new Item(item);
				ItemSlot itemSlot2 = new ItemSlot(i2, 0.75, 10);
				populateRow(itemSlot2, items.indexOf(item));
				break;
			case "Mt. Dew":
			case "Coke":
				Item i3 = new Item(item);
				ItemSlot itemSlot3 = new ItemSlot(i3, 1.25, 6);
				populateRow(itemSlot3, items.indexOf(item));
				break;
			default:
				//99.00
			}
		}
	}
	
	private void populateRow(ItemSlot itemSlot, int row) {
		List<String> alphabet = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I"));
		for(int i = 0; i < COLUMNS; i++) {
			String location = (row + 1) + alphabet.get(i);
			System.out.println(String.format("Adding Row - %s", location));
			addNewItem(itemSlot, location);
		}
	}
	
	public void describe() {
		List<String> alphabet = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I"));
		for(int i = 1; i <= ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				String location = i + alphabet.get(j);
				System.out.print(String.format("Location (%s): ", location));
				spaces.get(location).describe();
			}
		}
	}
	
	public void addMoney(double money) {
		this.money += money;
	}
	
	public Return buy (String itemLocation) {
		ItemSlot selectedItem = findItemAtLocation(itemLocation);
		Return result;
		
		// Check if valid location
		if(selectedItem == null) {
			System.out.println("No items at that location");
			return null;
		} 
		
		
		// Check if enough money
		if(money > selectedItem.getPrice()) {
			if(selectedItem.getQuantity() > 0) {
				double change = money - selectedItem.getPrice();
				result = new Return(selectedItem.getItem(), change);
				selectedItem.addQuantity(-1);
				money = 0.00;
			} else {
				System.out.println("The quantity at that location is zero");
				return null;
			}
		} else {
			System.out.println(String.format("You dont have enough - cost: %f | money: %f", selectedItem.getPrice(), money));
			return null;
		}	
		
		return result;
	}
	
	public void addNewItem(ItemSlot itemSlot, String itemLocation) {
		ItemSlot selectedItem = findItemAtLocation(itemLocation);
		
		if(selectedItem == null) {
			ItemSlot newItem = itemSlot;
			spaces.put(itemLocation, itemSlot);
		} else {
			System.out.println("That space is already taken by " + selectedItem.getItem().getName());
		}
	}
	
	public void addItemQuatity(String itemLocation, int addHowMany) {
		ItemSlot selectedItem = findItemAtLocation(itemLocation);
		
		if(selectedItem != null) {
			selectedItem.addQuantity(addHowMany);
		}
		
	}
	
	private ItemSlot findItemAtLocation(String itemLocation) {
		return spaces.getOrDefault(itemLocation, null);
	}
	

}
