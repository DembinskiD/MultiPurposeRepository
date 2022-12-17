package com.dembinski.log4j;


import org.junit.jupiter.api.Test;

class Log4jConfigurationsTest {
    Log4jConfigurations log4jConfigurations = new Log4jConfigurations();

    @Test
    public void testLoggerConfiguration() {
        log4jConfigurations.logDifferentLevels();
    }

}