/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import java.awt.Graphics;
import javax.swing.JPanel;

public class AlphaColorPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public AlphaColorPanel() {
		// The panel must be non-opaque in order for this technique to work.
		setOpaque(false);

		// Sets a BorderLayout so that this panel is initially similar to the
		// content pane of JFrame/JDialog/etc...
		setLayout(new BorderLayout());
	}

	@Override
	protected void paintComponent(Graphics g) {
		// Fills the panel with the color that can even be semi-transparent.
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}