package com.binaryNomad.oopprinciples;

import com.binaryNomad.oopprinciples.library_management.Library;
import com.binaryNomad.oopprinciples.library_management.books.Book;
import com.binaryNomad.oopprinciples.library_management.books.CookBook;
import com.binaryNomad.oopprinciples.library_management.books.Magazine;
import com.binaryNomad.oopprinciples.library_management.books.ScienceFictionBook;
import com.binaryNomad.oopprinciples.vehicle_rental.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class OopPrinciplesApplication {

	public static void main(String[] args) {

		SpringApplication.run(OopPrinciplesApplication.class, args);

		//TestLibrary();
		TestRental();


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

}
