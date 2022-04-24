package net.crizin.devtools.processor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.TimeZone;
import org.springframework.stereotype.Component;

@Component
public class LongUnixTimestampProcessor implements Processor {

	private static final String TITLE = "Timestamp to DateTime (Long)";
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

	@Override
	public String getTitle() {
		return TITLE;
	}

	@Override
	public Optional<Result> process(String text) {
		OffsetDateTime datetime;

		try {
			datetime = OffsetDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(text.trim())), TimeZone.getDefault().toZoneId());
		} catch (Exception e) {
			return Optional.empty();
		}

		int probability;
		int yearDiff = Math.abs(datetime.getYear() - LocalDate.now().getYear());

		if (yearDiff == 0) {
			probability = 100;
		} else if (yearDiff <= 10) {
			probability = 80;
		} else if (yearDiff <= 100) {
			probability = 50;
		} else {
			probability = 0;
		}

		return Optional.of(new Result(TITLE, FORMATTER.format(datetime), probability));
	}
}