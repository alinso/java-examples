/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class CustomCheckBoxIconsFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JCheckBox standardCheckBox;
	private CustomCheckBox customCheckBox;
	private JButton enableControlButton;
	private JButton rolloverControlButton;

	public CustomCheckBoxIconsFrame() {
		super("Custom Checkbox Icons - Java Example by andbin");

		standardCheckBox = new JCheckBox("Standard checkbox");
		customCheckBox = new CustomCheckBox("Custom checkbox with icons for each state");
		enableControlButton = new JButton();   // text is set just below
		rolloverControlButton = new JButton();   // text is set just below

		setCheckBoxEnabled(true);
		setRolloverEnabled(true);

		// Creates a demo panel for checkboxes.
		JPanel checkboxPanel = new JPanel();
		checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.PAGE_AXIS));
		checkboxPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		checkboxPanel.add(standardCheckBox);
		checkboxPanel.add(customCheckBox);

		// Creates a control panel with two buttons in a single row.
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(1, 2, 10, 0));
		controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		controlPanel.add(enableControlButton);
		controlPanel.add(rolloverControlButton);

		// Layouts the panels.
		getContentPane().add(checkboxPanel, BorderLayout.CENTER);
		getContentPane().add(controlPanel, BorderLayout.SOUTH);

		// Registers action listeners implemented as anonymous inner classes.
		enableControlButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCheckBoxEnabled(!standardCheckBox.isEnabled());
			}
		});

		rolloverControlButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setRolloverEnabled(!standardCheckBox.isRolloverEnabled());
			}
		});

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(450, 200);
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}

	private void setCheckBoxEnabled(boolean enabled) {
		standardCheckBox.setEnabled(enabled);
		customCheckBox.setEnabled(enabled);
		enableControlButton.setText(enabled ? "Disable checkbox" : "Enable checkbox");
	}

	private void setRolloverEnabled(boolean enabled) {
		standardCheckBox.setRolloverEnabled(enabled);
		customCheckBox.setRolloverEnabled(enabled);
		rolloverControlButton.setText(enabled ? "Disable rollover effect" : "Enable rollover effect");
	}
}