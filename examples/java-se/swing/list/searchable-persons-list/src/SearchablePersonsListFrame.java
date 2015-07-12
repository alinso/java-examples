/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;

public class SearchablePersonsListFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel searchLabel;
	private JTextField searchTextField;
	private SearchableListModel<Person> personsListModel;
	private JList personsList;
	private JScrollPane personsListScrollPane;

	public SearchablePersonsListFrame() {
		super("Searchable Persons List - Java Example by andbin");

		// Creates components and list model.
		searchLabel = new JLabel("Search start of words in first name/last name/city:");
		searchTextField = new JTextField();
		personsListModel = new SearchableListModel<Person>(PersonsDB.getPersons());
		personsList = new JList(personsListModel);
		personsListScrollPane = new JScrollPane(personsList);

		createGuiLayout();

		// Registers a custom implementation of DocumentListener to receive
		// notification of any changes from the searchTextField.
		searchTextField.getDocument().addDocumentListener(new AnyChangeDocumentListener() {
			@Override
			protected void documentChanged(DocumentEvent e) {
				applyFilter();
			}
		});

		// Setups the frame.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(450, 450);
		setLocationRelativeTo(null);   // Centers the frame on the screen.
	}

	private void createGuiLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);

		searchTextField.setAlignmentX(0);

		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.PAGE_AXIS));
		searchPanel.add(searchLabel);
		searchPanel.add(searchTextField);

		GridBagConstraints c1 = new GridBagConstraints();
		c1.weightx = 1;
		c1.fill = GridBagConstraints.HORIZONTAL;
		c1.gridwidth = GridBagConstraints.REMAINDER;
		c1.insets = new Insets(10, 10, 0, 10);
		add(searchPanel, c1);

		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.weightx = 1;
		c2.weighty = 1;
		c2.fill = GridBagConstraints.BOTH;
		c2.insets = new Insets(10, 10, 10, 10);
		add(personsListScrollPane, c2);
	}

	private void applyFilter() {
		String searchText = searchTextField.getText();

		if (searchText.length() > 0) {
			// Creates and sets a new filter if the text is non empty.
			PersonListFilter filter = new PersonListFilter(searchText);
			personsListModel.setFilter(filter);
		} else {
			// Removes the filter if the text is empty.
			personsListModel.setFilter(null);
		}
	}
}