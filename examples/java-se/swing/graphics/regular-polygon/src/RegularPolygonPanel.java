/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import javax.swing.JPanel;

/*
 * This class is a Swing panel that draws a "regular polygon".
 *
 * For mathematical details about regular polygons, please see:
 *
 * http://en.wikipedia.org/wiki/Regular_polygon
 * http://en.wikipedia.org/wiki/Circumscribed_circle
 */
public class RegularPolygonPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public static final int MIN_SIDES_COUNT = 3;

	private int sidesCount;
	private boolean showCircumscribedCircle;
	private Stroke circleStroke;
	private Stroke polygonStroke;

	public RegularPolygonPanel() {
		setBackground(Color.WHITE);
		setOpaque(true);
		sidesCount = MIN_SIDES_COUNT;
		circleStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
		polygonStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
	}

	public int getSidesCount() {
		return sidesCount;
	}

	public void setSidesCount(int sidesCount) {
		// Precondition: a regular polygon cannot have less than 3 sides.
		if (sidesCount < MIN_SIDES_COUNT) {
			throw new IllegalArgumentException("Illegal number of sides for a regular polygon");
		}

		// Updates and repaints only if the sidesCount is really changed.
		if (this.sidesCount != sidesCount) {
			this.sidesCount = sidesCount;
			repaint();   // Asks a repaint for the entire panel.
		}
	}

	public boolean isShowCircumscribedCircle() {
		return showCircumscribedCircle;
	}

	public void setShowCircumscribedCircle(boolean showCircumscribedCircle) {
		// Updates and repaints only if the showCircumscribedCircle is really changed.
		if (this.showCircumscribedCircle != showCircumscribedCircle) {
			this.showCircumscribedCircle = showCircumscribedCircle;
			repaint();   // Asks a repaint for the entire panel.
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Gets the physical width/height of the panel.
		int panelWidth = getWidth();
		int panelHeight = getHeight();

		// Calculates the square "box" that contains the regular polygon.
		int boxSize = Math.min(panelWidth, panelHeight);

		// Calculates the circle diameter a bit smaller.
		double circleDiameter = boxSize * 0.9;

		// Calculates the circle radius.
		double circleRadius = circleDiameter / 2;

		// Calculates the center of the panel.
		double centerX = panelWidth / 2.0;
		double centerY = panelHeight / 2.0;

		double radiansBase;

		int sides = getSidesCount();

		if (sides % 2 == 1) {
			// The number of sides is odd (3,5,7,etc...), the first vertex is at the top.
			radiansBase = -Math.PI / 2;
		} else {
			// The number of sides is even (4,6,8,etc...)
			radiansBase = -Math.PI / 2 + Math.PI / sides;
		}

		// Creates the polygon path.
		Path2D.Double polygonPath = new Path2D.Double();

		for (int i = 0; i < sides; i++) {
			double radians = radiansBase + i * 2 * Math.PI / sides;

			double x = centerX + circleRadius * Math.cos(radians);
			double y = centerY + circleRadius * Math.sin(radians);

			if (i == 0) {
				polygonPath.moveTo(x, y);
			} else {
				polygonPath.lineTo(x, y);
			}
		}

		polygonPath.closePath();

		Graphics2D g2d = (Graphics2D) g;

		// Activates the "anti-aliasing" feature for a good quality.
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (isShowCircumscribedCircle()) {
			// Draws the circumscribed circle.
			Ellipse2D.Double circle = new Ellipse2D.Double(centerX - circleRadius,
					centerY - circleRadius, circleDiameter, circleDiameter);
			g2d.setColor(new Color(160, 160, 160));
			g2d.setStroke(circleStroke);
			g2d.draw(circle);
		}

		// Fills the path of the regular polygon.
		g2d.setColor(Color.YELLOW);
		g2d.fill(polygonPath);

		// Strokes the path of the regular polygon.
		g2d.setColor(Color.RED);
		g2d.setStroke(polygonStroke);
		g2d.draw(polygonPath);
	}
}