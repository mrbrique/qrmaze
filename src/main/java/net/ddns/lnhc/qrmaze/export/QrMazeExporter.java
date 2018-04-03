package net.ddns.lnhc.qrmaze.export;

import java.io.File;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

import com.google.zxing.common.BitMatrix;

import net.ddns.lnhc.qrmaze.QrMazeBuilder;
import net.ddns.lnhc.qrmaze.QrMazeContextType;

public abstract class QrMazeExporter implements Command {

	public abstract void export(BitMatrix matrix);

	private Context context;

	public boolean execute(Context arg) throws Exception {
		this.setContext(arg);
		this.export((BitMatrix) arg.get(QrMazeContextType.BITMATRIX));
		return false;
	}

	public File getOutputFolder() {
		File output = new File("output");
		if (!output.exists()) {
			output.mkdirs();
		}
		return output;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

}
