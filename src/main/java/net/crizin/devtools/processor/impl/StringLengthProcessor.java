package net.crizin.devtools.processor.impl;

import com.ibm.icu.text.BreakIterator;
import java.util.Optional;
import net.crizin.devtools.processor.Processor;
import net.crizin.devtools.processor.Result;
import org.springframework.stereotype.Component;

@Component
public class StringLengthProcessor implements Processor {

	private static final String TITLE = "String length";
	private static final String SORT_KEY = "10.string.length";

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
		if (text.isEmpty()) {
			return Optional.empty();
		}

		long byteLength = text.getBytes().length;
		long stringLength = text.length();
		long codePointLength = text.codePoints().count();
		long graphemeLength = getGraphemeLength(text);

		String result = String.format("Bytes: %d, String length: %d, Code Points: %d, Grapheme: %d", byteLength, stringLength, codePointLength, graphemeLength);

		return Optional.of(new Result(TITLE, result, codePointLength != graphemeLength));
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