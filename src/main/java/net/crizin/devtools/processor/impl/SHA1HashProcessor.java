package net.crizin.devtools.processor;

import java.util.Optional;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class SHA1HashProcessor implements Processor {

	private static final String TITLE = "SHA-1 Hash";

	@Override
	public String getTitle() {
		return TITLE;
	}

	@Override
	public Optional<Result> process(String text) {
		if (text.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(new Result(TITLE, DigestUtils.sha1Hex(text), 0));
	}
}