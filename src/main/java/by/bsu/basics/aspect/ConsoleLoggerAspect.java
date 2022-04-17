package by.bsu.basics.aspect;

import by.bsu.basics.entity.Event;
import by.bsu.basics.logger.EventLogger;
import by.bsu.basics.logger.impl.ConsoleEventLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ConsoleLoggerAspect {
    private final int max;
    private int counter = 0;
    private final EventLogger otherLogger;

    public ConsoleLoggerAspect(int max, EventLogger otherLogger) {
        this.max = max;
        this.otherLogger = otherLogger;
    }

    @Pointcut("execution(* by.bsu.basics.logger.impl.ConsoleEventLogger.logEvent(..))")
    public void consoleLoggerMethods() {
    }

    @Around("consoleLoggerMethods() && args(event)")
    public void aroundLogEvent(ProceedingJoinPoint joinPoint, Event event) throws Throwable {
            counter++;
            if (counter > max) {
                otherLogger.logEvent(event);
            } else {
                joinPoint.proceed(new Object[]{event});
            }
    }

}
