/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

public class RGBColorSliderPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private EventListenerList listenerList;
	private ColorSlider redColorSlider;
	private ColorSlider greenColorSlider;
	private ColorSlider blueColorSlider;

	public RGBColorSliderPanel() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		listenerList = new EventListenerList();
		redColorSlider = setupColorSlider("Red", 0);
		greenColorSlider = setupColorSlider("Green", 0);
		blueColorSlider = setupColorSlider("Blue", 0);
	}

	public Color getColor() {
		// Gets red/green/blue values.
		int redValue = redColorSlider.getValue();
		int greenValue = greenColorSlider.getValue();
		int blueValue = blueColorSlider.getValue();

		// Creates and returns a new Color.
		Color color = new Color(redValue, greenValue, blueValue);
		return color;
	}

	public void setColor(Color color) {
		// The following three setValue invocations may cause up to three
		// distinct fires of the ColorChangeEvent.
		// It is not really a problem in this example.
		redColorSlider.setValue(color.getRed());
		greenColorSlider.setValue(color.getGreen());
		blueColorSlider.setValue(color.getBlue());
	}

	// This is a specific method to add (register) a ColorListener.
	public void addColorListener(ColorListener colorListener) {
		listenerList.add(ColorListener.class, colorListener);
	}

	// This is a specific method to remove (unregister) a ColorListener.
	public void removeColorListener(ColorListener colorListener) {
		listenerList.remove(ColorListener.class, colorListener);
	}

	protected void fireColorChanged() {
		// Gets a "snapshot" of all registered listeners.
		Object[] listeners = listenerList.getListenerList();

		// Listeners are notified from the last to the first registered.
		// To understand why this cycle is so particular, please see the
		// documentation of EventListenerList (eventually, see also the
		// source code of some other classes like javax.swing.table.AbstractTableModel).

		for (int i = listeners.length-2; i >= 0; i -= 2) {
			// The test on the Class is not really necessary in this example
			// since the listener type is only one: ColorListener.
			if (listeners[i] == ColorListener.class) {
				// Creates the event object. The "source" of the event is *this* object.
				ColorChangeEvent event = new ColorChangeEvent(this, getColor());
				// For each registered listener, invokes the colorChanged method.
				((ColorListener) listeners[i+1]).colorChanged(event);
			}
		}
	}

	private ColorSlider setupColorSlider(String labelText, int initialValue) {
		ColorSlider colorSlider = new ColorSlider(labelText, initialValue);

		JTextField valueTextfield = colorSlider.getValueTextfield();

		// This setMinimumSize is necessary, otherwise GridBagLayout can "shrink"
		// the textfield to a very small size if there isn't sufficient space!
		valueTextfield.setMinimumSize(valueTextfield.getPreferredSize());

		// Adds label into GridBagLayout at column 0 for current row.
		GridBagConstraints c1 = new GridBagConstraints();
		c1.anchor = GridBagConstraints.LINE_START;
		c1.insets = new Insets(0, 0, 0, 10);
		add(colorSlider.getLabel(), c1);

		// Adds slider into GridBagLayout at column 1 for current row.
		GridBagConstraints c2 = new GridBagConstraints();
		c2.weightx = 1.0;
		c2.fill = GridBagConstraints.HORIZONTAL;
		add(colorSlider.getSlider(), c2);

		// Adds textfield into GridBagLayout at column 2 for current row.
		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridwidth = GridBagConstraints.REMAINDER;   // Last cell in row.
		c3.insets = new Insets(0, 10, 0, 0);
		add(colorSlider.getValueTextfield(), c3);

		return colorSlider;
	}

	private class ColorSlider {
		private JLabel label;
		private JSlider slider;
		private JTextField valueTextfield;

		public ColorSlider(String labelText, int initialValue) {
			label = new JLabel(labelText);
			slider = new JSlider(0, 255, initialValue);
			valueTextfield = new JTextField(3);

			// Setups the slider.
			slider.setPaintTicks(true);
			slider.setMajorTickSpacing(15);
			slider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					int value = slider.getValue();
					valueTextfield.setText(String.valueOf(value));
					fireColorChanged();
				}
			});

			// Setups the textfield.
			valueTextfield.setEditable(false);
			valueTextfield.setText(String.valueOf(initialValue));
		}

		public JLabel getLabel() {
			return label;
		}

		public JSlider getSlider() {
			return slider;
		}

		public JTextField getValueTextfield() {
			return valueTextfield;
		}

		public int getValue() {
			return slider.getValue();
		}

		public void setValue(int value) {
			// This set also causes the fire of the ChangeEvent from the slider!
			slider.setValue(value);
		}
	}
}