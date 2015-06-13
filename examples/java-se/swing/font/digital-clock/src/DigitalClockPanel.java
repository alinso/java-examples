/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
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
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;

public class DigitalClockPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel clockLabel;
	private DateFormat dateFormat;
	private Timer timer;
	private long lastSeconds;

	public DigitalClockPanel() {
		setLayout(new BorderLayout());

		clockLabel = new JLabel();
		clockLabel.setOpaque(true);
		clockLabel.setHorizontalAlignment(SwingConstants.CENTER);

		add(clockLabel, BorderLayout.CENTER);

		// Sets the default time format based on current locale.
		setDateFormat(DateFormat.getTimeInstance());

		// Setups a timer which fires events every 1/10 of second (100 milliseconds).
		timer = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateClock();
			}
		});

		// Starts the timer.
		timer.start();
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
		updateClock();
	}

	public void setClockBorder(Border border) {
		clockLabel.setBorder(border);
	}

	public void setClockBackground(Color background) {
		clockLabel.setBackground(background);
	}

	public void setClockForeground(Color foreground) {
		clockLabel.setForeground(foreground);
	}

	public void setClockFont(Font font) {
		clockLabel.setFont(font);
	}

	private void updateClock() {
		Date currentDate = new Date();

		long millis = currentDate.getTime();
		long seconds = millis / 1000;

		if (lastSeconds != seconds) {
			lastSeconds = seconds;

			String clockText = dateFormat.format(currentDate);
			clockLabel.setText(clockText);
		}
	}
}