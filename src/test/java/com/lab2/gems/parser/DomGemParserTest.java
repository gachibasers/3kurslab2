package com.lab2.gems.parser;

import com.lab2.gems.model.Color;
import com.lab2.gems.model.Gemstone;
import com.lab2.gems.model.Preciousness;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class DomGemParserTest {

    private static final File XML = new File("src/main/resources/gems.xml");

    @Test
    public void testParse() throws Exception {
        List<Gemstone> gems = new DomGemParser().parse(XML);
        assertEquals(6, gems.size());

        Gemstone ruby = findByName(gems, "Ruby");
        assertNotNull(ruby);
        assertEquals("G002", ruby.getId());
        assertEquals(Preciousness.PRECIOUS, ruby.getPreciousness());
        assertEquals("Myanmar", ruby.getOrigin());
        assertEquals(3.8, ruby.getValue(), 0.001);
        assertEquals(Color.RED, ruby.getVisualParameters().getColor());
        assertEquals(9, ruby.getVisualParameters().getFacets());
    }

    @Test(expected = Exception.class)
    public void testParseMissingFileThrows() throws Exception {
        new DomGemParser().parse(new File("src/main/resources/missing.xml"));
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
