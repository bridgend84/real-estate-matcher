package com.codecool.realestatematcher.logic;

import com.codecool.realestatematcher.data.Customer;
import com.codecool.realestatematcher.data.Estate;

import java.util.function.Predicate;

public class EstateTypeMatcher implements Predicate<Estate> {
    private final Customer customer;
    public EstateTypeMatcher(Customer customer) {
        this.customer = customer;
    }
    @Override
    public boolean test(Estate estate) {
        return customer.getPreferredTypes().isEmpty() ||
                customer
                .getPreferredTypes()
                .stream()
                .anyMatch(estateType -> estate.type().equals(estateType));
    }
}
