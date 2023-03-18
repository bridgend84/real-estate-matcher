package com.codecool.realestatematcher.data;

import java.util.ArrayList;
import java.util.List;

public class Populate {
    public List<Estate> generateEstates() {
        Estate house = new Estate(
                0,
                new Location("Hungary", "Budapest", "Budapest", 18),
                List.of(new Sale(100000, Currency.EUR, SaleOptionName.SALE),
                        new Rent(5000, Currency.EUR, Period.YEAR, SaleOptionName.RENT)),
                EstateType.HOUSE);
        Estate apartment = new Estate(
                1,
                new Location("Hungary", "Budapest", "Budapest", 8),
                List.of(new Rent(300, Currency.EUR, Period.MONTH, SaleOptionName.RENT)),
                EstateType.APARTMENT);
        Estate apartment2 = new Estate(
                2,
                new Location("Austria", "Wien", "Wien", 4),
                List.of(new Sale(120000, Currency.EUR, SaleOptionName.SALE)),
                EstateType.APARTMENT);
        Estate office = new Estate(
                3,
                new Location("Austria", "Wien", "Wien", 6),
                List.of(new Rent(1000, Currency.EUR, Period.MONTH, SaleOptionName.RENT),
                        new Sale(200000, Currency.EUR, SaleOptionName.SALE)),
                EstateType.OFFICE);
        Estate land = new Estate(
                4,
                new Location("Hungary", "Pest", "Monor", 0),
                List.of(new Sale(10000, Currency.EUR, SaleOptionName.SALE)),
                EstateType.LAND);
        Estate garage = new Estate(
                5,
                new Location("Hungary", "Budapest", "Budapest", 3),
                List.of(new Rent(10000, Currency.HUF, Period.MONTH, SaleOptionName.RENT)),
                EstateType.GARAGE
        );
        return List.of(house, apartment, apartment2, office, land, garage);
    }

    public List<Customer> generateCustomers() {
        Customer customer = new Customer(
                "userWantApartment@gmail.com",
                List.of(new Location("Hungary", "Budapest", "Budapest", 0)),
                List.of(EstateType.APARTMENT, EstateType.GARAGE),
                List.of(new Rent(400, Currency.EUR, Period.MONTH, SaleOptionName.RENT),
                        new Rent(20000, Currency.HUF, Period.MONTH, SaleOptionName.RENT)),
                List.of(1),
                true);
        Customer customer2 = new Customer(
                "userWantOffice@gmail.com",
                List.of(new Location("Austria", "Wien", "Wien", 0)),
                List.of(EstateType.APARTMENT, EstateType.OFFICE),
                List.of(new Rent(1200, Currency.EUR, Period.MONTH, SaleOptionName.RENT),
                        new Sale(150000, Currency.EUR, SaleOptionName.SALE)),
                List.of(3),
                false);
        Customer customer3 = new Customer(
                "userWantLand@email.com",
                List.of(new Location("Hungary", "Pest", "Monor", 0)),
                List.of(EstateType.LAND),
                List.of(new Sale(12000, Currency.EUR, SaleOptionName.SALE)),
                new ArrayList<>(),
                false);
        Customer customer4 = new Customer(
                "userWantHouse@email.com",
                new ArrayList<>(),
                List.of(EstateType.HOUSE, EstateType.APARTMENT),
                new ArrayList<>(),
                new ArrayList<>(),
                true);
        return List.of(customer, customer2, customer3, customer4);
    }
}
