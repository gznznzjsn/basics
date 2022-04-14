package by.bsu.basics.logger;

import by.bsu.basics.entity.Event;

import java.io.IOException;
import java.util.List;

public class CombinedEventLogger implements EventLogger {
    List<EventLogger> loggerList;

    public CombinedEventLogger(List<EventLogger> loggerList) {
        this.loggerList = loggerList;
    }

    public void logEvent(Event event) throws IOException {
        for (EventLogger logger : loggerList) {
            logger.logEvent(event);
        }
    }
}
