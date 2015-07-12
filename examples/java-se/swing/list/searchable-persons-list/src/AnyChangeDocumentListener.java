/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public abstract class AnyChangeDocumentListener implements DocumentListener {
	public void insertUpdate(DocumentEvent e) {
		documentChanged(e);
	}

	public void removeUpdate(DocumentEvent e) {
		documentChanged(e);
	}

	public void changedUpdate(DocumentEvent e) {
		documentChanged(e);
	}

	protected abstract void documentChanged(DocumentEvent e);
}