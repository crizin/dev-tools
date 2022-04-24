package net.crizin.devtools.processor.impl;

import java.util.Optional;
import net.crizin.devtools.processor.Processor;
import net.crizin.devtools.processor.Result;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Component;

@Component
public class HtmlEncodeProcessor implements Processor {

	private static final String TITLE = "Encode HTML";
	private static final String SORT_KEY = "10.html.encode";

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
		String encoded = StringEscapeUtils.escapeHtml4(text);

		if (encoded.equals(text)) {
			return Optional.empty();
		}

		return Optional.of(new Result(TITLE, encoded, false));
	}
}