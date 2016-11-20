package com.suboch.task5.demo;

import com.suboch.task5.builder.FlowerStAXBuilder;
import com.suboch.task5.builder.FlowersDOMBuilder;
import com.suboch.task5.builder.FlowersSAXBuilder;
import com.suboch.task5.reporter.Reporter;
import com.suboch.task5.validator.FlowerErrorHandler;
import com.suboch.task5.validator.SAXValidator;
import com.suboch.task5.validator.XMLValidator;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 */
public class Demo {
    private static final Path FILE_PATH = Paths.get("data", "greenhouse.xml");
    private static final Path SCHEMA_PATH = Paths.get("data", "greenhouse.xsd");
    private static Reporter reporter = new Reporter();

    public static void main(String[] args) {
        XMLValidator validator = new XMLValidator(FILE_PATH, SCHEMA_PATH);
        validator.validate();

        FlowersSAXBuilder flowersSAXBuilder = new FlowersSAXBuilder();
        flowersSAXBuilder.buildFlowersList(FILE_PATH.toFile().getPath());
        reporter.reportFlowers("\nThe result of SAX builder:\n", flowersSAXBuilder.getFlowers());

        FlowersDOMBuilder flowersDOMBuilder = new FlowersDOMBuilder();
        flowersDOMBuilder.buildFlowersList(FILE_PATH.toFile().getPath());
        reporter.reportFlowers("\nThe result of DOM builder:\n", flowersDOMBuilder.getFlowers());

        FlowerStAXBuilder flowerStAXBuilder = new FlowerStAXBuilder();
        flowerStAXBuilder.buildFlowersList(FILE_PATH.toFile().getPath());
        reporter.reportFlowers("\nThe result of StAX builder:\n", flowerStAXBuilder.getFlowers());
    }
}
