/*
 * Copyright (C) 2016 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class CheckboxesTableModel<E> extends AbstractTableModel {
	public static final int CHECKBOX_COLUMN = 0;
	public static final int STRING_COLUMN = 1;

	private static final long serialVersionUID = 1L;

	private List<Item<E>> itemsList;

	public CheckboxesTableModel(Collection<E> elements) {
		itemsList = new ArrayList<Item<E>>(elements.size());

		for (E element : elements) {
			Item<E> item = new Item<E>(element);
			itemsList.add(item);
		}
	}

	// The following methods fulfil the contract of the TableModel interface,
	// so their signatures must NOT be changed.

	// Direct implementation for the TableModel interface.
	public int getRowCount() {
		return itemsList.size();
	}

	// Direct implementation for the TableModel interface.
	public int getColumnCount() {
		return 2;
	}

	// Overrides the default implementation in AbstractTableModel.
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == CHECKBOX_COLUMN) {
			// For Boolean type there is a predefined table cell renderer that
			// shows a checkbox.
			return Boolean.class;
		}

		// For Object type there is a predefined table cell renderer that show
		// the string resulting from toString() of objects.
		return Object.class;
	}

	// Overrides the default implementation in AbstractTableModel.
	@Override
	public String getColumnName(int columnIndex) {
		return "";   // Column names are not necessary for this example.
	}

	// Overrides the default implementation in AbstractTableModel.
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == CHECKBOX_COLUMN) {
			return true;  // Column is editable for checkboxes.
		}

		return false;  // All other columns are NOT editable.
	}

	// Direct implementation for the TableModel interface.
	public Object getValueAt(int rowIndex, int columnIndex) {
		Item<E> item = itemsList.get(rowIndex);

		if (columnIndex == CHECKBOX_COLUMN) {
			return item.isChecked();   // Auto-boxing boolean -> Boolean
		} else if (columnIndex == STRING_COLUMN) {
			E element = item.getElement();
			return element != null ? element.toString() : null;
		}

		return null;
	}

	// Overrides the default implementation in AbstractTableModel.
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Item<E> item = itemsList.get(rowIndex);

		// Only checkboxes column is editable and can be set.
		if (columnIndex == CHECKBOX_COLUMN) {
			// The object is expected to be a Boolean.
			Boolean checked = (Boolean) aValue;

			if (checked != null) {
				item.setChecked(checked);   // Auto-unboxing Boolean -> boolean
			}
		}
	}

	// The following methods are NOT to fulfil the contract of the TableModel
	// interface, nor for use by JTable (or by Swing in general).
	// These methods are completely custom, you may add many more like e.g.:
	// clearSelection, getAllElements, removeAllElements, etc...

	public List<E> getSelectedElements() {
		List<E> selectedElements = new ArrayList<E>();

		for (Item<E> item : itemsList) {
			if (item.isChecked()) {
				E element = item.getElement();
				selectedElements.add(element);
			}
		}

		return selectedElements;
	}


	// This is an internal class to contain one object item and an associated
	// check flag.
	private static class Item<T> {
		private boolean checked;
		private T element;

		public Item(T element) {
			this.element = element;
		}

		public boolean isChecked() {
			return checked;
		}

		public void setChecked(boolean checked) {
			this.checked = checked;
		}

		public T getElement() {
			return element;
		}
	}
}