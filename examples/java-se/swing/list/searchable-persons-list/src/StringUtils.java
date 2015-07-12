/*
 * Copyright (C) 2015 Andrea Binello ("andbin")
 *
 * This file is part of the "Java Examples" project and is licensed under the
 * MIT License. See one of the license files included in the root of the project
 * for the full text of the license.
 */

import java.util.StringTokenizer;

public class StringUtils {
	private StringUtils() {}

	public static boolean matchWordStart(String phrase, String searchText) {
		StringTokenizer tokenizer = new StringTokenizer(phrase);

		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();

			if (token.startsWith(searchText)) {
				return true;
			}
		}

		return false;
	}
}