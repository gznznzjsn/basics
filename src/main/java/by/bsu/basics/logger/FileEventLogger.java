package by.bsu.basics.logger;

import by.bsu.basics.entity.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    private final String fileName;
    private File file;

    private void init() throws IOException {
        this.file = new File(fileName);
        if(!file.canWrite()){
            throw new IOException("Writing not allowed");
        }
    }

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void logEvent(Event event) throws IOException {
        FileUtils.writeStringToFile(file, event +"\n",true);
    }
}
