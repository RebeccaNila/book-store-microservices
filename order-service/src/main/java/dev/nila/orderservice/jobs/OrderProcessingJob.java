package dev.nila.orderservice.jobs;

import dev.nila.orderservice.domain.OrderService;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderProcessingJob {
    private final OrderService orderService;
    // if we are running on prod multi nodes calling same job schedule
    // so we want to lock only one node should process this job
    // we will use ShedLock
    // this will ensure that only one of the instance process job
    // locking particular task ensure only one node will work (persistence mechanism)
    @Scheduled(cron = "${orders.new-orders-job-cron}")
    @SchedulerLock(name = "processNewOrders")
    public void processNewOrders() {
        // To assert that lock is held (prevents misconfiguration errors)
        LockAssert.assertLocked();
        log.info("Processing new orders at {}", Instant.now());
        orderService.processNewOrders();
    }
}
