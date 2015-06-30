/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class RowsBackgroundPatternFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public RowsBackgroundPatternFrame() {
		super("Rows Background Pattern - Java Example by andbin");

		Object[] tableColumnNames = { "Title", "Pages", "ISBN-13" };

		Object[][] tableData = {
			{ "Effective Java, 2nd Edition"                    , 384, "978-0-321-35668-0" },
			{ "JUnit in Action, 2nd Edition"                   , 504, "978-1-935-18202-3" },
			{ "Java Generics and Collections"                  , 286, "978-0-596-52775-4" },
			{ "Java I/O, 2nd Edition"                          , 728, "978-0-596-52750-1" },
			{ "Spring in Action, 4th Edition"                  , 624, "978-1-617-29120-3" },
			{ "The Definitive Guide to Java Swing, 3rd Edition", 928, "978-1-590-59447-6" },
			{ "The Java Programming Language, 4th Edition"     , 928, "978-0-321-34980-4" },
		};

		Color[] rowColorsPattern = {
			new Color(255, 232, 232),   // Light pink
			new Color(255, 255, 255),   // White
			new Color(216, 255, 255),   // Light cyan
			new Color(255, 255, 255),   // White
		};

		RowsBackgroundPatternTable table = new RowsBackgroundPatternTable(
				tableData, tableColumnNames, rowColorsPattern);

		// Sets some widths for a nice disposition of the columns.
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setMinWidth(60);
		table.getColumnModel().getColumn(1).setMaxWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setMaxWidth(150);

		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(550, 250);
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}
}