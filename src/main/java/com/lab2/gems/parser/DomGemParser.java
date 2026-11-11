package com.lab2.gems.parser;

import com.lab2.gems.model.Color;
import com.lab2.gems.model.Gemstone;
import com.lab2.gems.model.Preciousness;
import com.lab2.gems.model.VisualParameters;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * DOM-based gemstone parser (in-memory tree).
 */
public class DomGemParser implements GemParser {

    @Override
    public List<Gemstone> parse(File xmlFile) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        document.getDocumentElement().normalize();

        List<Gemstone> gemstones = new ArrayList<>();
        NodeList list = document.getElementsByTagName("gemstone");
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                gemstones.add(parseGemstone((Element) node));
            }
        }
        return gemstones;
    }

    private Gemstone parseGemstone(Element element) {
        Gemstone gemstone = new Gemstone();
        gemstone.setId(element.getAttribute("id"));
        gemstone.setPreciousness(Preciousness.valueOf(element.getAttribute("preciousness")));

        Element vp = null;
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element el = (Element) child;
            switch (el.getTagName()) {
                case "name":
                    gemstone.setName(textContent(el));
                    break;
                case "origin":
                    gemstone.setOrigin(textContent(el));
                    break;
                case "visualParameters":
                    vp = el;
                    break;
                case "value":
                    gemstone.setValue(Double.parseDouble(textContent(el)));
                    break;
                default:
                    break;
            }
        }
        if (vp != null) {
            gemstone.setVisualParameters(parseVisualParameters(vp));
        }
        return gemstone;
    }

    private VisualParameters parseVisualParameters(Element vp) {
        VisualParameters params = new VisualParameters();
        NodeList children = vp.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element el = (Element) child;
            switch (el.getTagName()) {
                case "color":
                    params.setColor(Color.valueOf(textContent(el)));
                    break;
                case "transparency":
                    params.setTransparency(Double.parseDouble(textContent(el)));
                    break;
                case "facets":
                    params.setFacets(Integer.parseInt(textContent(el)));
                    break;
                default:
                    break;
            }
        }
        return params;
    }

    private String textContent(Element element) {
        NodeList children = element.getChildNodes();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node.getNodeType() == Node.TEXT_NODE) {
                sb.append(node.getTextContent());
            }
        }
        return sb.toString().trim();
    }
}
