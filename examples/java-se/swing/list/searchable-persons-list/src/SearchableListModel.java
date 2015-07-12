/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

// This class is a custom implementation of the ListModel interface (extending
// AbstractListModel for convenience). This is a "generic" list model in which
// items can be filtered using a custom interface.

public class SearchableListModel<T> extends AbstractListModel {
	private static final long serialVersionUID = 1L;

	private List<T> originalItemsList;
	private List<T> filteredItemsList;
	private SearchableListFilter<? super T> filter;

	public SearchableListModel(List<T> items) {
		originalItemsList = new ArrayList<T>(items);
		filteredItemsList = originalItemsList;
	}

	// The following two methods fulfill the ListModel interface and they are
	// known and used by JList.

	public int getSize() {
		return filteredItemsList.size();
	}

	public Object getElementAt(int index) {
		return filteredItemsList.get(index);
	}

	// The following method is extra, custom. It is *not* for the ListModel
	// interface (nor for JList). It is intended for direct use on a
	// SearchableListModel reference.

	public void setFilter(SearchableListFilter<? super T> filter) {
		// The filter is applied only if it is a different object.
		if (this.filter != filter) {
			this.filter = filter;

			// Step 1: notifies all listeners for the removal of all the current elements.
			int oldSize = filteredItemsList.size();

			filteredItemsList = new ArrayList<T>();

			if (oldSize > 0) {
				fireIntervalRemoved(this, 0, oldSize - 1);
			}

			// Step 2: handles the filter.
			if (this.filter != null) {
				// Case 2a: if there is a filter, fills the new list with only
				// the accepted items.
				for (T item : originalItemsList) {
					if (filter.accept(item)) {
						filteredItemsList.add(item);
					}
				}
			} else {
				// Case 2b: if there is no filter, all the original items are considered.
				filteredItemsList = originalItemsList;
			}

			// Step 3: notifies all listeners for the addition of all the new
			// (either filtered or original) items.
			int newSize = filteredItemsList.size();

			if (newSize > 0) {
				fireIntervalAdded(this, 0, newSize - 1);
			}
		}
	}
}