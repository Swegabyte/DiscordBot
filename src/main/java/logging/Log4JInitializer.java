package logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;

public class Log4JInitializer {

    public static Logger initializeLogger(Class className){
        File file = new File("src/main/resources/log4j2.xml");
        LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
        context.setConfigLocation(file.toURI());
        return LogManager.getLogger(className);
    }
}
