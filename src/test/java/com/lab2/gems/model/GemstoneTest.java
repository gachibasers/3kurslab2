package com.lab2.gems.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class GemstoneTest {

    @Test
    public void testCreateAndGetters() {
        VisualParameters vp = new VisualParameters(Color.BLUE, 90.0, 5);
        Gemstone g = new Gemstone("G005", Preciousness.PRECIOUS, "Sapphire", "Sri Lanka", vp, 4.7);
        assertEquals("G005", g.getId());
        assertEquals(Preciousness.PRECIOUS, g.getPreciousness());
        assertEquals("Sapphire", g.getName());
        assertEquals("Sri Lanka", g.getOrigin());
        assertEquals(vp, g.getVisualParameters());
        assertEquals(4.7, g.getValue(), 0.001);
    }

    @Test
    public void testSetters() {
        Gemstone g = new Gemstone();
        g.setId("G001");
        g.setPreciousness(Preciousness.SEMI_PRECIOUS);
        g.setName("Amethyst");
        g.setOrigin("Brazil");
        g.setVisualParameters(new VisualParameters(Color.PINK, 70.0, 14));
        g.setValue(12.5);
        assertEquals("G001", g.getId());
        assertEquals(Preciousness.SEMI_PRECIOUS, g.getPreciousness());
        assertEquals(12.5, g.getValue(), 0.001);
    }

    @Test
    public void testEqualsAndHashCode() {
        VisualParameters vp = new VisualParameters(Color.RED, 85.0, 9);
        Gemstone a = new Gemstone("G002", Preciousness.PRECIOUS, "Ruby", "Myanmar", vp, 3.8);
        Gemstone b = new Gemstone("G002", Preciousness.PRECIOUS, "Ruby", "Myanmar", vp, 3.8);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void testToStringContainsId() {
        Gemstone g = new Gemstone("G001", Preciousness.PRECIOUS, "Diamond", "South Africa",
                new VisualParameters(Color.WHITE, 99.5, 12), 5.2);
        assertTrue(g.toString().contains("G001"));
    }
}
