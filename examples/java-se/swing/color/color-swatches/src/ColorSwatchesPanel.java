/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ColorSwatchesPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public ColorSwatchesPanel(Color[] colors, int columns) {
		// Sets a GridLayout with a fixed number of columns and an arbitrary
		// number of rows.
		setLayout(new GridLayout(0, columns));

		// Creates a FlatButtonBorder with 2 pixels margin.
		FlatButtonBorder border = new FlatButtonBorder(2);

		// Creates an ActionListener for all the buttons.
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// We know that the "source" is a JButton that contains a ColorSwatchIcon.
				JButton button = (JButton) e.getSource();
				ColorSwatchIcon colorSwatchIcon = (ColorSwatchIcon) button.getIcon();
				fireColorChanged(colorSwatchIcon.getColor());
			}
		};

		for (int i = 0; i < colors.length; i++) {
			// Creates a JButton that only contains a ColorSwatchIcon.
			ColorSwatchIcon icon = new ColorSwatchIcon(14, 14, colors[i]);
			JButton button = new JButton(icon);

			button.setRolloverEnabled(true);
			button.setFocusPainted(false);
			button.setBorderPainted(true);
			button.setContentAreaFilled(false);
			button.setBorder(border);
			button.addActionListener(actionListener);

			// Adds the button into the GridLayout in this panel.
			add(button);
		}
	}

	// NOTE: javax.swing.JComponent (base of all Swing components) has a field:
	//
	//    protected EventListenerList listenerList = new EventListenerList();
	//
	// Swing components use that special list to manage many listeners, even of
	// different types. This example also uses that list for ColorListener.

	// This is a specific method to add (register) a ColorListener.
	public void addColorListener(ColorListener colorListener) {
		listenerList.add(ColorListener.class, colorListener);
	}

	// This is a specific method to remove (unregister) a ColorListener.
	public void removeColorListener(ColorListener colorListener) {
		listenerList.remove(ColorListener.class, colorListener);
	}

	protected void fireColorChanged(Color color) {
		// Gets a "snapshot" of all registered listeners.
		Object[] listeners = listenerList.getListenerList();
		ColorChangeEvent event = null;

		// Listeners are notified from the last to the first registered.
		// To understand why this cycle is so particular, please see the
		// documentation of EventListenerList (and eventually, see also the
		// source code of some other classes like javax.swing.AbstractListModel).

		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == ColorListener.class) {
				if (event == null) {
					// Creates the event object. The "source" of the event
					// is *this* object.
					event = new ColorChangeEvent(this, color);
				}

				// For each registered ColorListener, invokes the colorChanged method.
				((ColorListener) listeners[i+1]).colorChanged(event);
			}
		}
	}
}