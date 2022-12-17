package com.dembinski.log4j;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Log4jConfigurations {

    public void logDifferentLevels() {
        logger.info("info log");
        logger.debug("debug log");
        logger.warn("warn log");
        logger.fatal("fatal log");
    }

    public static void main(String[] args) {
        new Log4jConfigurations().logDifferentLevels();
    }
}
