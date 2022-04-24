package net.crizin.devtools.processor.impl;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import net.crizin.devtools.processor.Processor;
import net.crizin.devtools.processor.Result;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class Base64DecodeProcessor implements Processor {

	private static final String TITLE = "Decode Base64";
	private static final String SORT_KEY = "90.base64.decode";

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
		if (StringUtils.isBlank(text)) {
			return Optional.empty();
		}

		String decoded;

		try {
			decoded = new String(Base64.decodeBase64(text.trim()), StandardCharsets.UTF_8);
		} catch (Exception e) {
			return Optional.empty();
		}

		if (!Base64.encodeBase64String(decoded.getBytes(StandardCharsets.UTF_8)).equals(text.trim())) {
			return Optional.empty();
		}

		return Optional.of(new Result(TITLE, decoded, true));
	}
}