package com.suboch.task5.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.nio.file.Path;

/**
 *
 */
public class SAXValidator {
    private static Logger logger = LogManager.getLogger();
    private Path filePath;
    private Path schemaPath;
    private DefaultHandler errorHandler;

    public SAXValidator(Path filePath, Path schemaPath, DefaultHandler errorHandler) {
        this.filePath = filePath;
        this.schemaPath = schemaPath;
        this.errorHandler = errorHandler;
    }

    public boolean validate() {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        Schema schema;
        try {
            schema = factory.newSchema(schemaPath.toFile());
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setSchema(schema);
            SAXParser parser = spf.newSAXParser();
            parser.parse(filePath.toFile(), new FlowerErrorHandler());
            return true;
        } catch (ParserConfigurationException e) {
            logger.error("", e);
        } catch (IOException e) {
            logger.fatal(e);
        } catch (SAXException e) {
            logger.error("",e);
        }
        return false;
    }
}
