package net.ddns.lnhc.qrmaze;

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

		new QrMazeBuilder().build(cmd.getArgs()[0], cmd);
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

	public BitMatrix prepare(String content, Map<EncodeHintType, ?> hintMap) {
		QRCodeWriter writer = new QRCodeWriter();

		try {
			return writer.encode(content, BarcodeFormat.QR_CODE, DIMENSION, DIMENSION, hintMap);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void build(String s, CommandLine cmd) {

		try {
			BitMatrix matrix = prepare(s, new EncodeHintBuilder(cmd).build());
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
