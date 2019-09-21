package com.date.validatorformatter;

public interface ValidateFormat {
	String formatDate(String inputDateFormat, String inputDate, String outputDateFormat);

	String validateDate(String inputDateFormat, String inputDate);
	
	boolean dayValidator(int day, int month, int year);
	
	boolean monthValidator(int day);
	
	boolean yearValidator(int year);
}
