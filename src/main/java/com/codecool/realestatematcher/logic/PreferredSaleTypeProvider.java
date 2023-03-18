package com.codecool.realestatematcher.logic;

import com.codecool.realestatematcher.data.Customer;
import com.codecool.realestatematcher.data.Rent;
import com.codecool.realestatematcher.data.Sale;

import java.util.function.Predicate;

public class PreferredSaleTypeProvider implements Predicate<Sale> {

    private final Customer customer;

    public PreferredSaleTypeProvider(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean test(Sale sale) {
        return customer.getSaleLimits().isEmpty() ||
                customer
                        .getSaleLimits()
                        .stream()
                        .anyMatch(saleLimit ->
                                saleLimit.getOptionName().equals(sale.getOptionName()) &&
                                        saleLimit.getCurrency().equals(sale.getCurrency()) &&
                                        saleLimit.getPrice() >= sale.getPrice() &&
                                        (!saleLimit.getClass().equals(Rent.class) ||
                                                ((Rent) saleLimit).getPeriod().equals(((Rent) sale).getPeriod())));
    }
}
