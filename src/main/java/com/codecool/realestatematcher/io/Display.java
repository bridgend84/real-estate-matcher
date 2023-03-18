package com.codecool.realestatematcher.io;

import com.codecool.realestatematcher.data.Customer;
import com.codecool.realestatematcher.data.Estate;
import com.codecool.realestatematcher.data.Rent;

public class Display {
    public void displayEstate(Estate estate) {
        System.out.println(
                estate.id() + " " +
                estate.type().name() + " " +
                estate.location().country() + " " +
                        estate.location().region() + " " +
                        estate.location().city() + " " +
                        (estate.location().district() == 0 ?
                                "" : estate.location().district() + ".district")
        );
        estate.saleOptions().forEach(sale ->
                System.out.println(
                        sale.getOptionName().name() + " " +
                        sale.getPrice() + " " +
                        sale.getCurrency().name() +
                        (sale.getClass().equals(Rent.class) ?
                                "/" + ((Rent) sale).getPeriod().name() : "")
                ));
    }

    public void displayCustomer(Customer customer) {
        String user = customer.getEmail() + (customer.hasMemberShip() ? " MEMBER" : " NOT MEMBER");
        System.out.println(user);
        System.out.println("*".repeat(user.length()));
    }
}
