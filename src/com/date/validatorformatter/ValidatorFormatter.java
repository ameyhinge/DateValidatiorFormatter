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
		String[] dayFormats = { "dd" };

		StringBuilder sbDateNonDelimiter = new StringBuilder();

		inputDateFormat = inputDateFormat.toUpperCase();
		System.out.println(inputDateFormat);
		try {

			// Convert to non delimited format
			char delimiter;
			for (int i = 0; i < inputDateFormat.length(); i++) {
				if (inputDateFormat.charAt(i) != 'D' && inputDateFormat.charAt(i) != 'M'
						&& inputDateFormat.charAt(i) != 'Y') {
					delimiter = inputDateFormat.charAt(i);

				} else {
					sbDateNonDelimiter.append(inputDateFormat.charAt(i));
				}
			}
			System.out.println(sbDateNonDelimiter);

			String dateNonDelimiter = sbDateNonDelimiter.toString();

			String inputDay;
			String inputMonth;
			String inputYear;

			// Select the input format and separate day month year
			if (dateNonDelimiter.equals("DDMMYY") || dateNonDelimiter.equals("DDYYMM")
					|| dateNonDelimiter.equals("MMDDYY") || dateNonDelimiter.equals("MMYYDD")
					|| dateNonDelimiter.equals("YYDDMM") || dateNonDelimiter.equals("YYMMDD")) {
				try {
					if (inputDate.length() != 8 && inputDate.length() != 6)
						throw new Exception("Error: Date is invalid. Invalid length for the input date.");
					else {
						if (inputDate.length() == 8) {
							if (dateNonDelimiter.equals("DDMMYY")) {
								inputDay = inputDate.substring(0, 2);
								inputMonth = inputDate.substring(3, 5);
								inputYear = inputDate.substring(6, 8);
							} else if (dateNonDelimiter.equals("DDYYMM")) {
								inputDay = inputDate.substring(0, 2);
								inputYear = inputDate.substring(3, 5);
								inputMonth = inputDate.substring(6, 8);
							} else if (dateNonDelimiter.equals("MMDDYY")) {
								inputMonth = inputDate.substring(0, 2);
								inputDay = inputDate.substring(3, 5);
								inputYear = inputDate.substring(6, 8);
							} else if (dateNonDelimiter.equals("MMYYDD")) {
								inputMonth = inputDate.substring(0, 2);
								inputYear = inputDate.substring(3, 5);
								inputDay = inputDate.substring(6, 8);
							} else if (dateNonDelimiter.equals("YYDDMM")) {
								inputYear = inputDate.substring(0, 2);
								inputDay = inputDate.substring(3, 5);
								inputMonth = inputDate.substring(6, 8);
							} else {
								inputYear = inputDate.substring(0, 2);
								inputMonth = inputDate.substring(3, 5);
								inputDay = inputDate.substring(6, 8);
							}

							System.out.println(inputDay + " " + inputMonth + " " + inputYear);
						} else {
							if (dateNonDelimiter.equals("DDMMYY")) {
								inputDay = inputDate.substring(0, 2);
								inputMonth = inputDate.substring(2, 4);
								inputYear = inputDate.substring(4, 6);
							} else if (dateNonDelimiter.equals("DDYYMM")) {
								inputDay = inputDate.substring(0, 2);
								inputYear = inputDate.substring(2, 4);
								inputMonth = inputDate.substring(4, 6);
							} else if (dateNonDelimiter.equals("MMDDYY")) {
								inputMonth = inputDate.substring(0, 2);
								inputDay = inputDate.substring(2, 4);
								inputYear = inputDate.substring(4, 6);
							} else if (dateNonDelimiter.equals("MMYYDD")) {
								inputMonth = inputDate.substring(0, 2);
								inputYear = inputDate.substring(2, 4);
								inputDay = inputDate.substring(4, 6);
							} else if (dateNonDelimiter.equals("YYDDMM")) {
								inputYear = inputDate.substring(0, 2);
								inputDay = inputDate.substring(2, 4);
								inputMonth = inputDate.substring(4, 6);
							} else {
								inputYear = inputDate.substring(0, 2);
								inputMonth = inputDate.substring(2, 4);
								inputDay = inputDate.substring(4, 6);
							}
						}

						int ipDay = Integer.parseInt(inputDay);
						int ipMonth = Integer.parseInt(inputMonth);
						int rawIpYear = Integer.parseInt(inputYear);
						int ipYear;
						if (rawIpYear < 50)
							ipYear = 2000 + rawIpYear;
						else
							ipYear = 1900 + rawIpYear;

						if (monthValidator(ipMonth) == false) {
							throw new Exception("Invalid month in the input date.");
						} else if (yearValidator(ipYear) == false) {
							throw new Exception("Invalid year in the input date.");
						} else if (dayValidator(ipDay, ipMonth, ipYear) == false) {
							throw new Exception("Invalid day in the input date.");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (dateNonDelimiter.equals("DDMMYYYY") || dateNonDelimiter.equals("DDYYYYMM")
					|| dateNonDelimiter.equals("MMDDYYYY") || dateNonDelimiter.equals("MMYYYYDD")
					|| dateNonDelimiter.equals("YYYYDDMM") || dateNonDelimiter.equals("YYYYMMDD")) {

				try {
					if (inputDate.length() != 10 && inputDate.length() != 8)
						throw new Exception("Error: Date is invalid. Invalid length for the input date.");
					else {
						if (inputDate.length() == 10) {

							if (dateNonDelimiter.equals("DDMMYYYY")) {
								inputDay = inputDate.substring(0, 2);
								inputMonth = inputDate.substring(3, 5);
								inputYear = inputDate.substring(6, 10);
							} else if (dateNonDelimiter.equals("DDYYYYMM")) {
								inputDay = inputDate.substring(0, 2);
								inputYear = inputDate.substring(3, 7);
								inputMonth = inputDate.substring(8, 10);
							} else if (dateNonDelimiter.equals("MMDDYYYY")) {
								inputMonth = inputDate.substring(0, 2);
								inputDay = inputDate.substring(3, 5);
								inputYear = inputDate.substring(6, 10);
							} else if (dateNonDelimiter.equals("MMYYYYDD")) {
								inputMonth = inputDate.substring(0, 2);
								inputYear = inputDate.substring(3, 7);
								inputDay = inputDate.substring(8, 10);
							} else if (dateNonDelimiter.equals("YYYYDDMM")) {
								inputYear = inputDate.substring(0, 4);
								inputDay = inputDate.substring(5, 7);
								inputMonth = inputDate.substring(8, 10);
							} else {
								inputYear = inputDate.substring(0, 4);
								inputMonth = inputDate.substring(5, 7);
								inputDay = inputDate.substring(8, 10);
							}

						} else {

							if (dateNonDelimiter.equals("DDMMYYYY")) {
								inputDay = inputDate.substring(0, 2);
								inputMonth = inputDate.substring(2, 4);
								inputYear = inputDate.substring(4, 8);
							} else if (dateNonDelimiter.equals("DDYYYYMM")) {
								inputDay = inputDate.substring(0, 2);
								inputYear = inputDate.substring(2, 6);
								inputMonth = inputDate.substring(6, 8);
							} else if (dateNonDelimiter.equals("MMDDYYYY")) {
								inputMonth = inputDate.substring(0, 2);
								inputDay = inputDate.substring(2, 4);
								inputYear = inputDate.substring(4, 8);
							} else if (dateNonDelimiter.equals("MMYYYYDD")) {
								inputMonth = inputDate.substring(0, 2);
								inputYear = inputDate.substring(2, 6);
								inputDay = inputDate.substring(6, 8);
							} else if (dateNonDelimiter.equals("YYYYDDMM")) {
								inputYear = inputDate.substring(0, 4);
								inputDay = inputDate.substring(4, 6);
								inputMonth = inputDate.substring(6, 8);
							} else {
								inputYear = inputDate.substring(0, 4);
								inputMonth = inputDate.substring(4, 6);
								inputDay = inputDate.substring(6, 8);
							}
						}
						System.out.println(inputDay + " " + inputMonth + " " + inputYear);
						int ipDay = Integer.parseInt(inputDay);
						int ipMonth = Integer.parseInt(inputMonth);
						int ipYear = Integer.parseInt(inputYear);

						if (monthValidator(ipMonth) == false) {
							throw new Exception("Invalid month in the input date.");
						} else if (yearValidator(ipYear) == false) {
							throw new Exception("Invalid year in the input date.");
						} else if (dayValidator(ipDay, ipMonth, ipYear) == false) {
							throw new Exception("Invalid day in the input date.");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean dayValidator(int day, int month, int year) {

		if (month == 2 && year % 4 == 0 && day > 0 && day < 30) {
			return true;
		} else if (month == 2 && year % 4 != 0 && day > 0 && day < 29) {
			return true;
		} else if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
				&& day > 0 && day < 32) {
			return true;
		} else if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 0 && day < 31) {
			return true;
		} else
			return false;
	}

	@Override
	public boolean monthValidator(int month) {

		if (month < 13 && month > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean yearValidator(int year) {

		if (year < 9999 && year > 1000)
			return true;
		else
			return false;

	}
}
