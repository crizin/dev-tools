package net.crizin.devtools.processor.impl;

import java.util.Optional;
import net.crizin.devtools.processor.Processor;
import net.crizin.devtools.processor.Result;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class MD5HashProcessor implements Processor {

	private static final String TITLE = "MD5 Hash";
	private static final String SORT_KEY = "99.hash.md5";

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

		return Optional.of(new Result(TITLE, DigestUtils.md5Hex(text), false));
	}
}