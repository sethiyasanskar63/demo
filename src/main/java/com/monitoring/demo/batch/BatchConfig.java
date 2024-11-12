package com.monitoring.demo.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.policy.SimpleCompletionPolicy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;

@Configuration
public class BatchConfig {

	@Bean("reversedStringBatchJob")
	public Job reversedStringBatchJob(JobRepository jobRepository, @Qualifier("reversedStringStep") Step reversedStringStep) {
		return new SimpleJobBuilder(new JobBuilder("reversedStringBatchJob", jobRepository)).start(reversedStringStep).build();
	}

	@Bean("reversedStringStep")
	public Step reversedStringStep(PlatformTransactionManager transactionManager, JobRepository jobRepository, @StepScope Reader reader, @StepScope Processor processor, @StepScope Writer writer, TaskExecutor taskExecutor) {
		// @formatter:off
		return new StepBuilder("reversedStringStep", jobRepository).<String, Map<String, String>>chunk(new SimpleCompletionPolicy(1), transactionManager)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.faultTolerant().retryLimit(3).retry(Exception.class)
				.transactionManager(transactionManager)
				.taskExecutor(taskExecutor)
				.build();
		// @formatter:on
	}
}
