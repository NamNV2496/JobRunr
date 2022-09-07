package com.jobrunr.service;

import lombok.extern.slf4j.Slf4j;
import org.jobrunr.jobs.annotations.Job;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class SampleJobService {

    public static final long EXECUTION_TIME = 5000L;

    private AtomicInteger count = new AtomicInteger();

    @Job(name = "The sample job with variable %0", retries = 2)
    public void executeSampleJob(String variable) {

        log.info("The sample job has begun. The variable you passed is {}", variable);
        try {
            Thread.sleep(EXECUTION_TIME);
        } catch (Exception e) {
            log.error("Error while executing sample job", e);
            e.printStackTrace();
        } finally {
            count.incrementAndGet();
            log.info("Sample job has finished...");
        }
    }

}