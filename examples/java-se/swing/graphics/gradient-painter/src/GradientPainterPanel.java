/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/*
 * This class is a Swing panel that draws a "linear gradient" using the
 * java.awt.GradientPaint class.
 */
public class GradientPainterPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Color firstColor;
	private Color secondColor;
	private GradientOrientation orientation;

	public GradientPainterPanel(Color firstColor, Color secondColor) {
		setFirstColor(firstColor);
		setSecondColor(secondColor);
		orientation = GradientOrientation.HORIZONTAL;
	}

	public Color getFirstColor() {
		return firstColor;
	}

	public void setFirstColor(Color firstColor) {
		if (firstColor == null) {
			throw new IllegalArgumentException("firstColor cannot be null");
		}

		// Updates and repaints only if the firstColor is really changed.
		if (this.firstColor == null || !this.firstColor.equals(firstColor)) {
			this.firstColor = firstColor;
			repaint();   // Asks a repaint for the entire panel.
		}
	}

	public Color getSecondColor() {
		return secondColor;
	}

	public void setSecondColor(Color secondColor) {
		if (secondColor == null) {
			throw new IllegalArgumentException("secondColor cannot be null");
		}

		// Updates and repaints only if the secondColor is really changed.
		if (this.secondColor == null || !this.secondColor.equals(secondColor)) {
			this.secondColor = secondColor;
			repaint();   // Asks a repaint for the entire panel.
		}
	}

	public GradientOrientation getOrientation() {
		return orientation;
	}

	public void setOrientation(GradientOrientation orientation) {
		if (orientation == null) {
			throw new IllegalArgumentException("orientation cannot be null");
		}

		// Updates and repaints only if the orientation is really changed.
		if (this.orientation != orientation) {
			this.orientation = orientation;
			repaint();   // Asks a repaint for the entire panel.
		}
	}

	protected void paintComponent(Graphics g) {
		// The typical super.paintComponent(g) invocation is *not* necessary
		// in this case, because the following code fills the entire surface
		// of the panel.

		Graphics2D g2d = (Graphics2D) g;

		// Gets the physical width/height of the panel.
		int panelWidth = getWidth();
		int panelHeight = getHeight();

		// Creates and sets the gradient.
		float x2;
		float y2;

		switch (orientation) {
		default:
		case HORIZONTAL:
			x2 = panelWidth;
			y2 = 0;
			break;
		case VERTICAL:
			x2 = 0;
			y2 = panelHeight;
			break;
		}

		GradientPaint gp = new GradientPaint(0, 0, firstColor, x2, y2, secondColor);

		g2d.setPaint(gp);

		// Fills the entire panel with the gradient.
		g2d.fillRect(0, 0, panelWidth, panelHeight);
	}
}