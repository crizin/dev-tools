package net.crizin.devtools.processor.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Optional;
import java.util.regex.Pattern;
import net.crizin.devtools.processor.Processor;
import net.crizin.devtools.processor.Result;
import org.springframework.stereotype.Component;

@Component
public class IpToLongProcessor implements Processor {

	private static final String TITLE = "IP to Long value";
	private static final String SORT_KEY = "90.ip2long";
	private static final Pattern IP_PATTERN = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");

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
		if (!IP_PATTERN.matcher(text.trim()).matches()) {
			return Optional.empty();
		}

		InetAddress inetAddress;

		try {
			inetAddress = InetAddress.getByName(text.trim());
		} catch (UnknownHostException e) {
			return Optional.empty();
		}

		int result = ByteBuffer.wrap(inetAddress.getAddress()).getInt();

		return Optional.of(new Result(TITLE, String.valueOf(result), true));
	}
}