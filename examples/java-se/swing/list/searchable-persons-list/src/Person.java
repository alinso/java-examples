/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

// This "immutable" class is used to model person entities. Note that the
// immutability is absolutely not a requirement in this Java Example. It was made
// this way just only for the sake of example.

public class Person {
	private final String firstName;
	private final String lastName;
	private final String city;

	public Person(String firstName, String lastName, String city) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCity() {
		return city;
	}

	public String toString() {
		return firstName + " " + lastName + " (" + city + ")";
	}
}