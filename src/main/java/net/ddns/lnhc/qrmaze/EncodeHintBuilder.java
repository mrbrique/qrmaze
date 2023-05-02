package net.ddns.lnhc.qrmaze;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

import org.apache.commons.cli.CommandLine;

import com.google.zxing.EncodeHintType;

public class EncodeHintBuilder {

	private HashMap<EncodeHintType, Object> hintMap;

	public EncodeHintBuilder() {
		hintMap = new HashMap<EncodeHintType, Object>();
	}

	public EncodeHintBuilder(CommandLine cmd) {
		this();

		agrument(cmd, Arguments.VERSION).map(Integer::parseInt).filter(v -> v > 0)
				.ifPresent(arg -> setQrVersion(arg));
		agrument(cmd, Arguments.CORRECTION_LEVEL).filter(arg -> Arrays.asList("L", "M", "Q", "H").contains(arg))
				.ifPresent(arg -> setErrorCorrection(arg));
	}

	public EncodeHintBuilder setAztecLayers(Object o) {
		hintMap.put(EncodeHintType.AZTEC_LAYERS, o);
		return this;
	}

	public EncodeHintBuilder setCharacterSet(Object o) {
		hintMap.put(EncodeHintType.CHARACTER_SET, o);
		return this;
	}

	public EncodeHintBuilder setDataMatrixShape(Object o) {
		hintMap.put(EncodeHintType.DATA_MATRIX_SHAPE, o);
		return this;
	}

	public EncodeHintBuilder setErrorCorrection(Object o) {
		hintMap.put(EncodeHintType.ERROR_CORRECTION, o);
		return this;
	}

	public EncodeHintBuilder setMargin(Object o) {
		hintMap.put(EncodeHintType.MARGIN, o);
		return this;
	}

	public EncodeHintBuilder setQrVersion(Object o) {
		hintMap.put(EncodeHintType.QR_VERSION, o);
		return this;
	}

	public EncodeHintBuilder setPdf417Compact(Object o) {
		hintMap.put(EncodeHintType.PDF417_COMPACTION, o);
		return this;
	}

	public EncodeHintBuilder setPdf417Compaction(Object o) {
		hintMap.put(EncodeHintType.PDF417_COMPACTION, o);
		return this;
	}

	public EncodeHintBuilder setPdf417Dimensions(Object o) {
		hintMap.put(EncodeHintType.PDF417_DIMENSIONS, o);
		return this;
	}

	public HashMap<EncodeHintType, Object> build() {
		return hintMap;
	}

	private Optional<String> agrument(CommandLine cmd, Arguments arg) {
		return Optional.ofNullable(cmd.getOptionValue(arg.getOpt()));
	}

}
