package com.lab2.gems.service;

import com.lab2.gems.model.Gemstone;

import java.util.Comparator;

/**
 * Comparators for gemstone sorting. Supports sorting by value (carats),
 * name, origin, and preciousness.
 */
public class GemstoneComparator implements Comparator<Gemstone> {

    public enum SortBy {
        VALUE, NAME, ORIGIN, PRECIOUSNESS
    }

    private final SortBy sortBy;
    private final boolean ascending;

    public GemstoneComparator() {
        this(SortBy.VALUE, true);
    }

    public GemstoneComparator(SortBy sortBy, boolean ascending) {
        this.sortBy = sortBy;
        this.ascending = ascending;
    }

    @Override
    public int compare(Gemstone g1, Gemstone g2) {
        int result;
        switch (sortBy) {
            case VALUE:
                result = Double.compare(g1.getValue(), g2.getValue());
                break;
            case NAME:
                result = g1.getName().compareToIgnoreCase(g2.getName());
                break;
            case ORIGIN:
                result = g1.getOrigin().compareToIgnoreCase(g2.getOrigin());
                break;
            case PRECIOUSNESS:
                result = g1.getPreciousness().compareTo(g2.getPreciousness());
                break;
            default:
                result = 0;
        }
        return ascending ? result : -result;
    }
}
