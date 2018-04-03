package net.ddns.lnhc.qrmaze.export;

import com.google.zxing.common.BitMatrix;

public class ConsoleExporter extends QrMazeExporter {

	@Override
	public void export(BitMatrix matrix) {
		for (int w = 0; w < matrix.getWidth(); w++) {
			for (int h = 0; h < matrix.getHeight(); h++) {
				System.out.print(matrix.get(w, h) ? "X" : " ");

			}
			System.out.println();
		}

	}

}
