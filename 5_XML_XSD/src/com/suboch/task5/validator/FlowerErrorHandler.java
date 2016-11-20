package com.suboch.task5.validator;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;


/**
 *
 */
public class FlowerErrorHandler extends DefaultHandler {
    private static Logger logger = LogManager.getLogger();

    public FlowerErrorHandler() {
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        logger.warn("", e);
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        logger.error("", e);
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        logger.fatal("", e);
    }

}
