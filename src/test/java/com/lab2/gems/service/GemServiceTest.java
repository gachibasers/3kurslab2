package com.lab2.gems.service;

import com.lab2.gems.model.GemCollection;
import com.lab2.gems.model.Gemstone;
import com.lab2.gems.model.Color;
import com.lab2.gems.model.Preciousness;
import com.lab2.gems.model.VisualParameters;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GemServiceTest {

    private List<Gemstone> gems() {
        return Arrays.asList(
                new Gemstone("G001", Preciousness.PRECIOUS, "Diamond", "South Africa",
                        new VisualParameters(Color.WHITE, 99.5, 12), 5.2),
                new Gemstone("G002", Preciousness.PRECIOUS, "Ruby", "Myanmar",
                        new VisualParameters(Color.RED, 85.0, 9), 3.8),
                new Gemstone("G003", Preciousness.SEMI_PRECIOUS, "Amethyst", "Brazil",
                        new VisualParameters(Color.PINK, 70.0, 14), 12.5));
    }

    @Test
    public void testSize() {
        GemService service = new GemService(gems());
        assertEquals(3, service.size());
    }

    @Test
    public void testSortByValueAscending() {
        GemService service = new GemService(gems());
        GemCollection sorted = service.sortByValueAscending();
        List<Gemstone> list = sorted.getGemstones();
        assertEquals(3, list.size());
        assertEquals("Ruby", list.get(0).getName());
        assertEquals("Amethyst", list.get(2).getName());
    }

    @Test
    public void testSortByName() {
        GemService service = new GemService(gems());
        GemCollection sorted = service.sortByName();
        List<Gemstone> list = sorted.getGemstones();
        assertEquals("Amethyst", list.get(0).getName());
        assertEquals("Diamond", list.get(1).getName());
        assertEquals("Ruby", list.get(2).getName());
    }

    @Test
    public void testOriginalListNotModified() {
        GemService service = new GemService(gems());
        service.sortByValueAscending();
        assertEquals("Diamond", service.getGemstones().get(0).getName());
    }
}
