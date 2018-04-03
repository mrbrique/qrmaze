package net.ddns.lnhc.qrmaze.export;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

import com.google.zxing.common.BitMatrix;

public class AutocadScrExporter extends QrMazeExporter {

	@Override
	public void export(BitMatrix matrix) {
		StringBuilder str = new StringBuilder();
		for (int w = 0; w < matrix.getWidth(); w++) {
			for (int h = 0; h < matrix.getHeight(); h++) {
				if (matrix.get(w, h)) {
					str.append(String.format("RECTANG %s,%s %s,%s\n", w, h, (w + 1), (h + 1)));
				}
			}
		}
		write(str);
	}

	private void write(StringBuilder str) {

		try {
			PrintWriter writer = new PrintWriter(
					new BufferedOutputStream(new FileOutputStream(new File(getOutputFolder(), "qrmaze.scr"))));
			writer.print(str.toString());
			writer.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
