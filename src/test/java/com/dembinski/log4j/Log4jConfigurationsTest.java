package com.dembinski.log4j;


import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.Logger;

/**
 * @see <a href="https://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/PatternLayout.html">Pattern Layout website</a>
 */
class Log4jConfigurationsTest {
    Log4jConfigurations log4jConfigurations = new Log4jConfigurations();

    @Test
    public void testLoggerConfiguration() {
        log4jConfigurations.logDifferentLevels();
    }

    @Test
    public void testObjectProvidedToLogger() {
        Object obj = "StringPassedToObject";
        Logger classLogger = LogManager.getLogger(Log4jConfigurationsTest.class);
        Logger stringLogger = LogManager.getLogger("TestString");
        Logger justLogger = LogManager.getLogger();
        Logger objectLogger = LogManager.getLogger(obj);

        classLogger.info("classLogger");
        stringLogger.info("stringLogger");
        justLogger.info("justLogger");
        objectLogger.info("objectLogger");
    }

}