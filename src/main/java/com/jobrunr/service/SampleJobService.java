package com.jobrunr.service;

import lombok.extern.slf4j.Slf4j;
import org.jobrunr.jobs.annotations.Job;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class SampleJobService {

    @Job(name = "The sample job with variable %0", retries = 2)
    public void executeSampleJob(String variable) {
        log.info("The sample job has begun. The variable you passed is {}", variable);
    }

    @Job(name = "The sample job")
    public void doWork() {
        log.info("The sample job has begun ");
    }

}