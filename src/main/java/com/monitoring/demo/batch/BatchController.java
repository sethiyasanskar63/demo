package com.monitoring.demo.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class BatchController {

	private final BatchService batchService;

	public BatchController(BatchService batchService) {
		this.batchService = batchService;
	}

	@GetMapping("/start")
	public ResponseEntity<String> startBatchJob() {
		try {
			JobExecution jobExecution = batchService.startCustomerStatusEvaluationBatchJob();
			return ResponseEntity.ok("Batch job started with status: " + jobExecution.getStatus());
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Failed to start batch job: " + e.getMessage());
		}
	}
}
