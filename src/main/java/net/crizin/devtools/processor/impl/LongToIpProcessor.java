package net.crizin.devtools.processor.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;
import net.crizin.devtools.processor.Processor;
import net.crizin.devtools.processor.Result;
import org.springframework.stereotype.Component;

@Component
public class LongToIpProcessor implements Processor {

	private static final String TITLE = "Long value to IP";
	private static final String SORT_KEY = "90.ip2long";

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
		long value;

		try {
			value = Long.parseLong(text);
		} catch (Exception e) {
			return Optional.empty();
		}

		if (value < 0) {
			return Optional.empty();
		}

		InetAddress inetAddress;

		try {
			inetAddress = InetAddress.getByName(text);
		} catch (UnknownHostException e) {
			return Optional.empty();
		}

		return Optional.of(new Result(TITLE, inetAddress.getHostAddress(), false));
	}
}