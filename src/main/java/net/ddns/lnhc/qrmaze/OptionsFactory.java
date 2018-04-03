package net.ddns.lnhc.qrmaze;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class OptionsFactory {

	public static final String ARGUMENT_VERSION = "v";

	public static Options buildOptions() {
		Options options = new Options();

		options.addOption(Option.builder(ARGUMENT_VERSION).desc("suggested QR code version ").hasArg()
				.argName("VERSION").build());

		return options;
	}

}
