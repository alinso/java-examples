/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class RainbowShadesPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	protected void paintComponent(Graphics g) {
		// The typical super.paintComponent(g) invocation is *not* necessary
		// in this case, because the following code fills the entire surface
		// of the panel.

		// Gets the physical width/height of the panel.
		int panelWidth = getWidth();
		int panelHeight = getHeight();

		float xMax = panelWidth - 1;

		float saturation = 1.0f;
		float brightness = 1.0f;

		for (int x = 0; x < panelWidth; x++) {
			// Calculates the hue as the fraction of x on xMax that gives
			// a value in the range [0.0,1.0], both inclusive.
			float hue = x / xMax;   // floating-point division!

			Color color = Color.getHSBColor(hue, saturation, brightness);

			// Draws a vertical line at coordinate x with the calculated color.
			g.setColor(color);
			g.drawLine(x, 0, x, panelHeight-1);
		}
	}
}