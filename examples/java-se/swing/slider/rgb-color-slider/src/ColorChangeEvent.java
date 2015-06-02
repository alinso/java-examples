/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.Color;
import java.util.EventObject;

/*
 * This class is the event which indicates a change of a color. Technically,
 * it is not mandatory to extend EventObject, however it is very common and
 * useful because EventObject already has the concept of "source" of the event.
 */
public class ColorChangeEvent extends EventObject {
	private static final long serialVersionUID = 1L;

	private Color color;

	public ColorChangeEvent(Object source, Color color) {
		super(source);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
}