package net.ddns.lnhc.qrmaze;

public enum Arguments {

	VERSION("v"), CORRECTION_LEVEL("c");

	private final String opt;

	public String getOpt() {
		return opt;
	}

	Arguments(String s) {
		this.opt = s;
	}

}
