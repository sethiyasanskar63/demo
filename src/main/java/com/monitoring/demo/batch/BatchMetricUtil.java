package com.monitoring.demo.batch;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BatchMetricUtil {

	private static final String BATCH_JOB_STARTED = "batch_job_started";
	private static final String BATCH_JOB_COMPLETED = "batch_job_completed";
	private static final String METRIC_FAILED = "Metric failed.";

	private final MeterRegistry meterRegistry;

	@Autowired
	public BatchMetricUtil(MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;
	}

	@PostConstruct
	public void init() {
		// @formatter:off
		Counter.builder(BATCH_JOB_STARTED)
				.description("Total number of customer evaluation jobs started")
				.register(meterRegistry);
		Counter.builder(BATCH_JOB_COMPLETED)
				.description("Total number of customer evaluation jobs completed")
				.register(meterRegistry);
		// @formatter:on
	}

	public void jobStarted() {
		Metrics.counter(BATCH_JOB_STARTED).increment();
	}

	public void jobCompleted() {
		Metrics.counter(BATCH_JOB_COMPLETED).increment();
	}
}
