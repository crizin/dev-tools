package net.crizin.devtools.processor.impl;

import java.util.Optional;
import java.util.regex.Pattern;
import net.crizin.devtools.processor.Processor;
import net.crizin.devtools.processor.Result;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Component;

@Component
public class HtmlDecodeProcessor implements Processor {

	private static final String TITLE = "Decode HTML";
	private static final String SORT_KEY = "10.html.decode";
	private static final Pattern ENCODED_HTML_PATTERN = Pattern.compile("&\\w+;", Pattern.CASE_INSENSITIVE);

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
		String decoded = StringEscapeUtils.unescapeHtml4(text);

		if (decoded.equals(text)) {
			return Optional.empty();
		}

		return Optional.of(new Result(TITLE, decoded, ENCODED_HTML_PATTERN.matcher(text).find()));
	}
}