package net.crizin.devtools.processor.impl;

import java.util.Optional;
import java.util.regex.Pattern;
import net.crizin.devtools.processor.Processor;
import net.crizin.devtools.processor.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class SafeFilenameProcessor implements Processor {

	private static final String TITLE = "Convert to safe filename";
	private static final String SORT_KEY = "90.safe.filename";
	private static final String[] ADVISE_SEARCH = new String[]{":", "/", "*", "?", "<", ">", "\"", "\\", "|"};
	private static final String[] ADVISE_REPLACEMENT = new String[]{"：", "／", "＊", "？", "〈", "〉", "＂", "＼", "｜"};
	private static final Pattern WHITE_SPACE_PATTERN = Pattern.compile("\\s+");

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
		String result = StringUtils.replaceEach(text, ADVISE_SEARCH, ADVISE_REPLACEMENT);
		result = WHITE_SPACE_PATTERN.matcher(result).replaceAll(" ").trim();

		if (text.trim().equals(result)) {
			return Optional.empty();
		}

		return Optional.of(new Result(TITLE, result, false));
	}
}