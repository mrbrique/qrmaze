package net.ddns.lnhc.qrmaze;

import org.junit.Test;

public class QrMazeBuilderTest {

	@Test
	public void test() {
		String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
		QrMazeBuilder.main(new String[] { lorem });

		QrMazeBuilder.main(new String[] { "-v", "7", lorem });

		QrMazeBuilder.main(new String[] { "-c", "H", lorem });

		QrMazeBuilder.main(new String[] { "-v", "12", "-c", "H", lorem });
	}

}
