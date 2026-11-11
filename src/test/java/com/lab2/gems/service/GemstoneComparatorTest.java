package com.lab2.gems.service;

import com.lab2.gems.model.Color;
import com.lab2.gems.model.Gemstone;
import com.lab2.gems.model.Preciousness;
import com.lab2.gems.model.VisualParameters;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GemstoneComparatorTest {

    private List<Gemstone> gems;

    @Before
    public void setUp() {
        gems = java.util.Arrays.asList(
                new Gemstone("G003", Preciousness.SEMI_PRECIOUS, "Amethyst", "Brazil",
                        new VisualParameters(Color.PINK, 70.0, 14), 12.5),
                new Gemstone("G001", Preciousness.PRECIOUS, "Diamond", "South Africa",
                        new VisualParameters(Color.WHITE, 99.5, 12), 5.2),
                new Gemstone("G002", Preciousness.PRECIOUS, "Ruby", "Myanmar",
                        new VisualParameters(Color.RED, 85.0, 9), 3.8));
    }

    @Test
    public void testSortByValueAscending() {
        java.util.Collections.sort(gems, new GemstoneComparator(
                GemstoneComparator.SortBy.VALUE, true));
        assertEquals("Ruby", gems.get(0).getName());
        assertEquals("Amethyst", gems.get(2).getName());
    }

    @Test
    public void testSortByValueDescending() {
        java.util.Collections.sort(gems, new GemstoneComparator(
                GemstoneComparator.SortBy.VALUE, false));
        assertEquals("Amethyst", gems.get(0).getName());
        assertEquals("Ruby", gems.get(2).getName());
    }

    @Test
    public void testSortByName() {
        java.util.Collections.sort(gems, new GemstoneComparator(
                GemstoneComparator.SortBy.NAME, true));
        assertEquals("Amethyst", gems.get(0).getName());
        assertEquals("Diamond", gems.get(1).getName());
        assertEquals("Ruby", gems.get(2).getName());
    }

    @Test
    public void testSortByPreciousness() {
        java.util.Collections.sort(gems, new GemstoneComparator(
                GemstoneComparator.SortBy.PRECIOUSNESS, true));
        assertEquals(Preciousness.PRECIOUS, gems.get(0).getPreciousness());
    }

    @Test
    public void testSortByOrigin() {
        java.util.Collections.sort(gems, new GemstoneComparator(
                GemstoneComparator.SortBy.ORIGIN, true));
        assertEquals("Brazil", gems.get(0).getOrigin());
    }
}
