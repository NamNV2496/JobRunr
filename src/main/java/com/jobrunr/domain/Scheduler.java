package com.jobrunr.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@Data
@Table(name = "scheduler")
@NoArgsConstructor
@AllArgsConstructor
public class Scheduler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column
    private String name;
    @Column
    private String cron;

}
