package com.codecool.realestatematcher.logic;

import com.codecool.realestatematcher.data.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class EstateMatchCalculatorTest {
    Estate apartment1 = new Estate(
            1,
            new Location("a","a","a",0),
            List.of(
                    new Sale(1000, Currency.EUR, SaleOptionName.SALE),
                    new Rent(100, Currency.EUR, Period.MONTH, SaleOptionName.RENT)
            ),
            EstateType.APARTMENT);
    Estate apartment2 = new Estate(
            2,
            new Location("b","b","b",1),
            List.of(
                    new Sale(2000, Currency.EUR, SaleOptionName.SALE),
                    new Rent(200, Currency.EUR, Period.MONTH, SaleOptionName.RENT)
            ),
            EstateType.APARTMENT);
    Estate apartment3 = new Estate(
            3,
            new Location("c","c","c",2),
            List.of(
                    new Sale(3000, Currency.EUR, SaleOptionName.SALE),
                    new Rent(300, Currency.EUR, Period.MONTH, SaleOptionName.RENT)
            ),
            EstateType.APARTMENT);
    List<Estate> estates = List.of(apartment1, apartment2, apartment3);
    @Test
    void testHappyLocationMatcher() {
        Customer customer = new Customer(
                "john@gmail.com",
                List.of(new Location("b","b","b",0)),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                false);
        List<Predicate<Estate>> filterList = List.of(new LocationMatcher(customer));
        EstateMatchCalculator estateMatchCalculator = new EstateMatchCalculator(
                customer,
                estates,
                filterList,
                0
        );
        assertEquals(2, estateMatchCalculator.getEstateMatches().get(0).id());
    }

    @Test
    void testSadLocationMatcher() {
        Customer customer = new Customer(
                "john@gmail.com",
                List.of(new Location("b","b","a",1)),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                false);
        List<Predicate<Estate>> filterList = List.of(new LocationMatcher(customer));
        EstateMatchCalculator estateMatchCalculator = new EstateMatchCalculator(
                customer,
                estates,
                filterList,
                0
        );
        assertTrue(estateMatchCalculator.getEstateMatches().isEmpty());
    }

    @Test
    void testHappyEstateTypeMatcher() {
        Customer customer = new Customer(
                "john@gmail.com",
                new ArrayList<>(),
                List.of(EstateType.APARTMENT),
                new ArrayList<>(),
                new ArrayList<>(),
                false);
        List<Predicate<Estate>> filterList = List.of(new EstateTypeMatcher(customer));
        EstateMatchCalculator estateMatchCalculator = new EstateMatchCalculator(
                customer,
                estates,
                filterList,
                0
        );
        assertEquals(List.of(1,2,3), estateMatchCalculator.getEstateMatches().stream().map(Estate::id).toList());
    }

    @Test
    void testSadEstateTypeMatcher() {
        Customer customer = new Customer(
                "john@gmail.com",
                new ArrayList<>(),
                List.of(EstateType.HOUSE),
                new ArrayList<>(),
                new ArrayList<>(),
                false);
        List<Predicate<Estate>> filterList = List.of(new EstateTypeMatcher(customer));
        EstateMatchCalculator estateMatchCalculator = new EstateMatchCalculator(
                customer,
                estates,
                filterList,
                0
        );
        assertTrue(estateMatchCalculator.getEstateMatches().isEmpty());
    }

    @Test
    void testSaleLimitMatcherWithNoMatches() {
        Customer customer = new Customer(
                "john@gmail.com",
                new ArrayList<>(),
                new ArrayList<>(),
                List.of(
                        new Sale(999, Currency.EUR, SaleOptionName.SALE)
                ),
                new ArrayList<>(),
                false
        );
        List<Predicate<Estate>> filterList = List.of(new SaleLimitMatcher(customer));
        EstateMatchCalculator estateMatchCalculator = new EstateMatchCalculator(
                customer,
                estates,
                filterList,
                0
        );
        assertTrue(estateMatchCalculator.getEstateMatches().isEmpty());
    }

    @Test
    void testSaleLimitMatcherWithOneMatch() {
        Customer customer = new Customer(
                "john@gmail.com",
                new ArrayList<>(),
                new ArrayList<>(),
                List.of(
                        new Sale(1999, Currency.EUR, SaleOptionName.SALE)
                ),
                new ArrayList<>(),
                false
        );
        List<Predicate<Estate>> filterList = List.of(new SaleLimitMatcher(customer));
        EstateMatchCalculator estateMatchCalculator = new EstateMatchCalculator(
                customer,
                estates,
                filterList,
                0
        );
        assertEquals(List.of(1), estateMatchCalculator.getEstateMatches().stream().map(Estate::id).toList());
        assertEquals(SaleOptionName.SALE, estateMatchCalculator.getEstateMatches()
                .stream().map(estate -> estate.saleOptions().get(0)).map(Sale::getOptionName).toList().get(0));
    }

    @Test
    void testSaleLimitMatcherWithThreeMatches() {
        Customer customer = new Customer(
                "john@gmail.com",
                new ArrayList<>(),
                new ArrayList<>(),
                List.of(
                        new Rent(300, Currency.EUR, Period.MONTH, SaleOptionName.RENT)
                ),
                new ArrayList<>(),
                true
        );
        List<Predicate<Estate>> filterList = List.of(new SaleLimitMatcher(customer));
        EstateMatchCalculator estateMatchCalculator = new EstateMatchCalculator(
                customer,
                estates,
                filterList,
                1.1
        );
        List<SaleOptionName> saleOptionNameList = estateMatchCalculator.getEstateMatches()
                .stream().map(estate -> estate.saleOptions().get(0)).map(Sale::getOptionName).toList();
        List<Integer> salePriceList = estateMatchCalculator.getEstateMatches()
                .stream().map(estate -> estate.saleOptions().get(0)).map(Sale::getPrice).toList();
        assertEquals(List.of(1,2,3), estateMatchCalculator.getEstateMatches().stream().map(Estate::id).toList());
        assertEquals(List.of(SaleOptionName.RENT, SaleOptionName.RENT, SaleOptionName.RENT), saleOptionNameList);
        assertEquals(List.of(0,0,0), salePriceList);
    }

    @Test
    void testGetPreferredEstatesWithProperPrice() {
        Customer customer = new Customer(
                "john@gmail.com",
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                List.of(2,3),
                true
        );
        EstateMatchCalculator estateMatchCalculator = new EstateMatchCalculator(
                customer,
                estates,
                new ArrayList<>(),
                1.1
        );
        assertEquals(List.of(0, 0), estateMatchCalculator.getPreferredEstates().stream()
                .map(estate -> estate.saleOptions().get(0)).map(Sale::getPrice).toList());
    }
}