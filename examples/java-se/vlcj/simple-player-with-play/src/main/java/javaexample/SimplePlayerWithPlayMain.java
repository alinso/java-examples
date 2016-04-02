/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

package javaexample;

import com.sun.jna.NativeLibrary;
import javax.swing.SwingUtilities;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

/*
 * This is the "main" class of this Java Example.
 *
 * The SwingUtilities.invokeLater causes the run() to be executed asynchronously
 * in the context of the EDT (Event Dispatch Thread).
 *
 * It is generally always a good practice to start all the GUI creation on the
 * EDT and *not* on the "main" thread.
 */
public class SimplePlayerWithPlayMain {
	public static void main(String[] args) {
		if (args.length == 1) {
			NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), args[0]);
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new SimplePlayerWithPlayFrame().setVisible(true);
				} catch (RuntimeException e) {
					System.err.println(e);
					GUIUtils.showTextAreaErrorDialog(null, e, "ERROR");
				}
			}
		});
	}
}