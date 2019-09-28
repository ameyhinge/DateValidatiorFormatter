package com.date.validatorformatter;

import java.util.Map;
import java.util.HashMap;

public class ValidatorFormatter implements ValidateFormat {

	private static Map<String, Integer> MONTH_MAP = new HashMap<String, Integer>();
	static {
		MONTH_MAP.put("JANUARY", 1);
		MONTH_MAP.put("FEBURARY", 2);
		MONTH_MAP.put("MARCH", 3);
		MONTH_MAP.put("APRIL", 4);
		MONTH_MAP.put("MAY", 5);
		MONTH_MAP.put("JUNE", 6);
		MONTH_MAP.put("JULY", 7);
		MONTH_MAP.put("AUGUST", 8);
		MONTH_MAP.put("SEPTEMBER", 9);
		MONTH_MAP.put("OCTOBER", 10);
		MONTH_MAP.put("NOVEMBER", 11);
		MONTH_MAP.put("DECEMBER", 12);
	}

	@Override
	public String formatDate(String inputDateFormat, String inputDate, String outputDateFormat) {

		return null;
	}

	@Override
	public validatorResult validateDate(String inputDateFormat, String inputDate) {

		Map<Character, Character> numCheck = new HashMap<Character, Character>();
		numCheck.put('0', '0');
		numCheck.put('1', '0');
		numCheck.put('2', '0');
		numCheck.put('3', '0');
		numCheck.put('4', '0');
		numCheck.put('5', '0');
		numCheck.put('6', '0');
		numCheck.put('7', '0');
		numCheck.put('8', '0');
		numCheck.put('9', '0');

		Map<Character, Character> dateCheck = new HashMap<Character, Character>();
		dateCheck.put('D', '0');
		dateCheck.put('M', '0');
		dateCheck.put('Y', '0');

		Map<Character, Character> delimiterCheck = new HashMap<Character, Character>();
		delimiterCheck.put('/', '0');
		delimiterCheck.put('.', '0');
		delimiterCheck.put(' ', '0');

		StringBuilder sbDateFormatNonDelimiter = new StringBuilder();
		StringBuilder sbDateNonDelimiter = new StringBuilder();

		inputDateFormat = inputDateFormat.toUpperCase();
		System.out.println(inputDateFormat);
		try {

			// Convert to non delimited date format
			StringBuilder sbFormatDelimiter = new StringBuilder();
			for (int i = 0; i < inputDateFormat.length(); i++) {
				if (!dateCheck.containsKey(inputDateFormat.charAt(i))) {
					sbFormatDelimiter.append(inputDateFormat.charAt(i));
				} else {
					sbDateFormatNonDelimiter.append(inputDateFormat.charAt(i));
				}
			}
			System.out.println("Delimiter detected in format: " + sbFormatDelimiter);

			// Date format delimiter validations
			if (sbFormatDelimiter.length() != 2 && sbFormatDelimiter.length() != 0) {
				validatorResult vr = new validatorResult();
				vr.setMessage("ERROR: Invalid Date Format. Invalid number of delimiters detected.");
				return vr;
			} else if (sbFormatDelimiter.length() != 0 && sbFormatDelimiter.charAt(0) != sbFormatDelimiter.charAt(1)) {
				System.out.println("WARNING: Different delimiters detected in format. Both will be considered.");
			} else if (sbFormatDelimiter.length() == 0) {
				System.out.println("WARNING: No delimiter detected in format.");
			}

			// Convert to non delimited date
			StringBuilder sbDateDelimiter = new StringBuilder();
			for (int i = 0; i < inputDate.length(); i++) {
				if (delimiterCheck.containsKey(inputDate.charAt(i))) {
					sbDateDelimiter.append(inputDate.charAt(i));
				} else {
					sbDateNonDelimiter.append(inputDate.charAt(i));
				}
			}
			System.out.println("Delimiter detected in date: " + sbDateDelimiter);

			System.out.println(sbDateFormatNonDelimiter);
			System.out.println(sbDateNonDelimiter);

			String dateFormatNonDelimiter = sbDateFormatNonDelimiter.toString();
			String dateNonDelimiter = sbDateNonDelimiter.toString();
			String formatDelimiter = sbFormatDelimiter.toString();
			String dateDelimiter = sbDateDelimiter.toString();
			String inputDay;
			String inputMonth;
			String inputYear;

			// Select the DDMMYY variants and verify validity
			if (dateFormatNonDelimiter.equals("DDMMYY") || dateFormatNonDelimiter.equals("DDYYMM")
					|| dateFormatNonDelimiter.equals("MMDDYY") || dateFormatNonDelimiter.equals("MMYYDD")
					|| dateFormatNonDelimiter.equals("YYDDMM") || dateFormatNonDelimiter.equals("YYMMDD")) {
				try {
					if (dateNonDelimiter.length() != 6) {
						validatorResult vr = new validatorResult();
						vr.setIsValidFormat(true);
						vr.setMessage("ERROR: Date is invalid. Invalid length for the input date.");
						return vr;
					} else {

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
							validatorResult vr = new validatorResult();
							vr.setMessage("ERROR: Date is invalid.");
							return vr;
						}

						String yearValidatorOp;
						if (monthValidator(inputMonth, numCheck).equals("false")) {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							vr.setIsValidFormat(true);
							vr.setMessage("ERROR: Invalid month value in the input date.");
							return vr;
						} else if ((yearValidatorOp = yearValidator(inputYear, numCheck)).equals("false")) {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							vr.setIsValidFormat(true);
							vr.setMessage("ERROR: Invalid year value in the input date.");
							return vr;
						} else if (dayValidator(inputDay, Integer.parseInt(inputMonth),
								Integer.parseInt(yearValidatorOp), numCheck).equals("false")) {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							vr.setIsValidFormat(true);
							vr.setMessage("ERROR: Invalid day value in the input date.");
							return vr;
						} else {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							if (dateDelimiter.equals(formatDelimiter)) {
								vr.setMessage("INFO: Valid input date.");
							} else {
								vr.setMessage("ERROR: Date valid but delimiters do not match.");
							}
							vr.setIsValidDate(true);
							vr.setIsValidFormat(true);
							return vr;
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
					if (dateNonDelimiter.length() != 8) {
						validatorResult vr = new validatorResult();
						vr.setIsValidFormat(true);
						vr.setMessage("ERROR: Date is invalid. Invalid length for the input date.");
						return vr;
					} else {

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
							validatorResult vr = new validatorResult();
							vr.setMessage("ERROR: Date is invalid.");
							return vr;
						}

						System.out.println(inputDay + " " + inputMonth + " " + inputYear);

						String yearValidatorOp;
						if (monthValidator(inputMonth, numCheck).equals("false")) {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							vr.setIsValidFormat(true);
							vr.setMessage("ERROR: Invalid month value in the input date.");
							return vr;
						} else if ((yearValidatorOp = yearValidator(inputYear, numCheck)).equals("false")) {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							vr.setIsValidFormat(true);
							vr.setMessage("ERROR: Invalid year value in the input date.");
							return vr;
						} else if (dayValidator(inputDay, Integer.parseInt(inputMonth),
								Integer.parseInt(yearValidatorOp), numCheck).equals("false")) {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							vr.setIsValidFormat(true);
							vr.setMessage("ERROR: Invalid day value in the input date.");
							return vr;
						} else {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							if (dateDelimiter.equals(formatDelimiter)) {
								vr.setMessage("INFO: Valid input date.");
							} else {
								vr.setMessage("ERROR: Date valid but delimiters do not match.");
							}
							vr.setIsValidDate(true);
							vr.setIsValidFormat(true);
							return vr;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				// Select the DDMMMYY variants and verify validity
			} else if (dateFormatNonDelimiter.equals("DDMMMYY") || dateFormatNonDelimiter.equals("DDYYMMM")
					|| dateFormatNonDelimiter.equals("MMMDDYY") || dateFormatNonDelimiter.equals("MMMYYDD")
					|| dateFormatNonDelimiter.equals("YYDDMMM") || dateFormatNonDelimiter.equals("YYMMMDD")) {

				try {
					if (dateNonDelimiter.length() != 7) {
						validatorResult vr = new validatorResult();
						vr.setIsValidFormat(true);
						vr.setMessage("ERROR: Date is invalid. Invalid length for the input date.");
						return vr;
					} else {

						if (dateFormatNonDelimiter.equals("DDMMMYY")) {
							inputDay = dateNonDelimiter.substring(0, 2);
							inputMonth = dateNonDelimiter.substring(2, 5);
							inputYear = dateNonDelimiter.substring(5, 7);
						} else if (dateFormatNonDelimiter.equals("DDYYMMM")) {
							inputDay = dateNonDelimiter.substring(0, 2);
							inputYear = dateNonDelimiter.substring(2, 4);
							inputMonth = dateNonDelimiter.substring(4, 7);
						} else if (dateFormatNonDelimiter.equals("MMMDDYY")) {
							inputMonth = dateNonDelimiter.substring(0, 3);
							inputDay = dateNonDelimiter.substring(3, 5);
							inputYear = dateNonDelimiter.substring(5, 7);
						} else if (dateFormatNonDelimiter.equals("MMMYYDD")) {
							inputMonth = dateNonDelimiter.substring(0, 3);
							inputYear = dateNonDelimiter.substring(3, 5);
							inputDay = dateNonDelimiter.substring(5, 7);
						} else if (dateFormatNonDelimiter.equals("YYDDMMM")) {
							inputYear = dateNonDelimiter.substring(0, 2);
							inputDay = dateNonDelimiter.substring(2, 4);
							inputMonth = dateNonDelimiter.substring(4, 7);
						} else if (dateFormatNonDelimiter.equals("YYMMMDD")) {
							inputYear = dateNonDelimiter.substring(0, 2);
							inputMonth = dateNonDelimiter.substring(2, 5);
							inputDay = dateNonDelimiter.substring(5, 7);
						} else {
							validatorResult vr = new validatorResult();
							vr.setMessage("ERROR: Date is invalid.");
							return vr;
						}

						System.out.println(inputDay + " " + inputMonth + " " + inputYear);

						String monthValidatorOp;
						String yearValidatorOp;
						if ((monthValidatorOp = monthValidator(inputMonth.toUpperCase(), dateFormatNonDelimiter,
								MONTH_MAP)).equals("false")) {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							vr.setIsValidDate(false);
							vr.setIsValidFormat(true);
							vr.setMessage("ERROR: Invalid month value in the input date.");
							return vr;
						} else if ((yearValidatorOp = yearValidator(inputYear, numCheck)).equals("false")) {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							vr.setIsValidFormat(true);
							vr.setMessage("ERROR: Invalid year value in the input date.");
							return vr;
						} else if (dayValidator(inputDay, MONTH_MAP.get(monthValidatorOp),
								Integer.parseInt(yearValidatorOp), numCheck).equals("false")) {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							vr.setIsValidFormat(true);
							vr.setMessage("ERROR: Invalid day value in the input date.");
							return vr;
						} else {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							if (dateDelimiter.equals(formatDelimiter)) {
								vr.setMessage("INFO: Valid input date.");
							} else {
								vr.setMessage("ERROR: Date valid but delimiters do not match.");
							}
							vr.setIsValidDate(true);
							vr.setIsValidFormat(true);
							return vr;
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
					if (dateNonDelimiter.length() != 9) {
						validatorResult vr = new validatorResult();
						vr.setIsValidFormat(true);
						vr.setMessage("ERROR: Date is invalid. Invalid length for the input date.");
						return vr;
					} else {

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
							validatorResult vr = new validatorResult();
							vr.setIsValidFormat(true);
							vr.setMessage("ERROR: Date is invalid.");
							return vr;
						}

						System.out.println(inputDay + " " + inputMonth + " " + inputYear);

						String monthValidatorOp;
						String yearValidatorOp;
						if ((monthValidatorOp = monthValidator(inputMonth.toUpperCase(), dateFormatNonDelimiter,
								MONTH_MAP)).equals("false")) {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							vr.setIsValidFormat(true);
							vr.setMessage("ERROR: Invalid month value in the input date.");
							return vr;
						} else if ((yearValidatorOp = yearValidator(inputYear, numCheck)).equals("false")) {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							vr.setIsValidFormat(true);
							vr.setMessage("ERROR: Invalid year value in the input date.");
							return vr;
						} else if (dayValidator(inputDay, MONTH_MAP.get(monthValidatorOp),
								Integer.parseInt(yearValidatorOp), numCheck).equals("false")) {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							vr.setIsValidFormat(true);
							vr.setMessage("ERROR: Invalid day value in the input date.");
							return vr;
						} else {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							if (dateDelimiter.equals(formatDelimiter)) {
								vr.setMessage("INFO: Valid input date.");
							} else {
								vr.setMessage("ERROR: Date valid but delimiters do not match.");
							}
							vr.setIsValidDate(true);
							vr.setIsValidFormat(true);
							return vr;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				// Select the DDMMMMYY variants and verify validity
			} else if (dateFormatNonDelimiter.equals("DDMMMMYY") || dateFormatNonDelimiter.equals("DDYYMMMM")
					|| dateFormatNonDelimiter.equals("MMMMDDYY") || dateFormatNonDelimiter.equals("MMMMYYDD")
					|| dateFormatNonDelimiter.equals("YYDDMMMM") || dateFormatNonDelimiter.equals("YYMMMMDD")) {

				try {
					if (dateNonDelimiter.length() < 7 || dateNonDelimiter.length() > 14) {
						validatorResult vr = new validatorResult();
						vr.setIsValidFormat(true);
						vr.setMessage("ERROR: Date is invalid. Invalid length for the input date.");
						return vr;
					} else {

						if (dateFormatNonDelimiter.equals("DDMMMMYY")) {
							inputDay = dateNonDelimiter.substring(0, 2);
							inputMonth = monthExtracter(dateNonDelimiter, numCheck);
							inputYear = dateNonDelimiter.substring(dateNonDelimiter.length() - 2,
									dateNonDelimiter.length());
						} else if (dateFormatNonDelimiter.equals("DDYYMMMM")) {
							inputDay = dateNonDelimiter.substring(0, 2);
							inputYear = dateNonDelimiter.substring(2, 4);
							inputMonth = monthExtracter(dateNonDelimiter, numCheck);
						} else if (dateFormatNonDelimiter.equals("MMMMDDYY")) {
							inputMonth = monthExtracter(dateNonDelimiter, numCheck);
							inputDay = dateNonDelimiter.substring(dateNonDelimiter.length() - 4,
									dateNonDelimiter.length() - 2);
							inputYear = dateNonDelimiter.substring(dateNonDelimiter.length() - 2,
									dateNonDelimiter.length());
						} else if (dateFormatNonDelimiter.equals("MMMMYYDD")) {
							inputMonth = monthExtracter(dateNonDelimiter, numCheck);
							inputYear = dateNonDelimiter.substring(dateNonDelimiter.length() - 4,
									dateNonDelimiter.length() - 2);
							inputDay = dateNonDelimiter.substring(dateNonDelimiter.length() - 2,
									dateNonDelimiter.length());
						} else if (dateFormatNonDelimiter.equals("YYDDMMMM")) {
							inputYear = dateNonDelimiter.substring(0, 2);
							inputDay = dateNonDelimiter.substring(2, 4);
							inputMonth = monthExtracter(dateNonDelimiter, numCheck);
						} else if (dateFormatNonDelimiter.equals("YYMMMMDD")) {
							inputYear = dateNonDelimiter.substring(0, 2);
							inputMonth = monthExtracter(dateNonDelimiter, numCheck);
							inputDay = dateNonDelimiter.substring(dateNonDelimiter.length() - 2,
									dateNonDelimiter.length());
						} else {
							validatorResult vr = new validatorResult();
							vr.setIsValidFormat(true);
							vr.setMessage("ERROR: Date is invalid.");
							return vr;
						}

						System.out.println(inputDay + " " + inputMonth + " " + inputYear);

						String monthValidatorOp;
						String yearValidatorOp;
						if ((monthValidatorOp = monthValidator(inputMonth.toUpperCase(), dateFormatNonDelimiter,
								MONTH_MAP)).equals("false")) {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							vr.setIsValidFormat(true);
							vr.setMessage("ERROR: Invalid month value in the input date.");
							return vr;
						} else if ((yearValidatorOp = yearValidator(inputYear, numCheck)).equals("false")) {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							vr.setIsValidFormat(true);
							vr.setMessage("ERROR: Invalid year value in the input date.");
							return vr;
						} else if (dayValidator(inputDay, MONTH_MAP.get(monthValidatorOp),
								Integer.parseInt(yearValidatorOp), numCheck).equals("false")) {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							vr.setIsValidFormat(true);
							vr.setMessage("ERROR: Invalid day value in the input date.");
							return vr;
						} else {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							if (dateDelimiter.equals(formatDelimiter)) {
								vr.setMessage("INFO: Valid input date.");
							} else {
								vr.setMessage("ERROR: Date valid but delimiters do not match.");
							}
							vr.setIsValidDate(true);
							vr.setIsValidFormat(true);
							return vr;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				// Select the DDMMMMYYYY variants and verify validity
			} else if (dateFormatNonDelimiter.equals("DDMMMMYYYY") || dateFormatNonDelimiter.equals("DDYYYYMMMM")
					|| dateFormatNonDelimiter.equals("MMMMDDYYYY") || dateFormatNonDelimiter.equals("MMMMYYYYDD")
					|| dateFormatNonDelimiter.equals("YYYYDDMMMM") || dateFormatNonDelimiter.equals("YYYYMMMMDD")) {

				try {
					if (dateNonDelimiter.length() < 9 || dateNonDelimiter.length() > 16) {
						validatorResult vr = new validatorResult();
						vr.setIsValidFormat(true);
						vr.setMessage("ERROR: Date is invalid. Invalid length for the input date.");
						return vr;
					} else {

						if (dateFormatNonDelimiter.equals("DDMMMMYYYY")) {
							inputDay = dateNonDelimiter.substring(0, 2);
							inputMonth = monthExtracter(dateNonDelimiter, numCheck);
							inputYear = dateNonDelimiter.substring(dateNonDelimiter.length() - 4,
									dateNonDelimiter.length());
						} else if (dateFormatNonDelimiter.equals("DDYYYYMMMM")) {
							inputDay = dateNonDelimiter.substring(0, 2);
							inputYear = dateNonDelimiter.substring(2, 6);
							inputMonth = monthExtracter(dateNonDelimiter, numCheck);
						} else if (dateFormatNonDelimiter.equals("MMMMDDYYYY")) {
							inputMonth = monthExtracter(dateNonDelimiter, numCheck);
							inputDay = dateNonDelimiter.substring(dateNonDelimiter.length() - 6,
									dateNonDelimiter.length() - 4);
							inputYear = dateNonDelimiter.substring(dateNonDelimiter.length() - 4,
									dateNonDelimiter.length());
						} else if (dateFormatNonDelimiter.equals("MMMMYYYYDD")) {
							inputMonth = monthExtracter(dateNonDelimiter, numCheck);
							inputYear = dateNonDelimiter.substring(dateNonDelimiter.length() - 6,
									dateNonDelimiter.length() - 2);
							inputDay = dateNonDelimiter.substring(dateNonDelimiter.length() - 2,
									dateNonDelimiter.length());
						} else if (dateFormatNonDelimiter.equals("YYYYDDMMMM")) {
							inputYear = dateNonDelimiter.substring(0, 4);
							inputDay = dateNonDelimiter.substring(4, 6);
							inputMonth = monthExtracter(dateNonDelimiter, numCheck);
						} else if (dateFormatNonDelimiter.equals("YYYYMMMMDD")) {
							inputYear = dateNonDelimiter.substring(0, 4);
							inputMonth = monthExtracter(dateNonDelimiter, numCheck);
							inputDay = dateNonDelimiter.substring(dateNonDelimiter.length() - 2,
									dateNonDelimiter.length());
						} else {
							validatorResult vr = new validatorResult();
							vr.setIsValidFormat(true);
							vr.setMessage("ERROR: Date is invalid.");
							return vr;
						}

						System.out.println(inputDay + " " + inputMonth + " " + inputYear);

						String monthValidatorOp;
						String yearValidatorOp;
						if ((monthValidatorOp = monthValidator(inputMonth.toUpperCase(), dateFormatNonDelimiter,
								MONTH_MAP)).equals("false")) {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							vr.setIsValidFormat(true);
							vr.setMessage("ERROR: Invalid month value in the input date.");
							return vr;
						} else if ((yearValidatorOp = yearValidator(inputYear, numCheck)).equals("false")) {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							vr.setIsValidFormat(true);
							vr.setMessage("ERROR: Invalid year value in the input date.");
							return vr;
						} else if (dayValidator(inputDay, MONTH_MAP.get(monthValidatorOp),
								Integer.parseInt(yearValidatorOp), numCheck).equals("false")) {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							vr.setIsValidFormat(true);
							vr.setMessage("ERROR: Invalid day value in the input date.");
							return vr;
						} else {
							validatorResult vr = new validatorResult();
							vr.setInputDay(inputDay);
							vr.setInputMonth(inputMonth);
							vr.setInputYear(inputYear);
							if (dateDelimiter.equals(formatDelimiter)) {
								vr.setMessage("INFO: Valid input date.");
							} else {
								vr.setMessage("ERROR: Date valid but delimiters do not match.");
							}
							vr.setIsValidDate(true);
							vr.setIsValidFormat(true);
							return vr;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				validatorResult vr = new validatorResult();
				vr.setIsValidDate(false);
				vr.setMessage("ERROR: Invalid date format.");
				return vr;
			}
		}
		// Global Exceptions
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String dayValidator(String day, int month, int year, Map<Character, Character> numCheck) {

		// Check if day values are numeric
		for (int i = 0; i < day.length(); i++) {
			if (!numCheck.containsKey(day.charAt(i))) {
				return "false";
			}
		}

		int iDay = Integer.parseInt(day);

		if (month == 2 && year % 4 == 0 && iDay > 0 && iDay < 30) {
			return day;
		} else if (month == 2 && year % 4 != 0 && iDay > 0 && iDay < 29) {
			return day;
		} else if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
				&& iDay > 0 && iDay < 32) {
			return day;
		} else if ((month == 4 || month == 6 || month == 9 || month == 11) && iDay > 0 && iDay < 31) {
			return day;
		} else {
			return "false";
		}
	}

	private String monthValidator(String month, Map<Character, Character> numCheck) {

		// Check if month values are numeric
		for (int i = 0; i < month.length(); i++) {
			if (!numCheck.containsKey(month.charAt(i))) {
				return "false";
			}
		}
		if (Integer.parseInt(month) < 13 && Integer.parseInt(month) > 0) {
			return month;
		} else {
			return "false";
		}
	}

	private String monthValidator(String month, String dateFormatNonDelimiter, Map<String, Integer> monthMap) {
		if (dateFormatNonDelimiter.contains("MMMM") && monthMap.containsKey(month))
			return month;
		else if (dateFormatNonDelimiter.contains("MMM") && !dateFormatNonDelimiter.contains("MMMM")) {
			for (String s : monthMap.keySet()) {
				if (s.substring(0, 3).equals(month)) {
					return s;
				}
			}
		}
		return "false";
	}

	private String yearValidator(String year, Map<Character, Character> numCheck) {

		// Check if year values are numeric
		for (int i = 0; i < year.length(); i++) {
			if (!numCheck.containsKey(year.charAt(i))) {
				return "false";
			}
		}
		if (Integer.parseInt(year) < 9999 && Integer.parseInt(year) > 1000) {
			return year;
		} else if (Integer.parseInt(year) < 50) {
			return "20" + year;
		} else if (Integer.parseInt(year) > 50) {
			return "19" + year;
		} else {
			return "false";
		}
	}

	private String monthExtracter(String dateNonDelimiter, Map<Character, Character> numCheck) {
		StringBuilder sbMonth = new StringBuilder();
		for (int s = 0; s < dateNonDelimiter.length(); s++) {
			if (!numCheck.containsKey(dateNonDelimiter.charAt(s))) {
				sbMonth.append(dateNonDelimiter.charAt(s));
			}
		}
		return sbMonth.toString();
	}
}

class validatorResult {
	String inputDay;
	String inputMonth;
	String inputYear;
	boolean isValidDate;
	boolean isValidFormat;
	String message;

	public String getInputDay() {
		return inputDay;
	}

	public void setInputDay(String inputDay) {
		this.inputDay = inputDay;
	}

	public String getInputMonth() {
		return inputMonth;
	}

	public void setInputMonth(String inputMonth) {
		this.inputMonth = inputMonth;
	}

	public String getInputYear() {
		return inputYear;
	}

	public void setInputYear(String inputYear) {
		this.inputYear = inputYear;
	}

	public boolean getIsValidDate() {
		return isValidDate;
	}

	public void setIsValidDate(boolean isValidDate) {
		this.isValidDate = isValidDate;
	}

	public boolean getIsValidFormat() {
		return isValidFormat;
	}

	public void setIsValidFormat(boolean isValidFormat) {
		this.isValidFormat = isValidFormat;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
