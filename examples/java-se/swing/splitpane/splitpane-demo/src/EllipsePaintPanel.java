/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

public class EllipsePaintPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		// Activates the "anti-aliasing" feature for a good quality.
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Gets the physical width/height of the panel.
		int panelWidth = getWidth();
		int panelHeight = getHeight();

		// Fills the entire surface using the background color of the component.
		g2d.setColor(getBackground());
		g2d.fillRect(0, 0, panelWidth, panelHeight);

		// Fills an ellipse using the foreground color of the component.
		g2d.setColor(getForeground());
		g2d.fill(new Ellipse2D.Float(1, 1, panelWidth-1, panelHeight-1));
	}
}