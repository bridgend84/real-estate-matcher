package com.codecool.realestatematcher.data;

public class Rent extends Sale{
    private final Period period;
    public Rent(int price, Currency currency, Period period, SaleOptionName name) {
        super(price, currency, name);
        this.period = period;
    }

    public Period getPeriod() {
        return period;
    }
}
