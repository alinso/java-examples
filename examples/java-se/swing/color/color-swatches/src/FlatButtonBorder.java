/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.AbstractButton;
import javax.swing.border.AbstractBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

// This class is a custom implementation of the Border interface (the extension
// of AbstractBorder is only for convenience). The realized border is a simple
// "flat" border that shows a raised/lowered border only for rollover/pressed state.
//
// NOTE: This border can be applied ONLY to buttons (subclasses of AbstractButton),
// not to other Swing components.

public class FlatButtonBorder extends AbstractBorder {
	private static final long serialVersionUID = 1L;

	// The margin between the border and the content of the button.
	private int margin;

	// The Border used for the "rollover" state (when mouse is over the button).
	private Border rolloverBorder;

	// The Border used for the "pressed" state.
	private Border pressedBorder;

	public FlatButtonBorder(int margin) {
		this.margin = margin;
		rolloverBorder = new BevelBorder(BevelBorder.RAISED);
		pressedBorder = new BevelBorder(BevelBorder.LOWERED);
	}

	// Overrides paintBorder in AbstractBorder.
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		AbstractButton button = (AbstractButton) c;   // The component must be a button!

		if (button.getModel().isPressed()) {
			pressedBorder.paintBorder(c, g, x, y, width, height);
		} else if (button.getModel().isRollover()) {
			rolloverBorder.paintBorder(c, g, x, y, width, height);
		}
	}

	// Overrides getBorderInsets in AbstractBorder.
	public Insets getBorderInsets(Component c) {
		return getBorderInsets(c, new Insets(0, 0, 0, 0));
	}

	// Overrides getBorderInsets in AbstractBorder.
	public Insets getBorderInsets(Component c, Insets insets) {
		Insets i = rolloverBorder.getBorderInsets(c);
		insets.set(i.top+margin, i.left+margin, i.bottom+margin, i.right+margin);
		return insets;
	}

	// Overrides isBorderOpaque in AbstractBorder.
	public boolean isBorderOpaque() {
		// This border is non-opaque because pressedBorder/rolloverBorder is
		// not drawn for the normal state.
		return false;
	}
}