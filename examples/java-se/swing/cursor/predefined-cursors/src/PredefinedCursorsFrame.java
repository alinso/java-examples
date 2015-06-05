/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class PredefinedCursorsFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel boxesPanel;
	private Font labelFont;

	public PredefinedCursorsFrame() {
		super("Predefined Cursors - Java Example by andbin");

		boxesPanel = new JPanel(new GridLayout(0, 3, 10, 10));
		boxesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		boxesPanel.setOpaque(true);
		boxesPanel.setBackground(new Color(239, 239, 239));

		labelFont = new Font("Dialog", Font.PLAIN, 12);

		addCursorBox("CROSSHAIR_CURSOR", Cursor.CROSSHAIR_CURSOR);
		addCursorBox("DEFAULT_CURSOR", Cursor.DEFAULT_CURSOR);
		addCursorBox("E_RESIZE_CURSOR", Cursor.E_RESIZE_CURSOR);
		addCursorBox("HAND_CURSOR", Cursor.HAND_CURSOR);
		addCursorBox("MOVE_CURSOR", Cursor.MOVE_CURSOR);
		addCursorBox("NE_RESIZE_CURSOR", Cursor.NE_RESIZE_CURSOR);
		addCursorBox("NW_RESIZE_CURSOR", Cursor.NW_RESIZE_CURSOR);
		addCursorBox("N_RESIZE_CURSOR", Cursor.N_RESIZE_CURSOR);
		addCursorBox("SE_RESIZE_CURSOR", Cursor.SE_RESIZE_CURSOR);
		addCursorBox("SW_RESIZE_CURSOR", Cursor.SW_RESIZE_CURSOR);
		addCursorBox("S_RESIZE_CURSOR", Cursor.S_RESIZE_CURSOR);
		addCursorBox("TEXT_CURSOR", Cursor.TEXT_CURSOR);
		addCursorBox("WAIT_CURSOR", Cursor.WAIT_CURSOR);
		addCursorBox("W_RESIZE_CURSOR", Cursor.W_RESIZE_CURSOR);

		getContentPane().add(boxesPanel, BorderLayout.CENTER);

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}

	private void addCursorBox(String title, int cursorType) {
		Cursor cursor = Cursor.getPredefinedCursor(cursorType);

		JLabel cursorLabel = new JLabel(" " + title);
		cursorLabel.setCursor(cursor);
		cursorLabel.setFont(labelFont);
		cursorLabel.setVerticalAlignment(SwingConstants.TOP);
		cursorLabel.setOpaque(true);
		cursorLabel.setForeground(Color.BLACK);
		cursorLabel.setBackground(Color.WHITE);
		cursorLabel.setBorder(BorderFactory.createLineBorder(new Color(96, 96, 96), 1));

		Dimension d = cursorLabel.getPreferredSize();
		d.width += 10;   // Adds 10 pixels for some more space on the right.
		d.height = 60;   // Fixed height.
		cursorLabel.setPreferredSize(d);

		boxesPanel.add(cursorLabel);
	}
}