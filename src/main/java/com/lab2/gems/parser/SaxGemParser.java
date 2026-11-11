package com.lab2.gems.parser;

import com.lab2.gems.model.Color;
import com.lab2.gems.model.Gemstone;
import com.lab2.gems.model.Preciousness;
import com.lab2.gems.model.VisualParameters;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * SAX-based gemstone parser (event-driven, low memory footprint).
 */
public class SaxGemParser implements GemParser {

    @Override
    public List<Gemstone> parse(File xmlFile) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        GemHandler handler = new GemHandler();
        saxParser.parse(xmlFile, handler);
        return handler.getGemstones();
    }

    private static class GemHandler extends DefaultHandler {

        private final List<Gemstone> gemstones = new ArrayList<>();
        private Gemstone current;
        private VisualParameters currentVp;
        private StringBuilder content = new StringBuilder();

        private boolean inName;
        private boolean inOrigin;
        private boolean inColor;
        private boolean inTransparency;
        private boolean inFacets;
        private boolean inValue;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes)
                throws SAXException {
            content.setLength(0);
            if ("gemstone".equals(qName)) {
                String id = attributes.getValue("id");
                String preciousness = attributes.getValue("preciousness");
                current = new Gemstone();
                current.setId(id);
                current.setPreciousness(Preciousness.valueOf(preciousness));
                currentVp = new VisualParameters();
            } else {
                switch (qName) {
                    case "name":
                        inName = true;
                        break;
                    case "origin":
                        inOrigin = true;
                        break;
                    case "color":
                        inColor = true;
                        break;
                    case "transparency":
                        inTransparency = true;
                        break;
                    case "facets":
                        inFacets = true;
                        break;
                    case "value":
                        inValue = true;
                        break;
                    default:
                        break;
                }
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            content.append(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            String text = content.toString().trim();
            if ("name".equals(qName)) {
                inName = false;
                current.setName(text);
            } else if ("origin".equals(qName)) {
                inOrigin = false;
                current.setOrigin(text);
            } else if ("color".equals(qName)) {
                inColor = false;
                currentVp.setColor(Color.valueOf(text));
            } else if ("transparency".equals(qName)) {
                inTransparency = false;
                currentVp.setTransparency(Double.parseDouble(text));
            } else if ("facets".equals(qName)) {
                inFacets = false;
                currentVp.setFacets(Integer.parseInt(text));
            } else if ("value".equals(qName)) {
                inValue = false;
                current.setValue(Double.parseDouble(text));
            } else if ("visualParameters".equals(qName)) {
                current.setVisualParameters(currentVp);
            } else if ("gemstone".equals(qName)) {
                gemstones.add(current);
                current = null;
                currentVp = null;
            }
            content.setLength(0);
        }

        public List<Gemstone> getGemstones() {
            return gemstones;
        }
    }
}
