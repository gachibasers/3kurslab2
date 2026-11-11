package com.lab2.gems.parser;

import com.lab2.gems.model.Color;
import com.lab2.gems.model.Gemstone;
import com.lab2.gems.model.Preciousness;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class StaxGemParserTest {

    private static final File XML = new File("src/main/resources/gems.xml");

    @Test
    public void testParse() throws Exception {
        List<Gemstone> gems = new StaxGemParser().parse(XML);
        assertEquals(6, gems.size());

        Gemstone citrine = findByName(gems, "Citrine");
        assertNotNull(citrine);
        assertEquals("G006", citrine.getId());
        assertEquals(Preciousness.SEMI_PRECIOUS, citrine.getPreciousness());
        assertEquals("Brazil", citrine.getOrigin());
        assertEquals(15.3, citrine.getValue(), 0.001);
        assertEquals(Color.YELLOW, citrine.getVisualParameters().getColor());
        assertEquals(95.0, citrine.getVisualParameters().getTransparency(), 0.001);
        assertEquals(12, citrine.getVisualParameters().getFacets());
    }

    @Test(expected = Exception.class)
    public void testParseMissingFileThrows() throws Exception {
        new StaxGemParser().parse(new File("src/main/resources/missing.xml"));
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
