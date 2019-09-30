package com.date.validatorformatter;

import java.util.HashMap;
import java.util.Map;

public class OldValidator {

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

	static char DEBUG_MODE = '1';

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

		// Convert date format to uppercase
		inputDateFormat = inputDateFormat.toUpperCase();

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
			// Date Format debug info
			if (DEBUG_MODE == '1') {
				System.out.println("Date Format from input: " + sbDateFormatNonDelimiter);
				System.out.println("Date Format Delimiter from input: " + sbFormatDelimiter);
			}

			// Date format delimiter validations
			if (sbFormatDelimiter.length() != 2 && sbFormatDelimiter.length() != 0) {
				validatorResult vr = new validatorResult();
				vr.setMessage("ERROR: Invalid Date Format. Invalid number of delimiters detected.");
				return vr;
			}

			// Date Format Delimiter debug info
			if (DEBUG_MODE == '1') {
				if (sbFormatDelimiter.length() != 0 && sbFormatDelimiter.charAt(0) != sbFormatDelimiter.charAt(1)) {
					System.out.println("WARNING: Different delimiters detected in format. Both will be considered.");
				}
				if (sbFormatDelimiter.length() == 0) {
					System.out.println("WARNING: No delimiter detected in format.");
				}
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

			// Date debug info
			if (DEBUG_MODE == '1') {
				System.out.println("Date from input: " + sbDateNonDelimiter);
				System.out.println("Date Delimiter from input: " + sbDateDelimiter);
			}

			// Strings needed for further validations
			String dateFormatNonDelimiter = sbDateFormatNonDelimiter.toString();
			String dateNonDelimiter = sbDateNonDelimiter.toString();
			String formatDelimiter = sbFormatDelimiter.toString();
			String dateDelimiter = sbDateDelimiter.toString();
			String inputDay = new String();
			String inputMonth = new String();
			String inputYear = new String();

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
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: DDMMYY");
							}
						} else if (dateFormatNonDelimiter.equals("DDYYMM")) {
							inputDay = dateNonDelimiter.substring(0, 2);
							inputYear = dateNonDelimiter.substring(2, 4);
							inputMonth = dateNonDelimiter.substring(4, 6);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: DDYYMM");
							}
						} else if (dateFormatNonDelimiter.equals("MMDDYY")) {
							inputMonth = dateNonDelimiter.substring(0, 2);
							inputDay = dateNonDelimiter.substring(2, 4);
							inputYear = dateNonDelimiter.substring(4, 6);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: MMDDYY");
							}
						} else if (dateFormatNonDelimiter.equals("MMYYDD")) {
							inputMonth = dateNonDelimiter.substring(0, 2);
							inputYear = dateNonDelimiter.substring(2, 4);
							inputDay = dateNonDelimiter.substring(4, 6);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: MMYYDD");
							}
						} else if (dateFormatNonDelimiter.equals("YYDDMM")) {
							inputYear = dateNonDelimiter.substring(0, 2);
							inputDay = dateNonDelimiter.substring(2, 4);
							inputMonth = dateNonDelimiter.substring(4, 6);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: YYDDMM");
							}
						} else if (dateFormatNonDelimiter.equals("YYMMDD")) {
							inputYear = dateNonDelimiter.substring(0, 2);
							inputMonth = dateNonDelimiter.substring(2, 4);
							inputDay = dateNonDelimiter.substring(4, 6);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: YYMMDD");
							}
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
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: DDMMYYYY");
							}
						} else if (dateFormatNonDelimiter.equals("DDYYYYMM")) {
							inputDay = dateNonDelimiter.substring(0, 2);
							inputYear = dateNonDelimiter.substring(2, 6);
							inputMonth = dateNonDelimiter.substring(6, 8);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: DDYYYYMM");
							}
						} else if (dateFormatNonDelimiter.equals("MMDDYYYY")) {
							inputMonth = dateNonDelimiter.substring(0, 2);
							inputDay = dateNonDelimiter.substring(2, 4);
							inputYear = dateNonDelimiter.substring(4, 8);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: MMDDYYYY");
							}
						} else if (dateFormatNonDelimiter.equals("MMYYYYDD")) {
							inputMonth = dateNonDelimiter.substring(0, 2);
							inputYear = dateNonDelimiter.substring(2, 6);
							inputDay = dateNonDelimiter.substring(6, 8);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: MMYYYYDD");
							}
						} else if (dateFormatNonDelimiter.equals("YYYYDDMM")) {
							inputYear = dateNonDelimiter.substring(0, 4);
							inputDay = dateNonDelimiter.substring(4, 6);
							inputMonth = dateNonDelimiter.substring(6, 8);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: YYYYDDMM");
							}
						} else if (dateFormatNonDelimiter.equals("YYYYMMDD")) {
							inputYear = dateNonDelimiter.substring(0, 4);
							inputMonth = dateNonDelimiter.substring(4, 6);
							inputDay = dateNonDelimiter.substring(6, 8);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: YYYYMMDD");
							}
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
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: DDMMMYY");
							}
						} else if (dateFormatNonDelimiter.equals("DDYYMMM")) {
							inputDay = dateNonDelimiter.substring(0, 2);
							inputYear = dateNonDelimiter.substring(2, 4);
							inputMonth = dateNonDelimiter.substring(4, 7);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: DDYYMMM");
							}
						} else if (dateFormatNonDelimiter.equals("MMMDDYY")) {
							inputMonth = dateNonDelimiter.substring(0, 3);
							inputDay = dateNonDelimiter.substring(3, 5);
							inputYear = dateNonDelimiter.substring(5, 7);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: MMMDDYY");
							}
						} else if (dateFormatNonDelimiter.equals("MMMYYDD")) {
							inputMonth = dateNonDelimiter.substring(0, 3);
							inputYear = dateNonDelimiter.substring(3, 5);
							inputDay = dateNonDelimiter.substring(5, 7);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: MMMYYDD");
							}
						} else if (dateFormatNonDelimiter.equals("YYDDMMM")) {
							inputYear = dateNonDelimiter.substring(0, 2);
							inputDay = dateNonDelimiter.substring(2, 4);
							inputMonth = dateNonDelimiter.substring(4, 7);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: YYDDMMM");
							}
						} else if (dateFormatNonDelimiter.equals("YYMMMDD")) {
							inputYear = dateNonDelimiter.substring(0, 2);
							inputMonth = dateNonDelimiter.substring(2, 5);
							inputDay = dateNonDelimiter.substring(5, 7);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: YYMMMDD");
							}
						}

						String monthValidatorOp;
						String yearValidatorOp;
						if ((monthValidatorOp = monthValidator(inputMonth.toUpperCase(), dateFormatNonDelimiter))
								.equals("false")) {
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
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: DDMMMYYYY");
							}
						} else if (dateFormatNonDelimiter.equals("DDYYYYMMM")) {
							inputDay = dateNonDelimiter.substring(0, 2);
							inputYear = dateNonDelimiter.substring(2, 6);
							inputMonth = dateNonDelimiter.substring(6, 9);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: DDYYYYMMM");
							}
						} else if (dateFormatNonDelimiter.equals("MMMDDYYYY")) {
							inputMonth = dateNonDelimiter.substring(0, 3);
							inputDay = dateNonDelimiter.substring(3, 5);
							inputYear = dateNonDelimiter.substring(5, 9);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: MMMDDYYYY");
							}
						} else if (dateFormatNonDelimiter.equals("MMMYYYYDD")) {
							inputMonth = dateNonDelimiter.substring(0, 3);
							inputYear = dateNonDelimiter.substring(3, 7);
							inputDay = dateNonDelimiter.substring(7, 9);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: MMMYYYYDD");
							}
						} else if (dateFormatNonDelimiter.equals("YYYYDDMMM")) {
							inputYear = dateNonDelimiter.substring(0, 4);
							inputDay = dateNonDelimiter.substring(4, 6);
							inputMonth = dateNonDelimiter.substring(6, 9);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: YYYYDDMMM");
							}
						} else if (dateFormatNonDelimiter.equals("YYYYMMMDD")) {
							inputYear = dateNonDelimiter.substring(0, 4);
							inputMonth = dateNonDelimiter.substring(4, 7);
							inputDay = dateNonDelimiter.substring(7, 9);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: YYYYMMMDD");
							}
						}

						String monthValidatorOp;
						String yearValidatorOp;
						if ((monthValidatorOp = monthValidator(inputMonth.toUpperCase(), dateFormatNonDelimiter))
								.equals("false")) {
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
						} else if (dayValidator(inputDay, MONTH_MAP.get(monthValidatorOp.toUpperCase()),
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
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: DDMMMMYY");
							}
						} else if (dateFormatNonDelimiter.equals("DDYYMMMM")) {
							inputDay = dateNonDelimiter.substring(0, 2);
							inputYear = dateNonDelimiter.substring(2, 4);
							inputMonth = monthExtracter(dateNonDelimiter, numCheck);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: DDYYMMMM");
							}
						} else if (dateFormatNonDelimiter.equals("MMMMDDYY")) {
							inputMonth = monthExtracter(dateNonDelimiter, numCheck);
							inputDay = dateNonDelimiter.substring(dateNonDelimiter.length() - 4,
									dateNonDelimiter.length() - 2);
							inputYear = dateNonDelimiter.substring(dateNonDelimiter.length() - 2,
									dateNonDelimiter.length());
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: MMMMDDYY");
							}
						} else if (dateFormatNonDelimiter.equals("MMMMYYDD")) {
							inputMonth = monthExtracter(dateNonDelimiter, numCheck);
							inputYear = dateNonDelimiter.substring(dateNonDelimiter.length() - 4,
									dateNonDelimiter.length() - 2);
							inputDay = dateNonDelimiter.substring(dateNonDelimiter.length() - 2,
									dateNonDelimiter.length());
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: MMMMYYDD");
							}
						} else if (dateFormatNonDelimiter.equals("YYDDMMMM")) {
							inputYear = dateNonDelimiter.substring(0, 2);
							inputDay = dateNonDelimiter.substring(2, 4);
							inputMonth = monthExtracter(dateNonDelimiter, numCheck);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: YYDDMMMM");
							}
						} else if (dateFormatNonDelimiter.equals("YYMMMMDD")) {
							inputYear = dateNonDelimiter.substring(0, 2);
							inputMonth = monthExtracter(dateNonDelimiter, numCheck);
							inputDay = dateNonDelimiter.substring(dateNonDelimiter.length() - 2,
									dateNonDelimiter.length());
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: YYMMMMDD");
							}
						}

						String monthValidatorOp;
						String yearValidatorOp;
						if ((monthValidatorOp = monthValidator(inputMonth.toUpperCase(), dateFormatNonDelimiter))
								.equals("false")) {
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
						} else if (dayValidator(inputDay, MONTH_MAP.get(monthValidatorOp.toUpperCase()),
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
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: DDMMMMYYYY");
							}
						} else if (dateFormatNonDelimiter.equals("DDYYYYMMMM")) {
							inputDay = dateNonDelimiter.substring(0, 2);
							inputYear = dateNonDelimiter.substring(2, 6);
							inputMonth = monthExtracter(dateNonDelimiter, numCheck);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: DDYYYYMMMM");
							}
						} else if (dateFormatNonDelimiter.equals("MMMMDDYYYY")) {
							inputMonth = monthExtracter(dateNonDelimiter, numCheck);
							inputDay = dateNonDelimiter.substring(dateNonDelimiter.length() - 6,
									dateNonDelimiter.length() - 4);
							inputYear = dateNonDelimiter.substring(dateNonDelimiter.length() - 4,
									dateNonDelimiter.length());
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: MMMMDDYYYY");
							}
						} else if (dateFormatNonDelimiter.equals("MMMMYYYYDD")) {
							inputMonth = monthExtracter(dateNonDelimiter, numCheck);
							inputYear = dateNonDelimiter.substring(dateNonDelimiter.length() - 6,
									dateNonDelimiter.length() - 2);
							inputDay = dateNonDelimiter.substring(dateNonDelimiter.length() - 2,
									dateNonDelimiter.length());
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: MMMMYYYYDD");
							}
						} else if (dateFormatNonDelimiter.equals("YYYYDDMMMM")) {
							inputYear = dateNonDelimiter.substring(0, 4);
							inputDay = dateNonDelimiter.substring(4, 6);
							inputMonth = monthExtracter(dateNonDelimiter, numCheck);
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: YYYYDDMMMM");
							}
						} else if (dateFormatNonDelimiter.equals("YYYYMMMMDD")) {
							inputYear = dateNonDelimiter.substring(0, 4);
							inputMonth = monthExtracter(dateNonDelimiter, numCheck);
							inputDay = dateNonDelimiter.substring(dateNonDelimiter.length() - 2,
									dateNonDelimiter.length());
							if (DEBUG_MODE == '1') {
								System.out.println("Date format detected: YYYYMMMMDD");
							}
						}

						String monthValidatorOp;
						String yearValidatorOp;
						if ((monthValidatorOp = monthValidator(inputMonth.toUpperCase(), dateFormatNonDelimiter))
								.equals("false")) {
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
						} else if (dayValidator(inputDay, MONTH_MAP.get(monthValidatorOp.toUpperCase()),
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
				if (DEBUG_MODE == '1') {
					System.out.println("Day validation failed.");
				}
				return "false";
			}
		}

		int iDay = Integer.parseInt(day);

		if (month == 2 && year % 4 == 0 && iDay > 0 && iDay < 30) {
			if (DEBUG_MODE == '1') {
				System.out.println("Day validated successfully.");
			}
			return day;
		} else if (month == 2 && year % 4 != 0 && iDay > 0 && iDay < 29) {
			if (DEBUG_MODE == '1') {
				System.out.println("Day validated successfully.");
			}
			return day;
		} else if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
				&& iDay > 0 && iDay < 32) {
			if (DEBUG_MODE == '1') {
				System.out.println("Day validated successfully.");
			}
			return day;
		} else if ((month == 4 || month == 6 || month == 9 || month == 11) && iDay > 0 && iDay < 31) {
			if (DEBUG_MODE == '1') {
				System.out.println("Day validated successfully.");
			}
			return day;
		} else {
			if (DEBUG_MODE == '1') {
				System.out.println("Day validation failed.");
			}
			return "false";
		}
	}

	private String monthValidator(String month, Map<Character, Character> numCheck) {

		if (DEBUG_MODE == '1') {
			System.out.println("Numeric Month validator called.");
		}
		// Check if month values are numeric
		for (int i = 0; i < month.length(); i++) {
			if (!numCheck.containsKey(month.charAt(i))) {
				if (DEBUG_MODE == '1') {
					System.out.println("Month validation failed.");
				}
				return "false";
			}
		}
		if (Integer.parseInt(month) < 13 && Integer.parseInt(month) > 0) {
			if (DEBUG_MODE == '1') {
				System.out.println("Month validated successfully.");
			}
			for (Map.Entry<String, Integer> e : MONTH_MAP.entrySet()) {
				if (e.getValue() == Integer.parseInt(month))
					return e.getKey();
			}
		} else {
			if (DEBUG_MODE == '1') {
				System.out.println("Month validation failed.");
			}
			return "false";
		}
		return month;
	}

	private String monthValidator(String month, String dateFormatNonDelimiter) {

		if (DEBUG_MODE == '1') {
			System.out.println("String Month validator called.");
		}
		if (dateFormatNonDelimiter.toUpperCase().contains("MMMM") && MONTH_MAP.containsKey(month.toUpperCase())) {
			if (DEBUG_MODE == '1') {
				System.out.println("Month validated successfully.");
			}
			return month.toUpperCase();
		} else if (dateFormatNonDelimiter.toUpperCase().contains("MMM")
				&& !dateFormatNonDelimiter.toUpperCase().contains("MMMM")) {
			for (String s : MONTH_MAP.keySet()) {
				if (s.substring(0, 3).equals(month.toUpperCase())) {
					if (DEBUG_MODE == '1') {
						System.out.println("Month validated successfully.");
					}
					return s;
				}
			}
		}
		if (DEBUG_MODE == '1') {
			System.out.println("Month validation failed.");
		}
		return "false";
	}

	private String yearValidator(String year, Map<Character, Character> numCheck) {

		// Check if year values are numeric
		for (int i = 0; i < year.length(); i++) {
			if (!numCheck.containsKey(year.charAt(i))) {
				if (DEBUG_MODE == '1') {
					System.out.println("Year validation failed.");
				}
				return "false";
			}
		}
		if (Integer.parseInt(year) < 9999 && Integer.parseInt(year) > 1000) {
			if (DEBUG_MODE == '1') {
				System.out.println("Year validated successfully.");
			}
			return year;
		} else if (year.length() == 2 && Integer.parseInt(year) < 50) {
			if (DEBUG_MODE == '1') {
				System.out.println("Appending 20 as prefix to the year.");
				System.out.println("Year validated successfully.");
			}
			return "20" + year;
		} else if (year.length() == 2 && Integer.parseInt(year) > 50) {
			if (DEBUG_MODE == '1') {
				System.out.println("Appending 19 as prefix to the year.");
				System.out.println("Year validated successfully.");
			}
			return "19" + year;
		} else {
			if (DEBUG_MODE == '1') {
				System.out.println("Year validation failed.");
			}
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

class dupvalidatorResult {

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
