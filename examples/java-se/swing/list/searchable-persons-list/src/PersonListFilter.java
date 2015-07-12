/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

// This class is a custom implementation of the SearchableListFilter interface
// to filter Person objects. The accept method returns true if a word in
// first name/last name/city starts with the searched text.

public class PersonListFilter implements SearchableListFilter<Person> {
	private final String search;

	public PersonListFilter(String search) {
		this.search = search.toLowerCase();
	}

	public boolean accept(Person item) {
		String firstName = item.getFirstName();
		String lastName = item.getLastName();
		String city = item.getCity();

		return StringUtils.matchWordStart(firstName.toLowerCase(), search)
		    || StringUtils.matchWordStart(lastName.toLowerCase(), search)
		    || StringUtils.matchWordStart(city.toLowerCase(), search);
	}
}