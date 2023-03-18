package com.codecool.realestatematcher;

import com.codecool.realestatematcher.data.*;
import com.codecool.realestatematcher.io.Display;
import com.codecool.realestatematcher.logic.EstateMatchCalculator;
import com.codecool.realestatematcher.logic.EstateTypeMatcher;
import com.codecool.realestatematcher.logic.LocationMatcher;
import com.codecool.realestatematcher.logic.SaleLimitMatcher;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        Populate populate = new Populate();
        List<Estate> estates = populate.generateEstates();
        List<Customer> customers = populate.generateCustomers();
        Display display = new Display();
        for (Customer customer : customers) {
            LocationMatcher locationMatcher = new LocationMatcher(customer);
            EstateTypeMatcher estateTypeMatcher = new EstateTypeMatcher(customer);
            SaleLimitMatcher saleLimitMatcher = new SaleLimitMatcher(customer);
            EstateMatchCalculator estateMatchCalculator = new EstateMatchCalculator(
                    customer,
                    estates,
                    List.of(locationMatcher, estateTypeMatcher, saleLimitMatcher),
                    0.03);
            display.displayCustomer(customer);
            System.out.println();
            if (!estateMatchCalculator.getPreferredEstates().isEmpty()) {
                System.out.println("MARKED ESTATES:");
                estateMatchCalculator.getPreferredEstates().forEach(display::displayEstate);
                System.out.println();
            }
            if (!estateMatchCalculator.getEstateMatches().isEmpty()) {
                System.out.println("THESE ESTATES MATCHES:");
                estateMatchCalculator.getEstateMatches().forEach(display::displayEstate);
                System.out.println();
            }
        }
    }
}
