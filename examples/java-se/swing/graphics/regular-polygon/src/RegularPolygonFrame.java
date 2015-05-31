/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class RegularPolygonFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JComboBox sidesComboBox;
	private JCheckBox circumscribedCircleCheckBox;
	private RegularPolygonPanel regularPolygonPanel;

	public RegularPolygonFrame() {
		super("Regular Polygon - Java Example by andbin");

		Integer[] sides = new Integer[12];

		for (int i = 0; i < sides.length; i++) {
			sides[i] = RegularPolygonPanel.MIN_SIDES_COUNT + i;   // Auto-boxing: int -> Integer
		}

		sidesComboBox = new JComboBox(sides);
		sidesComboBox.setMaximumSize(sidesComboBox.getPreferredSize());

		circumscribedCircleCheckBox = new JCheckBox("Show circumscribed circle");

		regularPolygonPanel = new RegularPolygonPanel();
		regularPolygonPanel.setPreferredSize(new Dimension(400, 320));

		JPanel controlsPanel = new JPanel();
		controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.LINE_AXIS));
		controlsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		controlsPanel.add(new JLabel("Number of sides"));
		controlsPanel.add(Box.createHorizontalStrut(5));
		controlsPanel.add(sidesComboBox);
		controlsPanel.add(Box.createHorizontalStrut(20));
		controlsPanel.add(circumscribedCircleCheckBox);

		getContentPane().add(controlsPanel, BorderLayout.NORTH);
		getContentPane().add(regularPolygonPanel, BorderLayout.CENTER);

		sidesComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer selectedSides = (Integer) sidesComboBox.getSelectedItem();
				regularPolygonPanel.setSidesCount(selectedSides);   // Auto-unboxing: Integer -> int
			}
		});

		circumscribedCircleCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean show = circumscribedCircleCheckBox.isSelected();
				regularPolygonPanel.setShowCircumscribedCircle(show);
			}
		});

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}
}