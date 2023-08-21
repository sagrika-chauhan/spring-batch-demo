package com.javatechie.spring.batch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Slf4j
public class JobStatusTasklet implements Tasklet {

    @Autowired
    private Job job;
    @Autowired
    private JobExplorer jobExplorer;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
        log.info("Inside JobStatusTasklet ");
        JobInstance lastJobInstance = jobExplorer.getLastJobInstance(job.getName());

        if (lastJobInstance != null) {
            List<JobExecution> runningExecutions = jobExplorer.getJobExecutions(lastJobInstance);

            for (JobExecution execution : runningExecutions) {
                if (execution.getStatus() == BatchStatus.STARTED  || execution.getStatus() == BatchStatus.STARTING) {
                    throw new IllegalStateException("Inside Tasklet {}" + "Job is already running: " + job.getName());
                }
            }
        }
        return null;
    }
}
