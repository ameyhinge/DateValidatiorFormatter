package com.date.validatorformatter;

import java.util.Map;

public interface ValidateFormat {
	String formatDate(String inputDateFormat, String inputDate, String outputDateFormat);

	String validateDate(String inputDateFormat, String inputDate);

	boolean dayValidator(int day, int month, int year);

	boolean monthValidator(int day);

	boolean yearValidator(int year);

	String monthExtracter(String dateNonDelimiter, Map<Character, Character> numCheck);
}
