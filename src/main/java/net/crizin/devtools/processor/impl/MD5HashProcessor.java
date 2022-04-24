package net.crizin.devtools.processor;

import java.util.Optional;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class MD5HashProcessor implements Processor {

	private static final String TITLE = "MD5 Hash";

	@Override
	public String getTitle() {
		return TITLE;
	}

	@Override
	public Optional<Result> process(String text) {
		if (text.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(new Result(TITLE, DigestUtils.md5Hex(text), 0));
	}
}