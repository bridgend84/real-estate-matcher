package com.codecool.realestatematcher.logic;

import com.codecool.realestatematcher.data.Customer;
import com.codecool.realestatematcher.data.Estate;

import java.util.function.Predicate;

public class SaleLimitMatcher implements Predicate<Estate> {
    private final Customer customer;

    public SaleLimitMatcher(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean test(Estate estate) {
        PreferredSaleTypeProvider preferredSaleTypeProvider = new PreferredSaleTypeProvider(customer);
        return estate.saleOptions().stream().anyMatch(preferredSaleTypeProvider);
    }
}
