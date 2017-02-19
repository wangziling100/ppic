package util.common;

import org.apache.commons.cli.Option;

public class CommonOpts{
	
	// common options in command line
	static public Option input = Option.builder("i")
			.longOpt("input")
			.argName("file")
			.hasArg()
			.desc("Path to input file")
			.build();
	static public Option output = Option.builder("o")
			.longOpt("output")
			.argName("file")
			.hasArg()
			.desc("File name for output file")
			.build();
	static public Option help = new Option("h", "help", false, "print this message");
	static public Option debug = new Option("debug", true, "print debugging information");
}