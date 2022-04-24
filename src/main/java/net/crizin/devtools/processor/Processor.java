package net.crizin.devtools.processor;

public interface ProcessorInterface {

	boolean canProcess(String text);

	String process(String text);
}