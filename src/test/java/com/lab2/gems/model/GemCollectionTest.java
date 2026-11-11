package com.lab2.gems.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GemCollectionTest {

    @Test
    public void testAddAndSize() {
        GemCollection collection = new GemCollection();
        collection.addGemstone(new Gemstone("G001", Preciousness.PRECIOUS, "Diamond", "South Africa",
                new VisualParameters(Color.WHITE, 99.5, 12), 5.2));
        assertEquals(1, collection.size());
        assertEquals(1, collection.getGemstones().size());
    }

    @Test
    public void testConstructorWithList() {
        List<Gemstone> list = Arrays.asList(
                new Gemstone("G001", Preciousness.PRECIOUS, "Diamond", "South Africa",
                        new VisualParameters(Color.WHITE, 99.5, 12), 5.2),
                new Gemstone("G002", Preciousness.PRECIOUS, "Ruby", "Myanmar",
                        new VisualParameters(Color.RED, 85.0, 9), 3.8));
        GemCollection collection = new GemCollection(list);
        assertEquals(2, collection.size());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullListThrows() {
        new GemCollection(null);
    }

    @Test
    public void testSetGemstonesCopiesList() {
        GemCollection collection = new GemCollection();
        java.util.ArrayList<Gemstone> external = new java.util.ArrayList<>();
        external.add(new Gemstone("G001", Preciousness.PRECIOUS, "Diamond", "South Africa",
                new VisualParameters(Color.WHITE, 99.5, 12), 5.2));
        collection.setGemstones(external);
        external.clear();
        assertEquals(1, collection.size());
    }
}
