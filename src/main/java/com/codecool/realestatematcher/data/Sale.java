package com.codecool.realestatematcher.data;

public class Sale {
    private final int price;
    private final Currency currency;
    private final SaleOptionName name;

    public Sale(int price, Currency currency, SaleOptionName name) {
        this.price = price;
        this.currency = currency;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public Currency getCurrency() { return currency; }

    public SaleOptionName getOptionName() {
        return name;
    }
}
