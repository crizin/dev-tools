package net.crizin.devtools.processor.impl;

import java.util.Optional;
import net.crizin.devtools.processor.Processor;
import net.crizin.devtools.processor.Result;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class SHA256HashProcessor implements Processor {

	private static final String TITLE = "SHA-256 Hash";
	private static final String SORT_KEY = "99.hash.sha256";

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
		if (text.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(new Result(TITLE, DigestUtils.sha256Hex(text), false));
	}
}