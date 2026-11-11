package com.lab2.gems.service;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

/**
 * Validates an XML document against an XSD schema.
 */
public class XmlValidator {

    private final Schema schema;

    public XmlValidator(File xsdFile) throws Exception {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        this.schema = factory.newSchema(xsdFile);
    }

    /**
     * Validates the XML file against the configured schema.
     *
     * @return true if the document is valid
     */
    public boolean validate(File xmlFile) {
        try {
            Validator validator = schema.newValidator();
            validator.validate(new javax.xml.transform.stream.StreamSource(xmlFile));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Schema getSchema() {
        return schema;
    }
}
