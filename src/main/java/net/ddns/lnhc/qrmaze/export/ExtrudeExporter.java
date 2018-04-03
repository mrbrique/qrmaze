package net.ddns.lnhc.qrmaze.export;

import com.google.zxing.common.BitMatrix;

import net.ddns.lnhc.qrmaze.QrMazeBuilder;
import net.ddns.lnhc.qrmaze.QrMazeContextType;

public class ExtrudeExporter extends QrMazeExporter {

	@Override
	public void export(BitMatrix matrix) {
		int[] rect = matrix.getEnclosingRectangle();
		int width, height;
		width = rect[2];
		height = rect[3];

		int edgeWidth = (width + height) / 2;
		System.out.println("Edge: " + edgeWidth);
		getContext().put(QrMazeContextType.DIMENSION, edgeWidth);
	}

}
