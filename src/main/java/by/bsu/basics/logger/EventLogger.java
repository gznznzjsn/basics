package by.bsu.basics.logger;

import by.bsu.basics.entity.Event;

import java.io.IOException;

public interface EventLogger {
    void logEvent(Event event) throws IOException;
}
