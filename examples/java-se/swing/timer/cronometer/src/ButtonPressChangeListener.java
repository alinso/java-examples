/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import javax.swing.AbstractButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public abstract class ButtonPressChangeListener implements ChangeListener {
	private boolean pressed;

	public final void stateChanged(ChangeEvent e) {
		AbstractButton button = (AbstractButton) e.getSource();
		boolean currentPressed = button.getModel().isPressed();

		if (currentPressed != pressed) {
			// The button has been pressed or released.
			pressed = currentPressed;

			if (pressed) {
				buttonPressed(e);
			}
		}
	}

	// This method must be implemented in a subclass.
	protected abstract void buttonPressed(ChangeEvent e);
}