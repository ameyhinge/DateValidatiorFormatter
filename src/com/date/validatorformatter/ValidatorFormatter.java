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
		return null;
	}

}
