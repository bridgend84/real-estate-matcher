package com.codecool.realestatematcher.logic;

import com.codecool.realestatematcher.data.*;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class EstateMatchCalculator {
    private final Customer customer;
    private final List<Estate> estates;
    private final List<Predicate<Estate>> filterList;
    private final Predicate<Sale> preferredSaleTypeProvider;
    private final double discount;
    public EstateMatchCalculator(
            Customer customer,
            List<Estate> estates,
            List<Predicate<Estate>> filterList,
            double discount) {
        this.customer = customer;
        this.estates = estates;
        this.filterList = filterList;
        this.preferredSaleTypeProvider = new PreferredSaleTypeProvider(customer);
        this.discount = Math.min(discount, 1.0);
    }
    public List<Estate> getEstateMatches() {
        Stream<Estate> estateStream = estates.stream();
        for (Predicate<Estate> filter : filterList) {
            estateStream = estateStream.filter(filter);
        }
        return estateStream
                .filter(estate -> !customer.getPreferredEstateIds().contains(estate.id()))
                .map(estate ->
                        new Estate(
                                estate.id(),
                                estate.location(),
                                estate
                                        .saleOptions()
                                        .stream()
                                        .filter(preferredSaleTypeProvider)
                                        .map(sale -> new Sale(
                                                customer.hasMemberShip() ?
                                                        (int) (sale.getPrice() * (1 - discount)) : sale.getPrice(),
                                                sale.getCurrency(),
                                                sale.getOptionName()
                                        ))
                                        .toList(),
                                estate.type()))
                .toList();
    }

    public List<Estate> getPreferredEstates() {
        return estates
                .stream()
                .filter(estate -> customer.getPreferredEstateIds().contains(estate.id()))
                .map(estate ->
                        new Estate(
                                estate.id(),
                                estate.location(),
                                estate
                                        .saleOptions()
                                        .stream()
                                        .map(sale -> new Sale(
                                                customer.hasMemberShip() ?
                                                        (int) (sale.getPrice() * (1 - discount)) : sale.getPrice(),
                                                sale.getCurrency(),
                                                sale.getOptionName()
                                        ))
                                        .toList(),
                                estate.type()))
                .toList();
    }
}
