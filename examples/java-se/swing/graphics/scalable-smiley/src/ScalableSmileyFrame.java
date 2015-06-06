/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class ScalableSmileyFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public ScalableSmileyFrame() {
		super("Scalable Smiley - Java Example by andbin");

		ScalableSmileyPanel scalableSmileyPanel = new ScalableSmileyPanel();
		scalableSmileyPanel.setOpaque(true);
		scalableSmileyPanel.setBackground(Color.WHITE);
		scalableSmileyPanel.setToolTipText("Resize the frame, the smiley will scale!");

		getContentPane().add(scalableSmileyPanel, BorderLayout.CENTER);

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}
}