package net.ddns.lnhc.qrmaze.export;

import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import org.jfree.graphics2d.svg.SVGGraphics2D;

import com.google.zxing.common.BitMatrix;

public class SvgExporter extends QrMazeExporter {

	@Override
	public void export(BitMatrix matrix) {

		// Graphics2D preparation
		SVGGraphics2D g2d = new SVGGraphics2D(matrix.getWidth(), matrix.getHeight());
		g2d.setColor(Color.BLACK);

		for (int w = 0; w < matrix.getWidth(); w++) {
			for (int h = 0; h < matrix.getHeight(); h++) {
				if (matrix.get(w, h)) {
					// draw a dot
					g2d.fillRect(w, h, 1, 1);
				}
			}
		}

		write(g2d);

	}

	private void write(SVGGraphics2D g2d) {
		String svg = g2d.getSVGDocument();

		try {
			PrintWriter writer = new PrintWriter(
					new BufferedOutputStream(new FileOutputStream(new File(getOutputFolder(), "qrmaze.svg"))));
			writer.print(svg);
			writer.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
