package com.jobrunr.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCronJob {
    private Integer id;
    private String cron;
}
