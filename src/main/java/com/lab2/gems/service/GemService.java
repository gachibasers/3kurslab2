package com.lab2.gems.service;

import com.lab2.gems.model.GemCollection;
import com.lab2.gems.model.Gemstone;

import java.util.Collections;
import java.util.List;

/**
 * Service layer for gemstone collection operations: parsing unification
 * and sorting.
 */
public class GemService {

    private final List<Gemstone> gemstones;

    public GemService(List<Gemstone> gemstones) {
        this.gemstones = java.util.Collections.unmodifiableList(
                new java.util.ArrayList<>(gemstones));
    }

    public List<Gemstone> getGemstones() {
        return gemstones;
    }

    public int size() {
        return gemstones.size();
    }

    public GemCollection sortBy(GemstoneComparator.SortBy sortBy, boolean ascending) {
        List<Gemstone> sorted = new java.util.ArrayList<>(gemstones);
        Collections.sort(sorted, new GemstoneComparator(sortBy, ascending));
        return new GemCollection(sorted);
    }

    public GemCollection sortByValueAscending() {
        return sortBy(GemstoneComparator.SortBy.VALUE, true);
    }

    public GemCollection sortByName() {
        return sortBy(GemstoneComparator.SortBy.NAME, true);
    }
}
