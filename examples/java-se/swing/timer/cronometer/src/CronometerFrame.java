/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;

public class CronometerFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private CronometerDisplayPanel cronometerDisplayPanel;
	private JPanel controlsPanel;
	private JButton startButton;
	private JButton stopButton;

	public CronometerFrame() {
		super("Cronometer - Java Example by andbin");

		cronometerDisplayPanel = new CronometerDisplayPanel(new DefaultElapsedTimeFormatter());
		cronometerDisplayPanel.setTextFont(new Font("SansSerif", Font.BOLD, 48));
		cronometerDisplayPanel.setTextForeground(new Color(96, 96, 96));
		cronometerDisplayPanel.setBorder(BorderFactory.createEmptyBorder(30, 70, 30, 70));

		controlsPanel = new JPanel(new GridLayout(1, 2));
		startButton = new JButton("START");
		stopButton = new JButton("STOP");
		stopButton.setEnabled(false);

		controlsPanel.add(startButton);
		controlsPanel.add(stopButton);

		getContentPane().add(cronometerDisplayPanel, BorderLayout.CENTER);
		getContentPane().add(controlsPanel, BorderLayout.SOUTH);

		startButton.addChangeListener(new ButtonPressChangeListener() {
			@Override
			protected void buttonPressed(ChangeEvent e) {
				startCronometer();
			}
		});

		stopButton.addChangeListener(new ButtonPressChangeListener() {
			@Override
			protected void buttonPressed(ChangeEvent e) {
				stopCronometer();
			}
		});

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}

	private void startCronometer() {
		cronometerDisplayPanel.start();
		startButton.setEnabled(false);
		stopButton.setEnabled(true);
		stopButton.requestFocusInWindow();
	}

	private void stopCronometer() {
		cronometerDisplayPanel.stop();
		startButton.setEnabled(true);
		stopButton.setEnabled(false);
		startButton.requestFocusInWindow();
	}
}