package by.bsu.basics.logger;

import by.bsu.basics.entity.Event;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event);
    }
}
