import com.codewithmosh.collections.*;
import com.codewithmosh.exceptions.ExceptionsDemo;
import com.codewithmosh.generics.GenericList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        ExceptionsDemo.show();

        GenericList<String> genericList = new GenericList<>();
        genericList.add("Rocket");
        genericList.add("Drax");
        genericList.add("Gamora");

        // Initially this wont work because the GenericList class does not
        // implement the ITERABLE interface
        var iterator = genericList.iterator();
        // [a, b, c]
        //
        while(iterator.hasNext()) {
            var current = iterator.next();
            System.out.println(current);
        }

        // Literally for each loop is syntactical sugar for the above.
        for(String name : genericList) {
            System.out.println(name);
        }

        // If you look at the byte code for the While and For loops
        // they use the same .hasNext and .next methods

        // -------------------------------------------------------------

        CollectionsDemo.show();
        // -------------------------------------------------------------

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Rocket", "e3@guardians.com"));
        customers.add(new Customer("Drax", "e2@guardians.com"));
        customers.add(new Customer("Gamora", "e1@guardians.com"));
        Collections.sort(customers);
        System.out.println(customers);
        // ------------------------
        // One of sort() overloads accepts a Comparator class
        Collections.sort(customers, new EmailComparator());
        System.out.println(customers);
        // -------------------------------------------------------------
        QueueDemo.show();
        // -------------------------------------------------------------
        SetDemo.show();
        // -------------------------------------------------------------


    }
}