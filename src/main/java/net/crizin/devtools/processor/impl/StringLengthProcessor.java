package net.crizin.devtools.processor;

import com.ibm.icu.text.BreakIterator;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class StringLengthProcessor implements Processor {

	private static final String TITLE = "Check length of text";

	@Override
	public String getTitle() {
		return TITLE;
	}

	@Override
	public Optional<Result> process(String text) {
		if (text.isEmpty()) {
			return Optional.empty();
		}

		long byteLength = text.getBytes().length;
		long stringLength = text.length();
		long codePointLength = text.codePoints().count();
		long graphemeLength = getGraphemeLength(text);

		String result = String.format("Bytes: %d, String: %d, Code Points: %d, Grapheme: %d", byteLength, stringLength, codePointLength, graphemeLength);
		int probability = (codePointLength == graphemeLength) ? 0 : 50;

		return Optional.of(new Result(TITLE, result, probability));
	}

	private int getGraphemeLength(String text) {
		BreakIterator it = BreakIterator.getCharacterInstance();
		it.setText(text);
		int count = 0;
		while (it.next() != BreakIterator.DONE) {
			count++;
		}
		return count;
	}
}