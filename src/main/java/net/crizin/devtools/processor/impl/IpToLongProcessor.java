package net.crizin.devtools.processor.impl;

import com.github.sisyphsu.dateparser.DateParserUtils;
import java.time.OffsetDateTime;
import java.util.Optional;
import net.crizin.devtools.processor.Processor;
import net.crizin.devtools.processor.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DateTimeProcessor implements Processor {

	private static final String TITLE = "DateTime to Timestamp";
	private static final String SORT_KEY = "10.datetime";

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
		if (StringUtils.isNumericSpace(text)) {
			return Optional.empty();
		}

		OffsetDateTime dateTime;

		try {
			dateTime = DateParserUtils.parseOffsetDateTime(text);
		} catch (Exception e) {
			return Optional.empty();
		}

		return Optional.of(new Result(TITLE, String.valueOf(dateTime.toInstant().getEpochSecond()), true));
	}
}