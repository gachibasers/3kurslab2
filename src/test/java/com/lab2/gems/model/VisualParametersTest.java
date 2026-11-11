package com.lab2.gems.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class VisualParametersTest {

    @Test
    public void testCreateAndGetters() {
        VisualParameters vp = new VisualParameters(Color.RED, 85.0, 9);
        assertEquals(Color.RED, vp.getColor());
        assertEquals(85.0, vp.getTransparency(), 0.001);
        assertEquals(9, vp.getFacets());
    }

    @Test
    public void testSetters() {
        VisualParameters vp = new VisualParameters();
        vp.setColor(Color.BLUE);
        vp.setTransparency(50.0);
        vp.setFacets(4);
        assertEquals(Color.BLUE, vp.getColor());
        assertEquals(50.0, vp.getTransparency(), 0.001);
        assertEquals(4, vp.getFacets());
    }

    @Test
    public void testEqualsAndHashCode() {
        VisualParameters a = new VisualParameters(Color.GREEN, 70.0, 14);
        VisualParameters b = new VisualParameters(Color.GREEN, 70.0, 14);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void testToString() {
        VisualParameters vp = new VisualParameters(Color.WHITE, 99.5, 12);
        assertTrue(vp.toString().contains("WHITE"));
    }
}
