package com.suboch.task5.builder;

import com.suboch.task5.entity.*;
import com.suboch.task5.exception.InvalidValueException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FlowerStAXBuilder {
    private static Logger logger = LogManager.getLogger();
    private List<Flower> flowers;
    private XMLInputFactory inputFactory;

    private static final String DASH = "-";
    private static final String UNDERLINE = "_";

    public FlowerStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
        flowers = new ArrayList<>();
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public void buildFlowersList(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (FlowersCharacteristic.INDOOR_FLOWER.getValue().equals(name)) {
                        Flower flower = buildFlower(new IndoorFlower(), reader);
                        flowers.add(flower);
                    } else if (FlowersCharacteristic.OUTDOOR_FLOWER.getValue().equals(name)) {
                        Flower flower = buildFlower(new OutdoorFlower(), reader);
                        flowers.add(flower);
                    }
                }
            }
        } catch (InvalidValueException e) {
            logger.warn(e);
        } catch (XMLStreamException e) {
            logger.fatal(e);
        } catch (FileNotFoundException e) {
            logger.fatal(e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                logger.fatal(ex);
            }
        }
    }

    private Flower buildFlower(Flower flower, XMLStreamReader reader) throws XMLStreamException, InvalidValueException {
        String name;
        FlowersCharacteristic characteristic;
        while (reader.hasNext()) {
            int type = reader.getEventType();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    characteristic = FlowersCharacteristic.valueOf(name.toUpperCase().replace(DASH, UNDERLINE));
                    switch (characteristic) {
                        case INDOOR_FLOWER:
                        case OUTDOOR_FLOWER:
                            flower.setName(reader.getAttributeValue(null, FlowersCharacteristic.NAME.getValue()));
                            break;
                        case SOIL:
                            flower.setSoilType(SoilType.valueOf(getElementValue(reader).toUpperCase()));
                            break;
                        case ORIGIN:
                            flower.setOrigin(getElementValue(reader));
                            break;
                        case STEM_COLOR:
                            flower.getVisualParameters().setStemColor(getElementValue(reader));
                            break;
                        case LEAF_COLOR:
                            flower.getVisualParameters().setLeafColor(getElementValue(reader));
                            break;
                        case SIZE:
                            int size = Integer.parseInt(getElementValue(reader));
                            flower.getVisualParameters().setSize(size);
                            break;
                        case GROWING_TIPS:
                            boolean heliphilous = Boolean.parseBoolean(reader.getAttributeValue(null, FlowersCharacteristic.HELIPHILOUS.getValue()));
                            flower.getGrowingTips().setHeliphilous(heliphilous);
                            break;
                        case TEMPERATURE:
                            int temperature = Integer.parseInt(getElementValue(reader));
                            flower.getGrowingTips().setTemperature(temperature);
                            break;
                        case WATER:
                            int water = Integer.parseInt(getElementValue(reader));
                            flower.getGrowingTips().setWater(water);
                            break;
                        case MULTIPLYING:
                            flower.setMultiplying(MultiplyingType.valueOf(getElementValue(reader).toUpperCase()));
                            break;
                        case FLOWERING:
                            ((IndoorFlower) flower).setFlowering(Boolean.parseBoolean(getElementValue(reader)));
                            break;
                        case LIFETIME:
                            ((OutdoorFlower) flower).setLifetime(FlowerLifetime.valueOf(getElementValue(reader).toUpperCase()));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    characteristic = FlowersCharacteristic.valueOf(name.toUpperCase().replace(DASH, UNDERLINE));
                    if (characteristic == FlowersCharacteristic.INDOOR_FLOWER || characteristic == FlowersCharacteristic.OUTDOOR_FLOWER) {
                        return flower;
                    }
                    break;
            }
            reader.next();
        }
        throw new XMLStreamException("Unknown element in tag Flower");
    }

    private String getElementValue(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText().replace(DASH, UNDERLINE);
        }
        return text;
    }
}
