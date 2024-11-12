package com.monitoring.demo.batch;

import jakarta.annotation.PostConstruct;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
@StepScope
public class Reader implements ItemReader<String> {

	private Iterator<String> stringIterator;

	@PostConstruct
	public void init() {
		List<String> strings = List.of("test1", "test2", "test3", "test4", "test5");
		stringIterator = strings.iterator();
	}

	@Override
	public String read() {
		if (stringIterator != null && stringIterator.hasNext()) {
			return stringIterator.next();
		}
		return null;
	}
}
