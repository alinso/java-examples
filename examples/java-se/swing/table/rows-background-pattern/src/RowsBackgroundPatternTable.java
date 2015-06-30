/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

// This class is a custom extension of JTable with one main goal: the overriding
// of the prepareRenderer method in order to change the background color for
// all cells, independently of any used cell renderer.
//
// The final result is a table with a repeating pattern of row background colors.

public class RowsBackgroundPatternTable extends JTable {
	private static final long serialVersionUID = 1L;

	private Color[] rowColorsPattern;

	public RowsBackgroundPatternTable(Object[][] rowData, Object[] columnNames, Color[] rowColorsPattern) {
		super(rowData, columnNames);
		this.rowColorsPattern = rowColorsPattern;
	}

	// This constructor is here just for example but is not used. You can add
	// other constructors you may need in a real project.
	public RowsBackgroundPatternTable(TableModel tableModel, Color[] rowColorsPattern) {
		super(tableModel);
		this.rowColorsPattern = rowColorsPattern;
	}

	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		// Invokes the standard implementation of prepareRenderer in JTable.
		Component rendererComponent = super.prepareRenderer(renderer, row, column);

		if (!isCellSelected(row, column)) {
			// If the cell is *not* selected, then calculates the index into
			// the pattern array and sets the background of the component.
			// If the cell is selected, the component certainly already has an
			// appropriate background color configured by the renderer.
			int index = row % rowColorsPattern.length;

			rendererComponent.setBackground(rowColorsPattern[index]);
		}

		return rendererComponent;
	}
}