package net.crizin.devtools.processor.impl;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import net.crizin.devtools.processor.Processor;
import net.crizin.devtools.processor.Result;
import org.springframework.stereotype.Component;

@Component
public class Base64EncodeProcessor implements Processor {

	private static final String TITLE = "Encode Base64";
	private static final String SORT_KEY = "90.base64.encode";

	@Override
	public String getTitle() {
		return TITLE;
	}

	@Override
	public String getSortKey() {
		return SORT_KEY;
	}

	@Override
	public Optional<Result> process(String text) {
		return Optional.of(new Result(TITLE, Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8)), false));
	}
}