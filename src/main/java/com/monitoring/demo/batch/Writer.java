package com.monitoring.demo.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@StepScope
public class Writer implements ItemWriter<Map<String, String>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(Writer.class);
	private final BatchMetricUtil batchMetricUtil;

	@Autowired
	public Writer(BatchMetricUtil batchMetricUtil) {
		this.batchMetricUtil = batchMetricUtil;
	}

	@Override
	public void write(Chunk<? extends Map<String, String>> chunk) {
		for (Map<String, String> item : chunk.getItems()) {
			LOGGER.info("Processed item: {}", item);
			batchMetricUtil.jobCompleted();
		}
	}
}
