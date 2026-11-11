package com.lab2.gems.service;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class XmlValidatorTest {

    private static final File XSD = new File("src/main/resources/gem.xsd");
    private static final File VALID_XML = new File("src/main/resources/gems.xml");
    private static final File INVALID_XML = new File("src/main/resources/invalid-gems.xml");

    @Test
    public void testValidateValidXml() throws Exception {
        XmlValidator validator = new XmlValidator(XSD);
        assertTrue(validator.validate(VALID_XML));
    }

    @Test
    public void testValidateInvalidXml() throws Exception {
        XmlValidator validator = new XmlValidator(XSD);
        // Facets value 99 violates the 4-15 restriction in the XSD
        String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<Gem xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
                + "     xsi:noNamespaceSchemaLocation=\"gem.xsd\">\n"
                + "  <gemstone id=\"G001\" preciousness=\"PRECIOUS\">\n"
                + "    <name>Diamond</name>\n"
                + "    <origin>South Africa</origin>\n"
                + "    <visualParameters>\n"
                + "      <color>WHITE</color>\n"
                + "      <transparency>99.5</transparency>\n"
                + "      <facets>99</facets>\n"
                + "    </visualParameters>\n"
                + "    <value>5.2</value>\n"
                + "  </gemstone>\n"
                + "</Gem>\n";
        java.nio.file.Files.write(INVALID_XML.toPath(), content.getBytes("UTF-8"));
        try {
            assertFalse(validator.validate(INVALID_XML));
        } finally {
            INVALID_XML.delete();
        }
    }
}
