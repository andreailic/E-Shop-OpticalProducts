package com.example.demo.utils;

import org.springframework.data.domain.Sort;

import java.util.Arrays;

public class SortingPaginationUtils {

    public static Sort.Order[] parseSortParameters(String[] sort) {
        return Arrays.stream(sort)
                .map(SortingPaginationUtils::parseSortParameter)
                .toArray(Sort.Order[]::new);
    }

    private static Sort.Order parseSortParameter(String sortParameter) {
        try {
            String[] parts = sortParameter.split(",");
            String field = parts[0];
            String direction = parts.length > 1 ? parts[1] : "asc";
            return Sort.Order.by(field).with(Sort.Direction.fromString(direction));
        } catch (Exception ex) {
            return null;
        }
    }
}
