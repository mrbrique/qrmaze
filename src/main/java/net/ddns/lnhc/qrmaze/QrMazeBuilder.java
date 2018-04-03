package net.ddns.lnhc.qrmaze;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.chain.Catalog;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.config.ConfigParser;
import org.apache.commons.chain.impl.CatalogFactoryBase;
import org.apache.commons.chain.impl.ContextBase;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import net.ddns.lnhc.qrmaze.export.AutocadScrExporter;
import net.ddns.lnhc.qrmaze.export.ConsoleExporter;
import net.ddns.lnhc.qrmaze.export.ExtrudeExporter;
import net.ddns.lnhc.qrmaze.export.PngExporter;
import net.ddns.lnhc.qrmaze.export.QrMazeExporter;

/**
 * Hello world!
 *
 */
public class QrMazeBuilder {

	private int DIMENSION = 1;

	private Command exporterChain;

	public static void main(String[] args) {

		CommandLine cmd = null;
		try {
			cmd = new DefaultParser().parse(OptionsFactory.buildOptions(), args, false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String qrVersion = cmd.getOptionValue(OptionsFactory.ARGUMENT_VERSION);
		new QrMazeBuilder().build(cmd.getArgs()[0], (qrVersion == null) ? -1 : Integer.parseInt(qrVersion));
	}

	public QrMazeBuilder() {

		ConfigParser parser = new ConfigParser();
		try {

			parser.parse(getClass().getClassLoader().getResource("exporter-chain.xml"));
			Catalog cata = CatalogFactoryBase.getInstance().getCatalog();
			exporterChain = cata.getCommand("exporterChain");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void build(String s, final int qrVersion) {
		QRCodeWriter writer = new QRCodeWriter();
		Map<EncodeHintType, Object> hintMap = new HashMap<EncodeHintType, Object>() {
			{
				put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
				if (qrVersion > 0) {
					put(EncodeHintType.QR_VERSION, qrVersion);
				}

			}
		};
		try {
			BitMatrix matrix = writer.encode(s, BarcodeFormat.QR_CODE, DIMENSION, DIMENSION, hintMap);
			exporterChain.execute(createChainContext(matrix));
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Context createChainContext(BitMatrix matrix) {
		// TODO Auto-generated method stub
		Context context = new ContextBase();
		context.put(QrMazeContextType.BITMATRIX, matrix);
		return context;
	}

}
