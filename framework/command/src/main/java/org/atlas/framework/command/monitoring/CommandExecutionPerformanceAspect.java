package org.atlas.framework.command.monitoring;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

@Component
@Aspect
@Slf4j
public class CommandExecutionPerformanceAspect {

    private static final long acceptedMaxExecutionTime = 3000; // 3 seconds

    @Around("execution(* org.atlas.framework.command.executor.CommandExecutor.handle(..))")
    public Object aroundHandle(ProceedingJoinPoint joinPoint) throws Throwable {
        // Get class name
        Class<?> clazz = joinPoint.getTarget().getClass();
        String className = clazz.getName();

        // Get method name
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String methodName = method.getName();

        // Log method start
        log.info("Started executing request {}#{}", className, methodName);

        // Execute method and measure elapsed time
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            long timeElapsed = stopWatch.getTotalTimeMillis();
            if (timeElapsed <= acceptedMaxExecutionTime) {
                log.info("Finished executing request {}#{}. Elapsed time: {} ms.", className, methodName, timeElapsed);
            } else {
                log.warn("Finished executing request {}#{}. Elapsed time: {} ms ===> Exceeded the maximum time, please check its performance!!!", className, methodName, timeElapsed);
            }
        }
    }
}
