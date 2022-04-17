package by.bsu.basics.logger.impl;

import by.bsu.basics.entity.Event;
import by.bsu.basics.logger.EventLogger;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event);
    }
}
