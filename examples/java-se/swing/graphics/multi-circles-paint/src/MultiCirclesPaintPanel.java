/*
 * Copyright (C) 2016-2017 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class MultiCirclesPaintPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private List<CircleElement> circleElements;

	public MultiCirclesPaintPanel() {
		circleElements = new ArrayList<CircleElement>();
	}

	public void addCircleElement(CircleElement circleElement) {
		circleElements.add(circleElement);

		// Asks a repaint for only the "bounding box" (rectangle) of the circle.
		// This is just a simple and quick optimization.
		repaint(circleElement.getBoundingBox());
	}

	public void removeAllCircleElements() {
		circleElements.clear();
		repaint();   // Asks a repaint for the entire panel.
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		// Activates the "anti-aliasing" feature for a good quality.
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Paints all the circles from the first (bottommost) to the last (topmost).
		for (CircleElement circleElement : circleElements) {
			int x = circleElement.getLeftX();
			int y = circleElement.getTopY();
			int d = circleElement.getDiameter();

			g.setColor(circleElement.getFillColor());
			g.fillOval(x, y, d, d);
		}
	}
}