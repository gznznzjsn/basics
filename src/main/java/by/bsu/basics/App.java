package by.bsu.basics;

import by.bsu.basics.entity.Client;
import by.bsu.basics.entity.Event;
import by.bsu.basics.entity.EventType;
import by.bsu.basics.logger.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Map;

public class App {
    Client client;
    Map<EventType,EventLogger> loggers;

    public App() {
    }

    public App(Client client, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.loggers = loggers;
    }

    public void logEvent(Event event, EventType type) throws IOException {
        EventLogger logger = loggers.get(type);
        logger.logEvent(event);
    }

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        App app = applicationContext.getBean("app", App.class);
        Event event = applicationContext.getBean("event", Event.class);
        event.setMsg("Blablablabla, user 1");
        app.logEvent(event, EventType.ERROR);
        app.logEvent(event, EventType.INFO);
        app.logEvent(event, EventType.INFO);
        app.logEvent(event, EventType.INFO);
        app.logEvent(event, EventType.INFO);
        app.logEvent(event, EventType.ERROR);
        app.logEvent(event, EventType.INFO);
        applicationContext.close();
    }
}
