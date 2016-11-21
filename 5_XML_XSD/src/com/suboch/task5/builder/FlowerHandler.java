package com.suboch.task5.builder;

import com.suboch.task5.flower.*;
import com.suboch.task5.exception.InvalidValueException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FlowerHandler extends DefaultHandler {
    private static Logger logger = LogManager.getLogger();
    private List<Flower> flowers;
    private Flower currentFlower;
    private FlowersCharacteristic currentElement = FlowersCharacteristic.EMPTY_TAG;

    private static final String DASH = "-";
    private static final String UNDERLINE = "_";

    public FlowerHandler() {
        flowers = new ArrayList<>();
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        FlowersCharacteristic characteristic = FlowersCharacteristic.valueOf(localName.toUpperCase().replace(DASH, UNDERLINE));

        switch (characteristic) {
            case INDOOR_FLOWER:
                currentFlower = new IndoorFlower();
                setNameAttribute(attributes);
                break;
            case OUTDOOR_FLOWER:
                currentFlower = new OutdoorFlower();
                setNameAttribute(attributes);
                break;
            case GROWING_TIPS:
                if (attributes.getLength()!=0) {
                    currentFlower.getGrowingTips().setHeliphilous(Boolean.parseBoolean(attributes.getValue(FlowersCharacteristic.HELIPHILOUS.getValue())));
                } else {
                    currentFlower.getGrowingTips().setHeliphilous(true);
                }
                currentElement = FlowersCharacteristic.EMPTY_TAG;
                break;
            case FLOWER:
            case FLOWERS:
            case VISUAL_PARAMETERS:
                currentElement = FlowersCharacteristic.EMPTY_TAG;
                break;
            default:
                currentElement = FlowersCharacteristic.valueOf(localName.toUpperCase().replace(DASH, UNDERLINE));
        }
    }

    private void setNameAttribute(Attributes attributes) {
        try {
            currentFlower.setName(attributes.getValue(FlowersCharacteristic.NAME.getValue()));
        } catch (InvalidValueException e) {
            logger.warn(e);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (FlowersCharacteristic.INDOOR_FLOWER.getValue().equals(localName) || FlowersCharacteristic.OUTDOOR_FLOWER.getValue().equals(localName)) {
            flowers.add(currentFlower);
        }
        currentElement = FlowersCharacteristic.EMPTY_TAG;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String text = new String(ch, start, length).trim().replace(DASH, UNDERLINE);
        if (currentElement != FlowersCharacteristic.EMPTY_TAG) {
            switch (currentElement) {
                case SOIL:
                    currentFlower.setSoilType(SoilType.valueOf(text.toUpperCase()));
                    break;
                case ORIGIN:
                    currentFlower.setOrigin(text);
                    break;
                case STEM_COLOR:
                    currentFlower.getVisualParameters().setStemColor(text);
                    break;
                case LEAF_COLOR:
                    currentFlower.getVisualParameters().setLeafColor(text);
                    break;
                case SIZE:
                    try {
                        currentFlower.getVisualParameters().setSize(Integer.valueOf(text.toUpperCase()));
                    } catch (InvalidValueException e) {
                        logger.warn(e);
                    }
                    break;
                case TEMPERATURE:
                    try {
                        currentFlower.getGrowingTips().setTemperature(Integer.parseInt(text));
                    } catch (InvalidValueException e) {
                        logger.warn(e);
                    }
                    break;
                case WATER:
                    currentFlower.getGrowingTips().setWater(Integer.parseInt(text));
                    break;
                case MULTIPLYING:
                    currentFlower.setMultiplying(MultiplyingType.valueOf(text.toUpperCase()));
                    break;
                case FLOWERING:
                    ((IndoorFlower) currentFlower).setFlowering(Boolean.parseBoolean(text));
                    break;
                case LIFETIME:
                    ((OutdoorFlower) currentFlower).setLifetime(FlowerLifetime.valueOf(text.toUpperCase()));
                    break;
                default:
                    throw new SAXException("Tag was not recognized");
            }
        }
    }
}
