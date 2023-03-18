package com.codecool.realestatematcher.data;

import java.util.List;

public class Customer {
    private final String email;
    private final List<Location> preferredLocations;
    private final List<EstateType> preferredTypes;
    private final List<Sale> saleLimits;
    private final List<Integer> preferredEstateIds;
    private final boolean memberShip;

    public Customer(String email,
                    List<Location> preferredLocations,
                    List<EstateType> preferredTypes,
                    List<Sale> saleLimits,
                    List<Integer> preferredEstateIds,
                    boolean memberShip) {
        this.email = email;
        this.preferredLocations = preferredLocations;
        this.preferredTypes = preferredTypes;
        this.saleLimits = saleLimits;
        this.preferredEstateIds = preferredEstateIds;
        this.memberShip = memberShip;
    }

    public String getEmail() {
        return email;
    }

    public List<Location> getPreferredLocations() {
        return preferredLocations;
    }

    public List<EstateType> getPreferredTypes() {
        return preferredTypes;
    }

    public List<Sale> getSaleLimits() {
        return saleLimits;
    }

    public List<Integer> getPreferredEstateIds() {
        return preferredEstateIds;
    }

    public boolean hasMemberShip() {
        return memberShip;
    }
}
