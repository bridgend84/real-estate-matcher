package com.codecool.realestatematcher.data;

import java.util.List;

public record Estate(int id, Location location, List<Sale> saleOptions, EstateType type) {
}
