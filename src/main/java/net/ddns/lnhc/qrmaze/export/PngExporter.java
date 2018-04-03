package net.ddns.lnhc.qrmaze.export;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class PngExporter extends QrMazeExporter {

	@Override
	public void export(BitMatrix matrix) {
		try {
			BufferedImage img = MatrixToImageWriter.toBufferedImage(matrix);
			ImageIO.write(img, "png", new File(getOutputFolder(), "qrmaze.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
