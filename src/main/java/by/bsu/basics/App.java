package by.bsu.basics;

import by.bsu.basics.entity.Client;
import by.bsu.basics.entity.Event;
import by.bsu.basics.entity.EventType;
import by.bsu.basics.logger.EventLogger;
import by.bsu.basics.logger.impl.DBLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Map;

public class App {
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    public App() {
    }

    public App(EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public void logEvent(Event event, EventType type) throws IOException {
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        App app = applicationContext.getBean("app", App.class);
        Event event = applicationContext.getBean("event", Event.class);
        Client client = applicationContext.getBean(Client.class);
        System.out.println(client.getGreeting());
        event.setMsg("Blablablabla, user 1");
        app.logEvent(event, null);
        app.logEvent(event, EventType.INFO);
        app.logEvent(event, EventType.INFO);

        app.logEvent(event, EventType.INFO);
        app.logEvent(event, EventType.INFO);
        app.logEvent(event, EventType.ERROR);
        app.logEvent(event, EventType.INFO);
        EventLogger dbLogger = (EventLogger) applicationContext.getBean("dbLogger");
        event = applicationContext.getBean("event", Event.class);
        dbLogger.logEvent(event);
        event = applicationContext.getBean("event", Event.class);
        dbLogger.logEvent(event);
        event = applicationContext.getBean("event", Event.class);
        dbLogger.logEvent(event);
        applicationContext.close();
    }


}
