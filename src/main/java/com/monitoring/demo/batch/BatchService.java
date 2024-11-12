package com.monitoring.demo.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchService {

	private final JobLauncher jobLauncher;
	private final Job reversedStringBatchJob;
	private final BatchMetricUtil batchMetricUtil;

	@Autowired
	public BatchService(JobLauncher jobLauncher, Job reversedStringBatchJob, BatchMetricUtil batchMetricUtil) {
		this.jobLauncher = jobLauncher;
		this.reversedStringBatchJob = reversedStringBatchJob;
		this.batchMetricUtil = batchMetricUtil;
	}

	public JobExecution startCustomerStatusEvaluationBatchJob() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder().addLong("startAt", System.currentTimeMillis()).toJobParameters();
		JobExecution jobExecution = jobLauncher.run(reversedStringBatchJob, jobParameters);
		batchMetricUtil.jobStarted();
		return jobExecution;
	}
}
