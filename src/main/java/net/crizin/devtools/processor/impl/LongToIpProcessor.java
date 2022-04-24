package net.crizin.devtools.processor.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Optional;
import net.crizin.devtools.processor.Processor;
import net.crizin.devtools.processor.Result;
import org.springframework.stereotype.Component;

@Component
public class IpToLongProcessor implements Processor {

	private static final String TITLE = "IP to Long value";
	private static final String SORT_KEY = "10.ip2long";

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