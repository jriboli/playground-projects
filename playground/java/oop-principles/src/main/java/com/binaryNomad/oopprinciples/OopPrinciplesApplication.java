package com.binaryNomad.oopprinciples;

import com.binaryNomad.oopprinciples.library_management.Library;
import com.binaryNomad.oopprinciples.library_management.books.Book;
import com.binaryNomad.oopprinciples.library_management.books.CookBook;
import com.binaryNomad.oopprinciples.library_management.books.Magazine;
import com.binaryNomad.oopprinciples.library_management.books.ScienceFictionBook;
import com.binaryNomad.oopprinciples.online_shopping.Cart;
import com.binaryNomad.oopprinciples.online_shopping.Order;
import com.binaryNomad.oopprinciples.online_shopping.payment.CreditCard;
import com.binaryNomad.oopprinciples.online_shopping.payment.GiftCard;
import com.binaryNomad.oopprinciples.online_shopping.payment.PayPal;
import com.binaryNomad.oopprinciples.online_shopping.payment.Payment;
import com.binaryNomad.oopprinciples.online_shopping.product.ActionFigure;
import com.binaryNomad.oopprinciples.online_shopping.product.Cellphone;
import com.binaryNomad.oopprinciples.online_shopping.product.GameConsole;
import com.binaryNomad.oopprinciples.online_shopping.product.Product;
import com.binaryNomad.oopprinciples.vehicle_rental.*;
import com.binaryNomad.oopprinciples.vehicle_rental.vehicle.Bike;
import com.binaryNomad.oopprinciples.vehicle_rental.vehicle.Car;
import com.binaryNomad.oopprinciples.vehicle_rental.vehicle.Truck;
import com.binaryNomad.oopprinciples.vehicle_rental.vehicle.Vehicle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class OopPrinciplesApplication {

	public static void main(String[] args) {

		SpringApplication.run(OopPrinciplesApplication.class, args);

		//TestLibrary();
		//TestRental();
		TestOnlineCart();

	}

	public static void TestLibrary() {
		Library library = new Library();
		List<Book> books = List.of(
				new ScienceFictionBook("Ready Player One", 348, null,
						List.of(4,5,3,4,4,4,1)),
				new ScienceFictionBook("The Martian", 506, null,
						List.of(5,4,2,2,2,3)),
				new ScienceFictionBook("World War Z", 634, null,
						List.of(5,5,5,4,4,5,5)),
				new CookBook("American Grilling", 102, 5, 34, null,
						List.of(10,3,14,12,30)),
				new CookBook("Tequila Mockingbird", 74, 2, 27, null,
						List.of(20,23,27,15)),
				new Magazine("The New Yorker", 53, "The New Yorker")
		);

		library.setBooks(books);

		List<Book> sortedBooks = library.sortBooks();
		sortedBooks.forEach(b -> System.out.println(b.getTitle()));

		System.out.println(library.findByTitle("Ready Player One"));
	}

	public static void TestRental() {
		RentalAgency agency = new RentalAgency("City Rentals");

		Vehicle car = new Car("ABC123", "Toyota Corolla", 40.0, 5);
		Vehicle bike = new Bike("XYZ789", "Yamaha FZ", 20.0, true);
		Vehicle truck = new Truck("LMN456", "Volvo Truck", 100.0, 2000.0);

		agency.addVehicle(car);
		agency.addVehicle(bike);
		agency.addVehicle(truck);

		Customer customer = new Customer("Rocket Raccoon");

		agency.rentVehicle(customer, car);
		System.out.println("At Agency: ");
		agency.getAvailableVehicles().forEach(v -> System.out.println(v.getModel()));

		agency.rentVehicle(customer, bike);
		System.out.println("At Agency: ");
		agency.getAvailableVehicles().forEach(v -> System.out.println(v.getModel()));

		customer.driveVehicle(bike);

		agency.returnVehicle(customer, truck);
	}

	public static void TestOnlineCart() {

		Product iphone = new Cellphone(1299.95, "Iphone 14", "This is the next greatest thing.", List.of("TMobile", "Verizon", "AT&T"));
		Product stormtrooper = new ActionFigure(49.50, "Samurai StormTrooper", "A Star Wars stormtrooper in classis samurai armor", "Mint");
		Product playstation = new GameConsole(499.99, "Playstation 5" , "Playstations latest gaming console", "Sony", 1000);

		Payment giftcard = new GiftCard("123456789876543", "1111", "04/29");
		Payment creditcard = new CreditCard("111111111111111", "9876", "01/34");
		Payment payPal = new PayPal("rocket", "123ABC");

		Cart cart = new Cart();
		cart.addItem(stormtrooper, 1);
		cart.displayCart();

		Order order = new Order();
		order.checkOut(cart);
		order.addPayment(giftcard);
		order.addShipping("Priority", "123 Main St., Boston, MA");
		System.out.println("Total Cost: " + order.calculateTotal());

		order.commit();
	}

}
