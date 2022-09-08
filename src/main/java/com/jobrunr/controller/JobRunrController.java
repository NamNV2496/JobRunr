package com.jobrunr.controller;

import com.jobrunr.domain.RequestCronJob;
import com.jobrunr.service.SampleJobService;
import lombok.RequiredArgsConstructor;
import org.jobrunr.jobs.JobId;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/jobrunr")
@RequiredArgsConstructor
public class JobRunrController {

    private final JobScheduler jobScheduler;
    private final SampleJobService sampleJobService;

    // for 1 times job
    @GetMapping(value = "/enqueue/{input}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> enqueue(@PathVariable("input") @DefaultValue("default-input") String input) {
        UUID uid = UUID.randomUUID();
        jobScheduler.enqueue(uid, () -> sampleJobService.executeSampleJob(input));
        return okResponse(uid.toString());
    }

    // for 1 times job in a specification time
    @GetMapping(value = "/schedule/{input}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> schedule(
            @PathVariable("input") @DefaultValue("default-input") String input,
            @RequestParam("scheduleAt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime scheduleAt) {
        UUID uid = UUID.randomUUID();
        jobScheduler.schedule(uid, scheduleAt, () -> sampleJobService.executeSampleJob(input));
        return okResponse(uid.toString());
    }

    // for many times use cron expression
    @PostMapping(value = "/scheldule")
    public ResponseEntity<String> setSchedule(@RequestBody RequestCronJob requestCronJob) {
        jobScheduler.scheduleRecurrently(requestCronJob.getId().toString(), requestCronJob.getCron(), sampleJobService::doWork);
        return new ResponseEntity<>(requestCronJob.getId().toString(), HttpStatus.OK);
    }

// delete job throw id
    @PostMapping(value = "/scheldule/stop/{id}")
    public ResponseEntity<String> setSchedule(@PathVariable String id) {
        jobScheduler.delete(id);
        return okResponse("job scheduled successfully");
    }


    private ResponseEntity<String> okResponse(String feedback) {
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }
}

