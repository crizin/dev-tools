package net.crizin.devtools.processor;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ProcessorService {

	private final Collection<Processor> processors;

	public ProcessorService(ApplicationContext context) {
		processors = context.getBeansOfType(Processor.class).values().stream().sorted(Comparator.comparing(Processor::getSortKey)).toList();
	}

	public List<Result> process(String text) {
		return processors.stream().flatMap(processor -> processor.process(text).stream())
				.sorted(Comparator.comparing(Result::highlight).reversed())
				.toList();
	}

	public List<String> getTitles() {
		return processors.stream().map(Processor::getTitle).toList();
	}
}