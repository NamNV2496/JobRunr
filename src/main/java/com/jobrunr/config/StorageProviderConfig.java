//package com.jobrunr.config;
//
//import org.jobrunr.jobs.mappers.JobMapper;
//import org.jobrunr.storage.InMemoryStorageProvider;
//import org.jobrunr.storage.StorageProvider;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class StorageProviderConfig {
//    if you want to save in memory storage
//    @Bean
//    public StorageProvider storageProvider(JobMapper jobMapper) {
//        InMemoryStorageProvider storageProvider = new InMemoryStorageProvider();
//        storageProvider.setJobMapper(jobMapper);
//        return storageProvider;
//    }
//}