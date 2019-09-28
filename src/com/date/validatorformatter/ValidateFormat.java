package com.date.validatorformatter;

public interface ValidateFormat {
	String formatDate(String inputDateFormat, String inputDate, String outputDateFormat);

	validatorResult validateDate(String inputDateFormat, String inputDate);
}
