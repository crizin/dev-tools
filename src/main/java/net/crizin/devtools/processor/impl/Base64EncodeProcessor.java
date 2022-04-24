package net.crizin.devtools.processor.impl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import net.crizin.devtools.processor.Processor;
import net.crizin.devtools.processor.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class UrlEncodeProcessor implements Processor {

	private static final String TITLE = "Encode URL";
	private static final String SORT_KEY = "10.url.encode";
	private static final String[] adviseSearch = new String[]{"+", "*", "%7E"};
	private static final String[] adviseReplacement = new String[]{"%20", "%2A", "~"};

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
		String encoded = StringUtils.replaceEach(URLEncoder.encode(text, StandardCharsets.UTF_8), adviseSearch, adviseReplacement);

		if (encoded.equals(text)) {
			return Optional.empty();
		}

		return Optional.of(new Result(TITLE, encoded, false));
	}
}