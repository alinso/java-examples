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
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;

public class ShapesHoverEffectPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private List<ShapeElement> shapeElements;
	private ShapeElement selectedShapeElement;
	private Stroke selectionStroke;

	public ShapesHoverEffectPanel() {
		shapeElements = new ArrayList<ShapeElement>();
		selectedShapeElement = null;
		selectionStroke = new BasicStroke(1, BasicStroke.CAP_SQUARE,
				BasicStroke.JOIN_MITER, 10.0f, new float[] { 1, 2 }, 0);

		// This panel manages many tooltips at different locations with the
		// override of the getToolTipText(MouseEvent) method.
		// Since we are not using the basic/classic setToolTipText, it is
		// necessary to register this component for the tooltip management in
		// an explicit way.
		ToolTipManager.sharedInstance().registerComponent(this);

		// Registers the necessary listeners.
		ListenersImpl listenersImpl = new ListenersImpl();

		addMouseListener(listenersImpl);
		addMouseMotionListener(listenersImpl);
	}

	public void addShapeElement(ShapeElement shapeElement) {
		shapeElements.add(shapeElement);
		Rectangle rect = shapeElement.getShape().getBounds();
		// Asks a repaint only for the bounding rectangle of the shape.
		repaint(rect);
	}

	// By overriding this method it is possible to manage many tooltips at
	// different locations.
	// This method is invoked by Swing when it is the moment to show a tooltip.
	// Using the mouse point you can return a tooltip text appropriate for
	// that point.
	@Override
	public String getToolTipText(MouseEvent e) {
		Point mousePoint = e.getPoint();
		ShapeElement shapeElement = findShapeElementByPoint(mousePoint);

		if (shapeElement != null) {
			return shapeElement.getTooltipText();
		} else {
			return null;   // No tooltip.
		}
	}

	// By overriding this method it is possible to control the physical location
	// of the tooltip.
	// This method is invoked by Swing when it is the moment to show a tooltip.
	// Using the mouse point you can return a tooltip location appropriate for
	// that point.
	@Override
	public Point getToolTipLocation(MouseEvent e) {
		Point mousePoint = e.getPoint();
		ShapeElement shapeElement = findShapeElementByPoint(mousePoint);

		if (shapeElement != null) {
			Rectangle r = shapeElement.getShape().getBounds();
			return new Point(r.x - 2, r.y + r.height + 5);
		} else {
			return null;   // Tooltip location is choosen by Swing.
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		// Activates the "anti-aliasing" feature for a good quality.
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Paints all shapes from the first (bottommost) to the last (topmost).
		for (int i = 0; i < shapeElements.size(); i++) {
			ShapeElement currentShapeElement = shapeElements.get(i);

			// Draws the current shape using its specific fill color.
			g2d.setColor(currentShapeElement.getFillColor());
			g2d.fill(currentShapeElement.getShape());
		}

		// Deactivates the "anti-aliasing".
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

		// If there is a selected shape element, draws a "selection" rectangle.
		if (selectedShapeElement != null) {
			Rectangle r = selectedShapeElement.getShape().getBounds();
			r.grow(2, 2);

			g2d.setColor(Color.BLACK);
			g2d.setStroke(selectionStroke);
			g2d.drawRect(r.x, r.y, r.width-1, r.height-1);
		}
	}

	private ShapeElement findShapeElementByPoint(Point pt) {
		// Shapes must be searched from the topmost to the bottommost.
		// For this reason, the iteration must start from the end of the list,
		// because the last shape painted is at the "top".
		for (int i = shapeElements.size() - 1; i >= 0; i--) {
			ShapeElement currentShapeElement = shapeElements.get(i);

			if (currentShapeElement.getShape().contains(pt)) {
				// The point is on this shape element.
				return currentShapeElement;
			}
		}

		return null;   // The point is not on a shape element.
	}

	private void setSelectedShapeElement(ShapeElement newSelectedShapeElement) {
		ShapeElement oldSelectedShapeElement = selectedShapeElement;

		// Changes the selected shape element only if it is really changed.
		if (newSelectedShapeElement != oldSelectedShapeElement) {
			selectedShapeElement = newSelectedShapeElement;

			if (oldSelectedShapeElement != null) {
				Rectangle rect = oldSelectedShapeElement.getShape().getBounds();
				rect.grow(2, 2);
				repaint(rect);
			}

			if (newSelectedShapeElement != null) {
				Rectangle rect = newSelectedShapeElement.getShape().getBounds();
				rect.grow(2, 2);
				repaint(rect);
			}
		}
	}


	private class ListenersImpl implements MouseListener, MouseMotionListener {
		// Methods for MouseListener interface.

		public void mouseClicked(MouseEvent e) { }
		public void mousePressed(MouseEvent e) { }
		public void mouseReleased(MouseEvent e) { }
		public void mouseEntered(MouseEvent e) { }

		// The mouseExited handles a special/corner case. The mouse cursor may
		// be moved quickly outside the panel without causing a mouseMoved
		// outside a shape.
		// The selected shape element must be nulled, otherwise the shape
		// would remain in the selected state.
		public void mouseExited(MouseEvent e) {
			setSelectedShapeElement(null);   // Clears selection.
		}

		// Methods for the MouseMotionListener interface.

		public void mouseDragged(MouseEvent e) { }

		public void mouseMoved(MouseEvent e) {
			Point mousePoint = e.getPoint();
			ShapeElement shapeElement = findShapeElementByPoint(mousePoint);
			setSelectedShapeElement(shapeElement);
		}
	}
}