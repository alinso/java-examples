/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class MultiCirclesScrollablePaintPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private List<CircleElement> circleElements;
	private int preferredWidth;
	private int preferredHeight;

	public MultiCirclesScrollablePaintPanel() {
		circleElements = new ArrayList<CircleElement>();
	}

	public void addCircleElement(CircleElement circleElement) {
		circleElements.add(circleElement);

		int x = circleElement.getLeftX();
		int y = circleElement.getTopY();
		int d = circleElement.getDiameter();

		// Asks a repaint for only the rectangle of the circle. This is just
		// a simple and quick optimization.
		repaint(x, y, d, d);

		// Updates (if necessary) the "preferred" size of this panel.
		updatePreferredSize(x+d, y+d);
	}

	public void removeAllCircleElement() {
		circleElements.clear();
		repaint();   // Asks a repaint for the entire panel.
		resetPreferredSize();   // Resets the "preferred" size of this panel.
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(preferredWidth, preferredHeight);
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


	private void resetPreferredSize() {
		preferredWidth = 0;
		preferredHeight = 0;
		revalidate();
	}

	private void updatePreferredSize(int askedWidth, int askedHeight) {
		boolean updated = false;

		if (askedWidth > preferredWidth) {
			preferredWidth = askedWidth;
			updated = true;
		}

		if (askedHeight > preferredHeight) {
			preferredHeight = askedHeight;
			updated = true;
		}

		if (updated) {
			// This is the important part of the preferred size handling!
			// revalidate() is different from the validate(). revalidate()
			// finds the first "validateRoot" in the containment hierarchy.
			// JScrollPane IS a validateRoot, thus the re-validation of layout
			// starts from JScrollPane and not just only from this panel.
			revalidate();
		}
	}
}