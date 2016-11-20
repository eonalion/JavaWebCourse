package com.suboch.task5.reporter;

import com.suboch.task5.entity.Flower;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 *
 */
public class Reporter {
    private static Logger logger = LogManager.getLogger();

    public void reportValidationResult() {

    }

    public void reportFlowers(String message, List<Flower> flowerList) {
        logger.log(Level.INFO, message + flowerList + "\n");
    }
}
