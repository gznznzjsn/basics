package by.bsu.basics.logger;

import by.bsu.basics.entity.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private final int cacheSize;
    private final List<Event> cache;

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        cache = new ArrayList<Event>();
    }

    public void logEvent(Event event) throws IOException {
        cache.add(event);
        if (cache.size() == cacheSize) {
            while (!cache.isEmpty()){
                super.logEvent(cache.remove(0));
            }
        }
    }

    public void destroy() throws IOException {
        while (!cache.isEmpty()){
            super.logEvent(cache.remove(0));
        }
    }
}
