package com.date.validatorformatter;

import java.util.Map;
import java.util.HashMap;

public class ValidatorFormatter implements ValidateFormat {

	@Override
	public String formatDate(String inputDateFormat, String inputDate, String outputDateFormat) {

		// Create and initialize hashmap for month strings
		Map monthMap = new HashMap<Integer, String>();
		monthMap.put(1, "January");
		monthMap.put(2, "February");
		monthMap.put(3, "March");
		monthMap.put(4, "April");
		monthMap.put(5, "May");
		monthMap.put(6, "June");
		monthMap.put(7, "July");
		monthMap.put(8, "August");
		monthMap.put(9, "September");
		monthMap.put(10, "October");
		monthMap.put(11, "November");
		monthMap.put(12, "December");

		return null;
	}

	@Override
	public String validateDate(String inputDateFormat, String inputDate) {

		// Create and initialize hashmap for month strings
		Map monthMap = new HashMap<Integer, String>();
		monthMap.put(1, "January");
		monthMap.put(2, "February");
		monthMap.put(3, "March");
		monthMap.put(4, "April");
		monthMap.put(5, "May");
		monthMap.put(6, "June");
		monthMap.put(7, "July");
		monthMap.put(8, "August");
		monthMap.put(9, "September");
		monthMap.put(10, "October");
		monthMap.put(11, "November");
		monthMap.put(12, "December");

		Map dayMap = new HashMap<Integer, String>();
		dayMap.put(1, "Monday");
		dayMap.put(2, "Tuesday");
		dayMap.put(3, "Wednesday");
		dayMap.put(4, "Thursday");
		dayMap.put(5, "Friday");
		dayMap.put(6, "Saturday");
		dayMap.put(7, "Sunday");

		String[] monthFormats = { "mm", "mmm", "mmmm" };
		String[] yearFormats = { "yy", "yyyy" };
		String[] dayFormats = { "dd", "ddd", "dddd" };

		StringBuilder inputFirstPart = null;
		StringBuilder inputSecondPart = null;
		StringBuilder inputThirdPart = null;
		StringBuilder inputDelimiter = null;
		inputDateFormat = inputDateFormat.toUpperCase();
		System.out.println(inputDateFormat);
		try {
			if (inputDateFormat.length() != 10 && inputDateFormat.length() != 8 && inputDateFormat.length() != 6
					&& inputDateFormat.length() != 12) {
				throw new Exception("ERROR : Invalid value for 'Date Format'. ");
			} else {
				// Check if input Format is delimited
				char delimiter;
				for (int i = 0; i < inputDateFormat.length(); i++) {
					if (inputDateFormat.charAt(i) != 'D' && inputDateFormat.charAt(i) != 'M'
							&& inputDateFormat.charAt(i) != 'Y') {
						delimiter = inputDateFormat.charAt(i);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
