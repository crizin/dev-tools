package net.crizin.devtools.processor;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class UrlDecodeProcessor implements Processor {

	private static final String TITLE = "Decode URL";
	private static final Pattern ENCODED_URL_PATTERN = Pattern.compile("%[\\dA-F]{2}", Pattern.CASE_INSENSITIVE);

	@Override
	public String getTitle() {
		return TITLE;
	}

	@Override
	public Optional<Result> process(String text) {
		String decoded = URLDecoder.decode(text, StandardCharsets.UTF_8);

		if (decoded.equals(text)) {
			return Optional.empty();
		}

		int probability = ENCODED_URL_PATTERN.matcher(text).find() ? 100 : 0;

		return Optional.of(new Result(TITLE, decoded, probability));
	}
}