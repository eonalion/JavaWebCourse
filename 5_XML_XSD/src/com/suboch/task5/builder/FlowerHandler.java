package com.suboch.task5.builder;

import com.suboch.task5.entity.Flower;
import com.suboch.task5.entity.MultiplyingType;
import com.suboch.task5.entity.SoilType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FlowerHandler extends DefaultHandler {
    private List<Flower> flowers;
    private Flower currentFlower;
    private FlowersCharacteristic currentElement = FlowersCharacteristic.EMPTY_TAG;

    private static final String DASH = "-";
    private static final String UNDERLINE = "_";

    public FlowerHandler(){
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
        if (FlowersCharacteristic.FLOWER.getValue().equals(localName)) {
            currentFlower = new Flower();
            currentFlower.setName(attributes.getValue(FlowersCharacteristic.NAME.getValue()));
        } else if (FlowersCharacteristic.FLOWERS.getValue().equals(localName) ||
                FlowersCharacteristic.VISUAL_PARAMETERS.getValue().equals(localName) ||
                FlowersCharacteristic.GROWING_TIPS.getValue().equals(localName)) {
            currentElement = FlowersCharacteristic.EMPTY_TAG;
        } else {
            currentElement = FlowersCharacteristic.valueOf(localName.toUpperCase().replace(DASH, UNDERLINE));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (FlowersCharacteristic.FLOWER.getValue().equals(localName)) {
            flowers.add(currentFlower);
        }
        currentElement = FlowersCharacteristic.EMPTY_TAG;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String text = new String(ch, start, length).trim();
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
                    currentFlower.getVisualParameters().setSize(Integer.valueOf(text.toUpperCase()));
                    break;
                case TEMPERATURE:
                    currentFlower.getGrowingTips().setTemperature(Integer.parseInt(text));
                    break;
                case WATER:
                    currentFlower.getGrowingTips().setWater(Integer.parseInt(text));
                    break;
                case MULTIPLYING:
                    currentFlower.setMultiplying(MultiplyingType.valueOf(text.toUpperCase()));
                    break;
                default:
                    throw new SAXException();//TODO: exception
            }
        }
    }
}
