package by.bsu.basics.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class StatisticsAspect {
    private Map<Class<?>, Integer> countMap;

    private void init(){
        countMap = new HashMap<>();
    }

    @Pointcut("execution(* *.logEvent(..))")
    public void allLogEventMethods() {
    }

    @Before("allLogEventMethods()")
    public void countInvocations(JoinPoint joinPoint) {
        Class<?> targetClass = joinPoint.getTarget().getClass();
        if (countMap.containsKey(targetClass)) {
            countMap.put(targetClass, countMap.get(targetClass) + 1);
        } else {
            countMap.put(targetClass, 1);
        }

    }

    private void destroy(){
        countMap.forEach((key,value)-> System.out.println(key.getSimpleName()+" - "+countMap.get(key)+" invocations;"));
    }

}
