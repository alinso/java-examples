/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import javax.swing.JButton;

/*
 * KeyButton is a JButton with one or more KeyAction objects.
 */
public class KeyButton extends JButton {
	private static final long serialVersionUID = 1L;

	private KeyAction[] keyActions;

	public KeyButton(String text, KeyAction keyAction) {
		super(text);
		keyActions = new KeyAction[] { keyAction };
	}

	public KeyButton(String text, KeyAction[] keyActions) {
		super(text);
		this.keyActions = keyActions.clone();
	}

	public KeyAction[] getKeyActions() {
		return keyActions.clone();
	}
}