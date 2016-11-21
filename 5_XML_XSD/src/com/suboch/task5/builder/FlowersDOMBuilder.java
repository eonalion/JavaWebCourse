package com.suboch.task5.builder;

import com.suboch.task5.flower.*;
import com.suboch.task5.exception.InvalidValueException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FlowersDOMBuilder {
    private static Logger logger = LogManager.getLogger();
    private List<Flower> flowers;
    private DocumentBuilder documentBuilder;

    private static final int INDEX_NULL = 0;
    private static final String DASH = "-";
    private static final String UNDERLINE = "_";

    public FlowersDOMBuilder() {
        flowers = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.fatal(e);
        }
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public void buildFlowersList(String fileName) {
        Document document;
        try {
            document = documentBuilder.parse(fileName);
            Element rootElement = document.getDocumentElement();

            NodeList indoorFlowerList = rootElement.getElementsByTagName(FlowersCharacteristic.INDOOR_FLOWER.getValue());
            NodeList outdoorFlowerList = rootElement.getElementsByTagName(FlowersCharacteristic.OUTDOOR_FLOWER.getValue());
            Element indoorFlowerElement, outdoorFlowerElement;
            for (int i = INDEX_NULL; i < indoorFlowerList.getLength(); i++) {
                indoorFlowerElement = (Element) indoorFlowerList.item(i);
                flowers.add(buildIndoorFlower(indoorFlowerElement));
            }

            for (int i = INDEX_NULL; i < outdoorFlowerList.getLength(); i++) {
                outdoorFlowerElement = (Element) outdoorFlowerList.item(i);
                flowers.add(buildOutdoorFlower(outdoorFlowerElement));
            }
        } catch (IOException e) {
            logger.fatal("",e);
        } catch (SAXException e) {
            logger.error("Error in document parsing", e);
        } catch (InvalidValueException e) {
            logger.warn("",e);
        }
    }

    public IndoorFlower buildIndoorFlower(Element flowerElement) throws InvalidValueException {
        IndoorFlower flower = (IndoorFlower) buildFlower(new IndoorFlower(), flowerElement);
        flower.setFlowering(Boolean.parseBoolean(getElementValue(flowerElement, FlowersCharacteristic.FLOWERING.getValue())));
        return flower;
    }

    public OutdoorFlower buildOutdoorFlower(Element flowerElement) throws InvalidValueException {
        OutdoorFlower flower = (OutdoorFlower) buildFlower(new OutdoorFlower(), flowerElement);
        flower.setLifetime(FlowerLifetime.valueOf(getElementValue(flowerElement, FlowersCharacteristic.LIFETIME.getValue()).toUpperCase()));
        return flower;
    }

    public Flower buildFlower(Flower flower, Element flowerElement) throws InvalidValueException {
        Element visualParameters = (Element) flowerElement.getElementsByTagName(FlowersCharacteristic.VISUAL_PARAMETERS.getValue()).item(INDEX_NULL);
        Element growingTips = (Element) flowerElement.getElementsByTagName(FlowersCharacteristic.GROWING_TIPS.getValue()).item(INDEX_NULL);

        flower.setName(flowerElement.getAttribute(FlowersCharacteristic.NAME.getValue()));
        flower.setSoilType(SoilType.valueOf(getElementValue(flowerElement, FlowersCharacteristic.SOIL.getValue()).toUpperCase()));
        flower.setOrigin(getElementValue(flowerElement, FlowersCharacteristic.ORIGIN.getValue()));
        flower.getVisualParameters().setStemColor(getElementValue(visualParameters, FlowersCharacteristic.STEM_COLOR.getValue()));
        flower.getVisualParameters().setLeafColor(getElementValue(visualParameters, FlowersCharacteristic.LEAF_COLOR.getValue()));
        flower.getVisualParameters().setSize(Integer.parseInt(getElementValue(visualParameters, FlowersCharacteristic.SIZE.getValue())));
        flower.getGrowingTips().setHeliphilous(Boolean.parseBoolean(growingTips.getAttribute(FlowersCharacteristic.HELIPHILOUS.getValue())));
        flower.getGrowingTips().setTemperature(Integer.parseInt(getElementValue(growingTips, FlowersCharacteristic.TEMPERATURE.getValue())));
        flower.getGrowingTips().setWater(Integer.parseInt(getElementValue(growingTips, FlowersCharacteristic.WATER.getValue())));
        flower.setMultiplying(MultiplyingType.valueOf(getElementValue(flowerElement, FlowersCharacteristic.MULTIPLYING.getValue()).toUpperCase()));

        return flower;
    }

    private static String getElementValue(Element parentElement, String tagName) {

        return parentElement.getElementsByTagName(tagName).item(INDEX_NULL).getTextContent().replace(DASH, UNDERLINE);
    }
}
