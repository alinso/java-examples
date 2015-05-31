/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class RainbowShadesFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public RainbowShadesFrame() {
		super("Rainbow Shades - Java Example by andbin");

		// Setups the panel.
		RainbowShadesPanel rainbowShadesPanel = new RainbowShadesPanel();

		getContentPane().add(rainbowShadesPanel, BorderLayout.CENTER);

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}
}