package com.lab2.gems.service;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * Transforms an XML document using an XSLT stylesheet.
 */
public class XsltTransformer {

    /**
     * Applies the XSLT stylesheet to the source XML and writes the result.
     *
     * @param xmlFile   source XML file
     * @param xslFile   XSLT stylesheet
     * @param outputFile destination file
     * @throws Exception if transformation fails
     */
    public void transform(File xmlFile, File xslFile, File outputFile) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(xslFile));
        transformer.transform(new StreamSource(xmlFile), new StreamResult(outputFile));
    }
}
