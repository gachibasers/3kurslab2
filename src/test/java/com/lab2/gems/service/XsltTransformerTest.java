package com.lab2.gems.service;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class XsltTransformerTest {

    @Test
    public void testTransformProducesOutput() throws Exception {
        File xml = new File("src/main/resources/gems.xml");
        File xsl = new File("src/main/resources/group-by-preciousness.xsl");
        File output = new File("out/gems-grouped-test.xml");
        output.delete();

        new XsltTransformer().transform(xml, xsl, output);

        assertTrue(output.exists());
        assertTrue(output.length() > 0);
        output.delete();
    }
}
