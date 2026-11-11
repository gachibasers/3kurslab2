package com.lab2.gems.parser;

import com.lab2.gems.model.Color;
import com.lab2.gems.model.Gemstone;
import com.lab2.gems.model.Preciousness;
import com.lab2.gems.model.VisualParameters;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * StAX-based gemstone parser (pull-based, cursor API).
 */
public class StaxGemParser implements GemParser {

    @Override
    public List<Gemstone> parse(File xmlFile) throws Exception {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        List<Gemstone> gemstones = new ArrayList<>();
        XMLStreamReader reader = null;
        try {
            reader = factory.createXMLStreamReader(new FileInputStream(xmlFile));
            Gemstone current = null;
            VisualParameters currentVp = null;
            String localName = null;

            while (reader.hasNext()) {
                int event = reader.next();
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        localName = reader.getLocalName();
                        if ("gemstone".equals(localName)) {
                            current = new Gemstone();
                            current.setId(reader.getAttributeValue(null, "id"));
                            current.setPreciousness(
                                    Preciousness.valueOf(reader.getAttributeValue(null, "preciousness")));
                            currentVp = new VisualParameters();
                        } else if ("visualParameters".equals(localName)) {
                            currentVp = new VisualParameters();
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        String text = reader.getText().trim();
                        if (text.isEmpty() || current == null) {
                            break;
                        }
                        switch (localName) {
                            case "name":
                                current.setName(text);
                                break;
                            case "origin":
                                current.setOrigin(text);
                                break;
                            case "color":
                                currentVp.setColor(Color.valueOf(text));
                                break;
                            case "transparency":
                                currentVp.setTransparency(Double.parseDouble(text));
                                break;
                            case "facets":
                                currentVp.setFacets(Integer.parseInt(text));
                                break;
                            case "value":
                                current.setValue(Double.parseDouble(text));
                                break;
                            default:
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        localName = reader.getLocalName();
                        if ("visualParameters".equals(localName)) {
                            current.setVisualParameters(currentVp);
                        } else if ("gemstone".equals(localName)) {
                            gemstones.add(current);
                            current = null;
                            currentVp = null;
                        }
                        break;
                    default:
                        break;
                }
            }
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception ignored) {
                }
            }
        }
        return gemstones;
    }
}
