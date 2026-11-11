package com.lab2.gems.parser;

import com.lab2.gems.model.Gemstone;

import java.io.File;
import java.util.List;

/**
 * Common contract for XML gemstone parsers (SAX, DOM, StAX).
 */
public interface GemParser {

    /**
     * Parses an XML file into a list of gemstones.
     *
     * @param xmlFile the XML file to parse
     * @return list of gemstones
     * @throws Exception if parsing fails
     */
    List<Gemstone> parse(File xmlFile) throws Exception;
}
