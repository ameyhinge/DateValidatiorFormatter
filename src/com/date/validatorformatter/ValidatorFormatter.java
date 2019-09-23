package com.date.validatorformatter;

import java.util.Map;
import java.util.HashMap;

public class ValidatorFormatter implements ValidateFormat {

	@Override
	public String formatDate(String inputDateFormat, String inputDate, String outputDateFormat) {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.date.validatorformatter.ValidateFormat#validateDate(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String validateDate(String inputDateFormat, String inputDate) {

		// Create and initialize hashmap for month strings
		Map<String, Integer> monthMap = new HashMap<String, Integer>();
		monthMap.put("JANUARY", 1);
		monthMap.put("FEBURARY", 2);
		monthMap.put("MARCH", 3);
		monthMap.put("APRIL", 4);
		monthMap.put("MAY", 5);
		monthMap.put("JUNE", 6);
		monthMap.put("JULY", 7);
		monthMap.put("AUGUST", 8);
		monthMap.put("SEPTEMBER", 9);
		monthMap.put("OCTOBER", 10);
		monthMap.put("NOVEMBER", 11);
		monthMap.put("DECEMBER", 12);

		String[] monthFormats = { "mm", "mmm", "mmmm" };
		String[] yearFormats = { "yy", "yyyy" };
		String[] dayFormats = { "dd" };

		StringBuilder sbDateFormatNonDelimiter = new StringBuilder();
		StringBuilder sbDateNonDelimiter = new StringBuilder();

		inputDateFormat = inputDateFormat.toUpperCase();
		System.out.println(inputDateFormat);
		try {

			// Convert to non delimited date format
			char delimiter;
			for (int i = 0; i < inputDateFormat.length(); i++) {
				if (inputDateFormat.charAt(i) != 'D' && inputDateFormat.charAt(i) != 'M'
						&& inputDateFormat.charAt(i) != 'Y') {
					delimiter = inputDateFormat.charAt(i);

				} else {
					sbDateFormatNonDelimiter.append(inputDateFormat.charAt(i));
				}
			}

			// Convert to non delimited date
			for (int i = 0; i < inputDate.length(); i++) {
				if (inputDate.charAt(i) == '.' || inputDate.charAt(i) == '/') {
					delimiter = inputDate.charAt(i);

				} else {
					sbDateNonDelimiter.append(inputDate.charAt(i));
				}
			}

			System.out.println(sbDateFormatNonDelimiter);
			System.out.println(sbDateNonDelimiter);

			String dateFormatNonDelimiter = sbDateFormatNonDelimiter.toString();
			String dateNonDelimiter = sbDateNonDelimiter.toString();
			String inputDay;
			String inputMonth;
			String inputYear;

			// Select the DDMMYY variants and verify validity
			if (dateFormatNonDelimiter.equals("DDMMYY") || dateFormatNonDelimiter.equals("DDYYMM")
					|| dateFormatNonDelimiter.equals("MMDDYY") || dateFormatNonDelimiter.equals("MMYYDD")
					|| dateFormatNonDelimiter.equals("YYDDMM") || dateFormatNonDelimiter.equals("YYMMDD")) {
				try {
					if (inputDate.length() != 6)
						throw new Exception("Error: Date is invalid. Invalid length for the input date.");
					else {

						if (dateFormatNonDelimiter.equals("DDMMYY")) {
							inputDay = dateNonDelimiter.substring(0, 2);
							inputMonth = dateNonDelimiter.substring(2, 4);
							inputYear = dateNonDelimiter.substring(4, 6);
						} else if (dateFormatNonDelimiter.equals("DDYYMM")) {
							inputDay = dateNonDelimiter.substring(0, 2);
							inputYear = dateNonDelimiter.substring(2, 4);
							inputMonth = dateNonDelimiter.substring(4, 6);
						} else if (dateFormatNonDelimiter.equals("MMDDYY")) {
							inputMonth = dateNonDelimiter.substring(0, 2);
							inputDay = dateNonDelimiter.substring(2, 4);
							inputYear = dateNonDelimiter.substring(4, 6);
						} else if (dateFormatNonDelimiter.equals("MMYYDD")) {
							inputMonth = dateNonDelimiter.substring(0, 2);
							inputYear = dateNonDelimiter.substring(2, 4);
							inputDay = dateNonDelimiter.substring(4, 6);
						} else if (dateFormatNonDelimiter.equals("YYDDMM")) {
							inputYear = dateNonDelimiter.substring(0, 2);
							inputDay = dateNonDelimiter.substring(2, 4);
							inputMonth = dateNonDelimiter.substring(4, 6);
						} else if (dateFormatNonDelimiter.equals("YYMMDD")) {
							inputYear = dateNonDelimiter.substring(0, 2);
							inputMonth = dateNonDelimiter.substring(2, 4);
							inputDay = dateNonDelimiter.substring(4, 6);
						} else {
							throw new Exception("Error: Date is invalid.");
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

				// Select the DDMMYYYY variants and verify validity
			} else if (dateFormatNonDelimiter.equals("DDMMYYYY") || dateFormatNonDelimiter.equals("DDYYYYMM")
					|| dateFormatNonDelimiter.equals("MMDDYYYY") || dateFormatNonDelimiter.equals("MMYYYYDD")
					|| dateFormatNonDelimiter.equals("YYYYDDMM") || dateFormatNonDelimiter.equals("YYYYMMDD")) {

				try {
					if (dateNonDelimiter.length() != 8)
						throw new Exception("Error: Date is invalid. Invalid length for the input date.");
					else {

						if (dateFormatNonDelimiter.equals("DDMMYYYY")) {
							inputDay = dateNonDelimiter.substring(0, 2);
							inputMonth = dateNonDelimiter.substring(2, 4);
							inputYear = dateNonDelimiter.substring(4, 8);
						} else if (dateFormatNonDelimiter.equals("DDYYYYMM")) {
							inputDay = dateNonDelimiter.substring(0, 2);
							inputYear = dateNonDelimiter.substring(2, 6);
							inputMonth = dateNonDelimiter.substring(6, 8);
						} else if (dateFormatNonDelimiter.equals("MMDDYYYY")) {
							inputMonth = dateNonDelimiter.substring(0, 2);
							inputDay = dateNonDelimiter.substring(2, 4);
							inputYear = dateNonDelimiter.substring(4, 8);
						} else if (dateFormatNonDelimiter.equals("MMYYYYDD")) {
							inputMonth = dateNonDelimiter.substring(0, 2);
							inputYear = dateNonDelimiter.substring(2, 6);
							inputDay = dateNonDelimiter.substring(6, 8);
						} else if (dateFormatNonDelimiter.equals("YYYYDDMM")) {
							inputYear = dateNonDelimiter.substring(0, 4);
							inputDay = dateNonDelimiter.substring(4, 6);
							inputMonth = dateNonDelimiter.substring(6, 8);
						} else if (dateFormatNonDelimiter.equals("YYYYMMDD")) {
							inputYear = dateNonDelimiter.substring(0, 4);
							inputMonth = dateNonDelimiter.substring(4, 6);
							inputDay = dateNonDelimiter.substring(6, 8);
						} else {
							throw new Exception("Error: Date is invalid.");
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

				// Select the DDMMMYYYY variants and verify validity
			} else if (dateFormatNonDelimiter.equals("DDMMMYYYY") || dateFormatNonDelimiter.equals("DDYYYYMMM")
					|| dateFormatNonDelimiter.equals("MMMDDYYYY") || dateFormatNonDelimiter.equals("MMMYYYYDD")
					|| dateFormatNonDelimiter.equals("YYYYDDMMM") || dateFormatNonDelimiter.equals("YYYYMMMDD")) {

				try {
					if (dateNonDelimiter.length() != 9)
						throw new Exception("Error: Date is invalid. Invalid length for the input date.");
					else {

						if (dateFormatNonDelimiter.equals("DDMMMYYYY")) {
							inputDay = dateNonDelimiter.substring(0, 2);
							inputMonth = dateNonDelimiter.substring(2, 5);
							inputYear = dateNonDelimiter.substring(5, 9);
						} else if (dateFormatNonDelimiter.equals("DDYYYYMMM")) {
							inputDay = dateNonDelimiter.substring(0, 2);
							inputYear = dateNonDelimiter.substring(2, 6);
							inputMonth = dateNonDelimiter.substring(6, 9);
						} else if (dateFormatNonDelimiter.equals("MMMDDYYYY")) {
							inputMonth = dateNonDelimiter.substring(0, 3);
							inputDay = dateNonDelimiter.substring(3, 5);
							inputYear = dateNonDelimiter.substring(5, 9);
						} else if (dateFormatNonDelimiter.equals("MMMYYYYDD")) {
							inputMonth = dateNonDelimiter.substring(0, 3);
							inputYear = dateNonDelimiter.substring(3, 7);
							inputDay = dateNonDelimiter.substring(7, 9);
						} else if (dateFormatNonDelimiter.equals("YYYYDDMMM")) {
							inputYear = dateNonDelimiter.substring(0, 4);
							inputDay = dateNonDelimiter.substring(4, 6);
							inputMonth = dateNonDelimiter.substring(6, 9);
						} else if (dateFormatNonDelimiter.equals("YYYYMMMDD")) {
							inputYear = dateNonDelimiter.substring(0, 4);
							inputMonth = dateNonDelimiter.substring(4, 7);
							inputDay = dateNonDelimiter.substring(7, 9);
						} else {
							throw new Exception("Error: Date is invalid.");
						}

						System.out.println(inputDay + " " + inputMonth + " " + inputYear);
						int ipDay = Integer.parseInt(inputDay);

						int ipMonth = 0;
						for (Map.Entry<String, Integer> mon : monthMap.entrySet()) {
							if (mon.toString().substring(0, 3).equals(inputMonth.toUpperCase()))
								ipMonth = mon.getValue();
						}
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
		}

		// Global Exceptions
		catch (Exception e) {
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
