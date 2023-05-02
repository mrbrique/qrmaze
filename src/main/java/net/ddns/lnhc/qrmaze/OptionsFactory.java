package net.ddns.lnhc.qrmaze;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class OptionsFactory {

	public static Options buildOptions() {
		Options options = new Options();

		options.addOption(Option.builder(Arguments.VERSION.getOpt()).desc("suggested QR code version ").hasArg()
				.argName("VERSION").build());

		options.addOption(Option.builder(Arguments.CORRECTION_LEVEL.getOpt())
				.desc("error correction level: L/M/Q/H").hasArg().argName("LEVEL").build());

		return options;
	}

}
