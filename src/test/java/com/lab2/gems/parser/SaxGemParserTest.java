package com.lab2.gems.parser;

import com.lab2.gems.model.Color;
import com.lab2.gems.model.Gemstone;
import com.lab2.gems.model.Preciousness;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class SaxGemParserTest {

    private static final File XML = new File("src/main/resources/gems.xml");

    @Test
    public void testParse() throws Exception {
        List<Gemstone> gems = new SaxGemParser().parse(XML);
        assertEquals(6, gems.size());

        Gemstone diamond = findByName(gems, "Diamond");
        assertNotNull(diamond);
        assertEquals("G001", diamond.getId());
        assertEquals(Preciousness.PRECIOUS, diamond.getPreciousness());
        assertEquals("South Africa", diamond.getOrigin());
        assertEquals(5.2, diamond.getValue(), 0.001);
        assertEquals(Color.WHITE, diamond.getVisualParameters().getColor());
        assertEquals(99.5, diamond.getVisualParameters().getTransparency(), 0.001);
        assertEquals(12, diamond.getVisualParameters().getFacets());
    }

    @Test(expected = Exception.class)
    public void testParseMissingFileThrows() throws Exception {
        new SaxGemParser().parse(new File("src/main/resources/missing.xml"));
    }

    private Gemstone findByName(List<Gemstone> gems, String name) {
        for (Gemstone g : gems) {
            if (name.equals(g.getName())) {
                return g;
            }
        }
        return null;
    }
}
