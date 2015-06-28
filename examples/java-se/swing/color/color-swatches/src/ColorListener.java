/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.util.EventListener;

/*
 * This is an interface to define a custom listener for color changes.
 *
 * Technically, it is not mandatory to extend EventListener. However, it is a
 * good practice and it is also required if you want to use the
 * javax.swing.event.EventListenerList class (please, see how ColorListener
 * objects are managed in ColorSwatchesPanel).
 */
public interface ColorListener extends EventListener {
	void colorChanged(ColorChangeEvent e);
}