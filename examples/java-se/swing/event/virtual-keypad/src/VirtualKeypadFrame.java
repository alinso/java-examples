/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class VirtualKeypadFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private KeyActionExecutor keyActionExecutor;
	private VirtualKeypadPanel virtualKeypadPanel;

	public VirtualKeypadFrame() {
		super("Virtual Keypad - Java Example by andbin");

		keyActionExecutor = new RobotKeyActionExecutor();
		virtualKeypadPanel = new VirtualKeypadPanel(keyActionExecutor);

		getContentPane().add(virtualKeypadPanel, BorderLayout.CENTER);

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);   // Centers the frame on the screen.

		// IMPORTANT: avoids that this window steals the focus to other windows.
		setFocusableWindowState(false);

		// IMPORTANT: makes this window "always on top". This is less important
		// but still useful. Note: some platforms might not support always-on-top
		// windows.
		setAlwaysOnTop(true);
	}
}