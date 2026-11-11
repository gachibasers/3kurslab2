package com.lab2.gems.app;

import com.lab2.gems.model.GemCollection;
import com.lab2.gems.model.Gemstone;
import com.lab2.gems.parser.DomGemParser;
import com.lab2.gems.parser.GemParser;
import com.lab2.gems.parser.SaxGemParser;
import com.lab2.gems.parser.StaxGemParser;
import com.lab2.gems.service.GemService;
import com.lab2.gems.service.GemstoneComparator;
import com.lab2.gems.service.XmlValidator;
import com.lab2.gems.service.XsltTransformer;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Console application for the Diamond Fund gemstones.
 * Parses gems.xml with SAX, DOM and StAX, validates against XSD,
 * sorts with Comparator and transforms via XSLT.
 */
public class GemApp {

    private static final Logger LOGGER = Logger.getLogger(GemApp.class.getName());

    private static final File XML_FILE = new File("src/main/resources/gems.xml");
    private static final File XSD_FILE = new File("src/main/resources/gem.xsd");
    private static final File XSL_FILE = new File("src/main/resources/group-by-preciousness.xsl");
    private static final File OUTPUT_FILE = new File("out/gems-grouped.xml");

    private GemApp() {
    }

    public static void main(String[] args) {
        setupLogging();

        LOGGER.info("=== Diamond Fund gemstone application started ===");

        // 1. Validate XML against XSD
        LOGGER.info("Validating " + XML_FILE.getPath() + " against " + XSD_FILE.getPath());
        boolean valid;
        try {
            XmlValidator validator = new XmlValidator(XSD_FILE);
            valid = validator.validate(XML_FILE);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to initialize XSD validator", e);
            return;
        }
        LOGGER.info("XML validation result: " + (valid ? "VALID" : "INVALID"));
        if (!valid) {
            LOGGER.warning("XML is invalid; aborting further processing.");
            return;
        }

        // 2. Parse with all three parsers
        List<Gemstone> sax = parse(new SaxGemParser(), "SAX");
        List<Gemstone> dom = parse(new DomGemParser(), "DOM");
        List<Gemstone> stax = parse(new StaxGemParser(), "StAX");

        LOGGER.info("Parsed gemstones: SAX=" + sax.size() + ", DOM=" + dom.size() + ", StAX=" + stax.size());

        // 3. Use the first parser result for further processing
        GemService service = new GemService(sax);

        // 4. Sort by value ascending (Comparator)
        GemCollection byValue = service.sortByValueAscending();
        LOGGER.info("Sorted by value (ascending):");
        byValue.getGemstones().forEach(g -> LOGGER.info("  " + g));

        // 5. Sort by name
        GemCollection byName = service.sortByName();
        LOGGER.info("Sorted by name:");
        byName.getGemstones().forEach(g -> LOGGER.info("  " + g.getName()));

        // 6. XSLT transformation: group by preciousness
        LOGGER.info("Transforming XML via XSLT (group by preciousness) -> " + OUTPUT_FILE.getPath());
        try {
            new XsltTransformer().transform(XML_FILE, XSL_FILE, OUTPUT_FILE);
            LOGGER.info("Transformation complete: " + OUTPUT_FILE.getPath());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "XSLT transformation failed", e);
        }

        LOGGER.info("=== Diamond Fund gemstone application finished ===");
    }

    private static List<Gemstone> parse(GemParser parser, String label) {
        try {
            long start = System.nanoTime();
            List<Gemstone> result = parser.parse(XML_FILE);
            long elapsed = System.nanoTime() - start;
            LOGGER.info(String.format("%s parsing: %d gemstones in %.2f ms",
                    label, result.size(), elapsed / 1_000_000.0));
            return result;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, label + " parsing failed", e);
            return java.util.Collections.emptyList();
        }
    }

    private static void setupLogging() {
        // Console handler with simple formatting
        java.util.logging.ConsoleHandler handler = new java.util.logging.ConsoleHandler();
        handler.setLevel(Level.INFO);
        handler.setFormatter(new java.util.logging.SimpleFormatter());
        LOGGER.setLevel(Level.INFO);
        LOGGER.addHandler(handler);
        LOGGER.setUseParentHandlers(false);
    }
}
