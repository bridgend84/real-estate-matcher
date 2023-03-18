package com.codecool.realestatematcher.logic;

import com.codecool.realestatematcher.data.Customer;
import com.codecool.realestatematcher.data.Estate;

import java.util.function.Predicate;

public class LocationMatcher implements Predicate<Estate> {
    private final Customer customer;

    public LocationMatcher(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean test(Estate estate) {
        return customer.getPreferredLocations().isEmpty() ||
                customer
                        .getPreferredLocations()
                        .stream()
                        .anyMatch(location ->
                                location.country().equals(estate.location().country()) &&
                                        location.region().equals(estate.location().region()) &&
                                        location.city().equals(estate.location().city()) &&
                                        (location.district() == estate.location().district() ||
                                                location.district() == 0));
    }
}
