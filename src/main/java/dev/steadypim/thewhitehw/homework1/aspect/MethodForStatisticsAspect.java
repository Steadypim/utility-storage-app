package dev.steadypim.thewhitehw.homework1.aspect;

import dev.steadypim.thewhitehw.homework1.event.utilitystoragestatistics.StatisticsEvent;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationEventPublisher;

@Aspect
@Component
@RequiredArgsConstructor
public class MethodForStatisticsAspect {
    private final ApplicationEventPublisher eventPublisher;

    @After("@annotation(dev.steadypim.thewhitehw.homework1.annotation.MethodForStatistics)")
    public void afterMethodForStatisticsExecution(){
        eventPublisher.publishEvent(new StatisticsEvent("Update UtilityStorage statistics"));
    }
}
