/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class CheckboxesTableFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private CheckboxesTableModel<City> citiesTableModel;
	private JTable citiesTable;
	private JScrollPane citiesTableScrollPane;
	private JButton showSelectionButton;

	public CheckboxesTableFrame() {
		super("Checkboxes Table - Java Example by andbin");

		List<City> cities = new ArrayList<City>();
		cities.add(new City("Beijing", "China"));
		cities.add(new City("Berlin", "Germany"));
		cities.add(new City("Bern", "Switzerland"));
		cities.add(new City("Buenos Aires", "Argentina"));
		cities.add(new City("Canberra", "Australia"));
		cities.add(new City("London", "United Kingdom"));
		cities.add(new City("Madrid", "Spain"));
		cities.add(new City("Moscow", "Russia"));
		cities.add(new City("Ottawa", "Canada"));
		cities.add(new City("Paris", "France"));
		cities.add(new City("Rome", "Italy"));
		cities.add(new City("Tokyo", "Japan"));
		cities.add(new City("Washington, D.C.", "United States"));

		citiesTableModel = new CheckboxesTableModel<City>(cities);

		citiesTable = new JTable(citiesTableModel);
		// Removes table header.
		citiesTable.setTableHeader(null);
		// Disables selection in cells so that no rows/columns can be selected.
		citiesTable.setCellSelectionEnabled(false);
		// Adds some vertical spacing.
		citiesTable.setRowHeight(citiesTable.getRowHeight() + 10);
		// Removes vertical lines.
		citiesTable.setShowVerticalLines(false);
		// Sets the width of checkboxes column.
		citiesTable.getColumnModel().getColumn(0).setMaxWidth(40);

		citiesTableScrollPane = new JScrollPane(citiesTable);
		citiesTableScrollPane.setPreferredSize(new Dimension(400, 250));

		showSelectionButton = new JButton("Show selected elements");

		showSelectionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSelectedElements();
			}
		});

		getContentPane().add(citiesTableScrollPane, BorderLayout.CENTER);
		getContentPane().add(showSelectionButton, BorderLayout.SOUTH);

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}


	private void showSelectedElements() {
		List<City> selectedCities = citiesTableModel.getSelectedElements();

		// Constructs the message string to display. Note that in this context
		// (for JOptionPane) the "\n" as newline is appropriate. See the
		// "How to Make Dialogs" page in the official Oracle's tutorial.

		StringBuilder builder = new StringBuilder();
		builder.append("You have selected:\n\n");

		if (selectedCities.isEmpty()) {
			builder.append("-- no elements --");
		} else {
			for (City selectedCity : selectedCities) {
				String cityInfo = String.format("\"%s\" in country %s",
						selectedCity.getName(), selectedCity.getCountry());

				builder.append(cityInfo).append("\n");
			}
		}

		JOptionPane.showMessageDialog(this, builder.toString(),
				"Selected Elements", JOptionPane.INFORMATION_MESSAGE);
	}
}