package com.monitoring.demo.batch;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@StepScope
public class Processor implements ItemProcessor<String, Map<String, String>> {

	@Override
	public Map<String, String> process(String item) {
		String reversedString = new StringBuilder(item).reverse().toString();
		Map<String, String> stringMap = new HashMap<>();
		stringMap.put(item, reversedString);
		return stringMap;
	}
}
