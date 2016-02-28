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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CronometerDisplayPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private ElapsedTimeFormatter elapsedTimeFormatter;
	private JLabel displayLabel;
	private Timer timer;
	private long baseTime;
	private long elapsedTime;

	public CronometerDisplayPanel(ElapsedTimeFormatter elapsedTimeFormatter) {
		this.elapsedTimeFormatter = elapsedTimeFormatter;

		displayLabel = new JLabel();
		displayLabel.setHorizontalAlignment(JLabel.CENTER);

		setLayout(new BorderLayout());
		add(displayLabel, BorderLayout.CENTER);

		showElapsedTime();

		timer = new Timer(50 /* milliseconds */, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleTimerEvent();
			}
		});
	}

	public void setTextFont(Font font) {
		displayLabel.setFont(font);
	}

	public void setTextForeground(Color color) {
		displayLabel.setForeground(color);
	}

	public void start() {
		baseTime = System.currentTimeMillis();
		elapsedTime = 0;
		showElapsedTime();
		timer.start();
	}

	public void stop() {
		timer.stop();
	}

	private void handleTimerEvent() {
		long currentTime = System.currentTimeMillis();

		elapsedTime += currentTime - baseTime;
		baseTime = currentTime;

		showElapsedTime();
	}

	private void showElapsedTime() {
		String elapsedTimeString = elapsedTimeFormatter.format(elapsedTime);
		displayLabel.setText(elapsedTimeString);
	}
}