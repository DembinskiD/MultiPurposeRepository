package com.dembinski.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jConfigurations {

    public void logDifferentLevels() {
        Logger logger = LogManager.getLogger(Log4jConfigurations.class);

        logger.info("info log");
        logger.debug("debug log");
        logger.warn("warn log");
        logger.fatal("fatal log");
    }

    public static void main(String[] args) {
        new Log4jConfigurations().logDifferentLevels();
    }
}
