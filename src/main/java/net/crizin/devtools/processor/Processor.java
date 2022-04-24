package net.crizin.devtools.processor;

import java.util.Optional;

public interface Processor {

	String getTitle();

	String getSortKey();

	Optional<Result> process(String text);
}