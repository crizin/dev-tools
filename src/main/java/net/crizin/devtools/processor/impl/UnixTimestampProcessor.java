package net.crizin.devtools.processor.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.TimeZone;
import net.crizin.devtools.processor.Processor;
import net.crizin.devtools.processor.Result;
import org.springframework.stereotype.Component;

@Component
public class UnixTimestampProcessor implements Processor {

	private static final String TITLE = "Timestamp to DateTime";
	private static final String SORT_KEY = "10.time.stamp";
	private static final BigDecimal UPPER_BOUND = BigDecimal.valueOf(Integer.MAX_VALUE);
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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
		BigDecimal value;

		try {
			value = new BigDecimal(text);
		} catch (Exception e) {
			return Optional.empty();
		}

		if (value.compareTo(BigDecimal.ZERO) < 0 || value.compareTo(UPPER_BOUND) > 0) {
			return Optional.empty();
		}

		OffsetDateTime datetime;

		try {
			datetime = OffsetDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(text.trim()) * 1000L), TimeZone.getDefault().toZoneId());
		} catch (Exception e) {
			return Optional.empty();
		}

		int yearDiff = Math.abs(datetime.getYear() - LocalDate.now().getYear());

		return Optional.of(new Result(TITLE, FORMATTER.format(datetime), yearDiff <= 10));
	}
}