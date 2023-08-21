package com.javatechie.spring.batch.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/jobs")
public class JobController{

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    @Autowired
    private JobExplorer jobExplorer;

    @PostMapping("/importCustomers")
    public void importCsvToDBJob() {
//        JobInstance lastJobInstance = jobExplorer.getLastJobInstance(job.getName());
//
//        if (lastJobInstance != null) {
//            List<JobExecution> runningExecutions = jobExplorer.getJobExecutions(lastJobInstance);
//
//            for (JobExecution execution : runningExecutions) {
//                if (execution.getStatus() == BatchStatus.STARTED || execution.getStatus() == BatchStatus.STARTING ) {
//                    throw new IllegalStateException("Inside Controller {}" + "Job is already running: " + job.getName());
//                }
//            }
//        }
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startedAt", System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            e.printStackTrace();
        }
        log.info("Job finished with status: ");
    }

    @PostMapping("/importCustomersToDB")
    public void importCsvToDBJob2() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startedAt", System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            e.printStackTrace();
        }
        log.info("Job finished with status: ");
    }

}
