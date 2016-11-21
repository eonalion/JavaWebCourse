package com.suboch.task5.builder;

import com.suboch.task5.flower.Flower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

/**
 *
 */
public class FlowersSAXBuilder {
    private static Logger logger = LogManager.getLogger();
    private List<Flower> flowers;
    private FlowerHandler flowerHandler;
    private XMLReader reader;

    public FlowersSAXBuilder(){
        flowerHandler = new FlowerHandler();
        try{
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(flowerHandler);
        } catch (SAXException e){
            logger.error("Error while creating XMLReader", e);
        }
    }

    public List<Flower> getFlowers(){
        return flowers;
    }

    public void buildFlowersList(String fileName){
        try{
            reader.parse(fileName);
        } catch (SAXException e){
            logger.error("Parsing error", e);
        } catch (IOException e){
            logger.fatal(e);
        }
        flowers = flowerHandler.getFlowers();
    }
}
