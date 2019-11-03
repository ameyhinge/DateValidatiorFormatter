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

	private static Map<String, Character> DAY_FORMATS = new HashMap<String, Character>();
	static {
		DAY_FORMATS.put("D", '0');
		DAY_FORMATS.put("DD", '0');
	}

	private static Map<String, Character> MONTH_FORMATS = new HashMap<String, Character>();
	static {
		MONTH_FORMATS.put("M", '0');
		MONTH_FORMATS.put("MM", '0');
		MONTH_FORMATS.put("MMM", '0');
		MONTH_FORMATS.put("MMMM", '0');
	}

	private static Map<String, Character> YEAR_FORMATS = new HashMap<String, Character>();
	static {
		YEAR_FORMATS.put("YY", '0');
		YEAR_FORMATS.put("YYYY", '0');
	}

	private static String[] MONTH_31 = { "JANUARY", "MARCH", "MAY", "JULY", "AUGUST", "OCTOBER", "DECEMBER" };
	private static String[] MONTH_30 = { "APRIL", "JUNE", "SEPTEMBER", "NOVEMBER" };
	private static String MONTH_02 = "FEBURARY";

	private static Map<Character, Character> DATE_CHECK = new HashMap<Character, Character>();
	static {
		DATE_CHECK.put('D', '0');
		DATE_CHECK.put('M', '0');
		DATE_CHECK.put('Y', '0');
	}

	static char DEBUG_MODE = '1';

	@Override
	public String incrementDate(String inputDateFormat, String inputDate, String quantity, String unit) {

		// Validate the date and format
		ValidatorResult vr = new ValidatorResult();
		vr = validateDate(inputDateFormat, inputDate);
		if (vr.getIsValidDate() == true && vr.getIsValidFormat() == true) {
			debugPrint(vr.getMessage());
		} else {
			return "ERROR: Input Date and Date format do not match.";
		}

		monthType(vr.getInputMonth());
		return "yay";
	}

	@Override
	public String formatDate(String inputDateFormat, String inputDate, String outputDateFormat) {

		// String builder for output date
		StringBuilder sbOutputDate = new StringBuilder();

		// Validate input date format and input date
		ValidatorResult vr = new ValidatorResult();
		vr = validateDate(inputDateFormat, inputDate);
		if (vr.getIsValidDate() == true && vr.getIsValidFormat() == true) {
			debugPrint(vr.getMessage());
		} else {
			return "ERROR: Input Date and Date format do not match.";
		}

		// Validate output date format
		FormatDetails fd = new FormatDetails();
		fd = formatProcessor(outputDateFormat);

		if (fd.getFormatValid() == true) {

			// Build if first part is day
			if (fd.getDayIndex() == '1') {
				sbOutputDate.append(vr.getInputDay());
				// Append delimiter
				if (fd.getFormatDelimiter().length() == 2) {
					sbOutputDate.append(fd.getFormatDelimiter().charAt(0));
				}
				// Build the second and third part
				char index = '2';
				while (index == '2' || index == '3') {
					if (fd.getMonthIndex() == index && fd.getMonthFormat().length() == 2) {
						if (MONTH_MAP.get(vr.getInputMonth()) < 9) {
							sbOutputDate.append("0");
						}
						sbOutputDate.append(MONTH_MAP.get(vr.getInputMonth()));
					} else if (fd.getMonthIndex() == index && fd.getMonthFormat().length() == 3) {
						sbOutputDate.append(vr.getInputMonth().substring(0, 3));
					} else if (fd.getMonthIndex() == index && fd.getMonthFormat().length() == 4) {
						sbOutputDate.append(vr.getInputMonth());
					} else if (fd.getYearIndex() == index
							&& (fd.getYearFormat().length() == 2 || fd.getYearFormat().length() == 4)) {
						sbOutputDate.append(vr.getInputYear().substring(4 - fd.getYearFormat().length(), 4));
					}
					// Append delimiter
					if (index == '2' && fd.getFormatDelimiter().length() == 2) {
						sbOutputDate.append(fd.getFormatDelimiter().charAt(0));
					}
					index = (char) ((int) index + 1);
				}
			}

			// Build if first part is month
			if (fd.getMonthIndex() == '1') {
				// Build the first part
				if (fd.getMonthFormat().length() == 2) {
					if (MONTH_MAP.get(vr.getInputMonth()) < 9) {
						sbOutputDate.append("0");
					}
					sbOutputDate.append(MONTH_MAP.get(vr.getInputMonth()));
				} else if (fd.getMonthFormat().length() == 3) {
					sbOutputDate.append(vr.getInputMonth().substring(0, 3));
				} else if (fd.getMonthFormat().length() == 4) {
					sbOutputDate.append(vr.getInputMonth());
				}
				// Append delimiter
				if (fd.getFormatDelimiter().length() == 2) {
					sbOutputDate.append(fd.getFormatDelimiter().charAt(0));
				}
				// Build the second and third part
				char index = '2';
				while (index == '2' || index == '3') {
					if (fd.getDayIndex() == index) {
						sbOutputDate.append(vr.getInputDay());
					} else if (fd.getYearIndex() == index
							&& (fd.getYearFormat().length() == 2 || fd.getYearFormat().length() == 4)) {
						sbOutputDate.append(vr.getInputYear().substring(4 - fd.getYearFormat().length(), 4));
					}
					// Append delimiter
					if (index == '2' && fd.getFormatDelimiter().length() == 2) {
						sbOutputDate.append(fd.getFormatDelimiter().charAt(0));
					}
					index = (char) ((int) index + 1);
				}
			}

			// Build if first part is year
			if (fd.getYearIndex() == '1') {
				// Build the first part
				if (fd.getYearFormat().length() == 2) {
					sbOutputDate.append(vr.getInputYear().substring(2, 4));
				} else if (fd.getYearFormat().length() == 4) {
					sbOutputDate.append(vr.getInputYear());
				}
				// Append delimiter
				if (fd.getFormatDelimiter().length() == 2) {
					sbOutputDate.append(fd.getFormatDelimiter().charAt(0));
				}
				// Build the second and third part
				char index = '2';
				while (index == '2' || index == '3') {
					if (fd.getDayIndex() == index) {
						sbOutputDate.append(vr.getInputDay());
					} else if (fd.getMonthIndex() == index && fd.getMonthFormat().length() == 2) {
						if (MONTH_MAP.get(vr.getInputMonth()) < 9) {
							sbOutputDate.append("0");
						}
						sbOutputDate.append(MONTH_MAP.get(vr.getInputMonth()));
					} else if (fd.getMonthIndex() == index && fd.getMonthFormat().length() == 3) {
						sbOutputDate.append(vr.getInputMonth().substring(0, 3));
					} else if (fd.getMonthIndex() == index && fd.getMonthFormat().length() == 4) {
						sbOutputDate.append(vr.getInputMonth());
					} else if (fd.getYearIndex() == index
							&& (fd.getYearFormat().length() == 2 || fd.getYearFormat().length() == 4)) {
						sbOutputDate.append(vr.getInputYear().substring(4 - fd.getYearFormat().length(), 4));
					}
					// Append delimiter
					if (index == '2' && fd.getFormatDelimiter().length() == 2) {
						sbOutputDate.append(fd.getFormatDelimiter().charAt(0));
					}
					index = (char) ((int) index + 1);
				}
			}
			debugPrint("Output Date is: " + sbOutputDate.toString());

		} else {
			return "ERROR: Invalid output format";
		}
		return sbOutputDate.toString();
	}

	@Override
	public ValidatorResult validateDate(String inputDateFormat, String inputDate) {

		Map<Character, Character> delimiterCheck = new HashMap<Character, Character>();
		delimiterCheck.put('/', '0');
		delimiterCheck.put('.', '0');
		delimiterCheck.put(' ', '0');

		// Validate format
		FormatDetails fd = new FormatDetails();
		fd = formatProcessor(inputDateFormat);

		ValidatorResult vr = new ValidatorResult();
		if (fd.getFormatValid() == true) {
			vr.setIsValidFormat(true);
			vr.setDayIndex(fd.getDayIndex());
			vr.setMonthIndex(fd.getMonthIndex());
			vr.setYearIndex(fd.getYearIndex());
		} else {
			vr.setMessage(fd.getMessage());
			return vr;
		}

		String inputDayFormat = fd.getDayFormat();
		String inputMonthFormat = fd.getMonthFormat();
		String inputYearFormat = fd.getYearFormat();

		char inputDayIndex = fd.getDayIndex();
		char inputMonthIndex = fd.getMonthIndex();
		char inputYearIndex = fd.getYearIndex();

		String dateFormatNonDelimiter = fd.getFormat();
		String formatDelimiter = fd.getFormatDelimiter();

		// Get input date and delimiters
		StringBuilder sbDateDelimiter = new StringBuilder();
		StringBuilder sbDateNonDelimiter = new StringBuilder();

		for (int i = 0; i < inputDate.length(); i++) {
			if (delimiterCheck.containsKey(inputDate.charAt(i))) {
				sbDateDelimiter.append(inputDate.charAt(i));
			} else {
				sbDateNonDelimiter.append(inputDate.charAt(i));
			}
		}

		String dateNonDelimiter = sbDateNonDelimiter.toString();
		debugPrint("Seperator in date: " + sbDateDelimiter);

		// Input date length validation
		if (dateNonDelimiter.length() < 4) {
			vr.setMessage("ERROR: Invalid length for the input date.");
			return vr;
		}

		// Dates to be returned from validators
		String monthReturned = new String();
		String yearReturned = new String();
		String dayReturned = new String();

		// Extract and validate month
		if (MONTH_FORMATS.containsKey(inputMonthFormat)) {
			if (inputMonthFormat.length() == 1 || inputMonthFormat.length() == 2 || inputMonthFormat.length() == 3) {
				if (inputMonthIndex == '1') {
					debugPrint("Checking Month at index 1");
					monthReturned = monthValidator(dateNonDelimiter.substring(0, inputMonthFormat.length()),
							inputMonthFormat);
				} else if (inputMonthIndex == '2' && inputDayIndex == '1') {
					debugPrint("Checking Month at index 2");
					monthReturned = monthValidator(dateNonDelimiter.substring(inputDayFormat.length(),
							inputDayFormat.length() + inputMonthFormat.length()), inputMonthFormat);
				} else if (inputMonthIndex == '2' && inputYearIndex == '1') {
					debugPrint("Checking Month at index 2");
					monthReturned = monthValidator(dateNonDelimiter.substring(inputYearFormat.length(),
							inputYearFormat.length() + inputMonthFormat.length()), inputMonthFormat);
				} else if (inputMonthIndex == '3') {
					debugPrint("Checking Month at index 3");
					monthReturned = monthValidator(dateNonDelimiter
							.substring(inputDayFormat.length() + inputYearFormat.length(), dateNonDelimiter.length()),
							inputMonthFormat);
				}
			} else if (inputMonthFormat.length() == 4) {
				debugPrint("Checking Month at all indices");
				String tempMonth = monthExtracter(dateNonDelimiter);
				monthReturned = monthValidator(tempMonth, inputMonthFormat);
			}
		}
		debugPrint("Month detected: " + monthReturned);
		// Exit if invalid month
		if (monthReturned.equals("false")) {
			vr.setMessage("ERROR: Invalid month value in input date.");
			return vr;
		} else {
			vr.setInputMonth(monthReturned);
		}

		// Extract and validate year
		if (YEAR_FORMATS.containsKey(inputYearFormat)) {
			if (inputYearIndex == '1') {
				debugPrint("Checking Year at index 1");
				yearReturned = yearValidator(dateNonDelimiter.substring(0, inputYearFormat.length()), inputYearFormat);
				System.out.println(yearReturned);
			} else if (inputYearIndex == '2' && inputDayIndex == '1') {
				debugPrint("Checking Year at index 2");
				yearReturned = yearValidator(dateNonDelimiter.substring(inputDayFormat.length(),
						inputDayFormat.length() + inputYearFormat.length()), inputYearFormat);
			} else if (inputYearIndex == '2' && inputMonthIndex == '1' && inputMonthFormat.length() != 4) {
				debugPrint("Checking Year at index 2");
				yearReturned = yearValidator(dateNonDelimiter.substring(inputMonthFormat.length(),
						inputMonthFormat.length() + inputYearFormat.length()), inputYearFormat);
			} else if (inputYearIndex == '2' && inputMonthIndex == '1' && inputMonthFormat.length() == 4) {
				debugPrint("Checking Year at index 2");
				yearReturned = yearValidator(dateNonDelimiter.substring(monthReturned.length(),
						monthReturned.length() + inputYearFormat.length()), inputYearFormat);
			} else if (inputYearIndex == '3' && inputMonthFormat.length() != 4) {
				debugPrint("Checking Year at index 3");
				yearReturned = yearValidator(dateNonDelimiter
						.substring(inputDayFormat.length() + inputMonthFormat.length(), dateNonDelimiter.length()),
						inputYearFormat);
			} else if (inputYearIndex == '3' && inputMonthFormat.length() == 4) {
				debugPrint("Checking Year at index 3");
				yearReturned = yearValidator(dateNonDelimiter.substring(
						inputDayFormat.length() + monthReturned.length(), dateNonDelimiter.length()), inputYearFormat);
			}
		}
		debugPrint("Year detected: " + yearReturned);
		// Exit if invalid year
		if (yearReturned.equals("false")) {
			vr.setMessage("ERROR: Invalid year value in input date.");
			return vr;
		} else {
			vr.setInputYear(yearReturned);
		}

		// Extract and validate day
		if (DAY_FORMATS.containsKey(inputDayFormat)) {
			if (inputDayIndex == '1') {
				debugPrint("Checking Day at index 1");
				dayReturned = dayValidator(dateNonDelimiter.substring(0, inputDayFormat.length()),
						MONTH_MAP.get(monthReturned), Integer.parseInt(yearReturned));
			}
			// First index is year
			else if (inputDayIndex == '2' && inputYearIndex == '1') {
				debugPrint("Checking Day at index 2");
				dayReturned = dayValidator(
						dateNonDelimiter.substring(inputYearFormat.length(),
								inputYearFormat.length() + inputDayFormat.length()),
						MONTH_MAP.get(monthReturned), Integer.parseInt(yearReturned));
			}
			// First index is month
			else if (inputDayIndex == '2' && inputMonthIndex == '1' && inputMonthFormat.length() != 4) {
				debugPrint("Checking Day at index 2");
				dayReturned = dayValidator(
						dateNonDelimiter.substring(inputMonthFormat.length(),
								inputMonthFormat.length() + inputDayFormat.length()),
						MONTH_MAP.get(monthReturned), Integer.parseInt(yearReturned));
			} else if (inputDayIndex == '2' && inputMonthIndex == '1' && inputMonthFormat.length() == 4) {
				debugPrint("Checking Day at index 2");
				dayReturned = dayValidator(
						dateNonDelimiter.substring(monthReturned.length(),
								monthReturned.length() + inputDayFormat.length()),
						MONTH_MAP.get(monthReturned), Integer.parseInt(yearReturned));
			}
			// Last index is day
			else if (inputDayIndex == '3' && inputMonthFormat.length() != 4) {
				debugPrint("Checking Day at index 3");
				dayReturned = dayValidator(
						dateNonDelimiter.substring(inputMonthFormat.length() + inputYearFormat.length(),
								dateNonDelimiter.length()),
						MONTH_MAP.get(monthReturned), Integer.parseInt(yearReturned));
			} else if (inputDayIndex == '3' && inputMonthFormat.length() == 4) {
				debugPrint("Checking Day at index 3");
				dayReturned = dayValidator(
						dateNonDelimiter.substring(monthReturned.length() + inputYearFormat.length(),
								dateNonDelimiter.length()),
						MONTH_MAP.get(monthReturned), Integer.parseInt(yearReturned));
			}
		}
		System.out.println("Day detected: " + dayReturned);
		// Exit if invalid day
		if (dayReturned.equals("false")) {
			vr.setMessage("ERROR: Invalid day value in input date.");
			return vr;
		} else {
			vr.setInputDay(dayReturned);
		}

		// Date validations done so setting date valid as true
		vr.setIsValidDate(true);
		if (formatDelimiter.equals(sbDateDelimiter.toString())) {
			vr.setMessage("INFO: Date validated successfully.");
		} else {
			vr.setMessage("ERROR: Date valid but seperators do not match.");
		}
		return vr;
	}

	// Private functions

	private String dayValidator(String day, int month, int year) {
		debugPrint("Day validator called.");

		int iDay = 0;
		try {
			iDay = Integer.parseInt(day);
		} catch (Exception e) {
			debugPrint("Day validation failed.");
			return "false";
		}

		if (month == 2 && year % 4 == 0 && iDay > 0 && iDay < 30) {
		} else if (month == 2 && year % 4 != 0 && iDay > 0 && iDay < 29) {
		} else if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
				&& iDay > 0 && iDay < 32) {
		} else if ((month == 4 || month == 6 || month == 9 || month == 11) && iDay > 0 && iDay < 31) {
		} else {
			debugPrint("Day validation failed.");
			return "false";
		}
		// Day is valid. Return it
		debugPrint("Day validated successfully.");
		if (day.length() == 1) {
			day = "0" + day;
		}
		return day;
	}

	private String monthValidator(String month, String inputMonthFormat) {

		debugPrint("String Month validator called.");
		if (inputMonthFormat.equals("MMMM") && MONTH_MAP.containsKey(month.toUpperCase())) {
			debugPrint("Month validated successfully.");
			return month.toUpperCase();
		} else if (inputMonthFormat.equals("MMM")) {
			for (String s : MONTH_MAP.keySet()) {
				if (s.substring(0, 3).equals(month.toUpperCase())) {
					debugPrint("Month validated successfully.");
					return s;
				}
			}
		}
		// Check if month values are numeric
		else {
			debugPrint("Numeric Month validator called.");

			int iMonth = 0;
			try {
				iMonth = Integer.parseInt(month);
			} catch (Exception e) {
				debugPrint("Month validation failed.");
				return "false";
			}
			if (iMonth < 13 && iMonth > 0) {
				debugPrint("Month validated successfully.");
				for (Map.Entry<String, Integer> e : MONTH_MAP.entrySet()) {
					if (e.getValue() == Integer.parseInt(month))
						return e.getKey();
				}
			}
		}
		debugPrint("Month validation failed.");
		return "false";
	}

	private String yearValidator(String year, String inputYearFormat) {

		debugPrint("Year validator called.");

		if (inputYearFormat.length() != year.length()) {
			debugPrint("Year validation failed.");
			return "false";
		}

		// Check if year values are numeric
		int iYear = 0;

		try {
			iYear = Integer.parseInt(year);
		} catch (Exception e) {
			debugPrint("Year validation failed.");
			return "false";
		}

		if (iYear < 9999 && iYear > 1000) {
		} else if (year.length() == 2 && iYear < 50) {
			debugPrint("Appending 20 as prefix to the year.");
			year = "20" + year;
		} else if (year.length() == 2 && iYear > 50) {
			debugPrint("Appending 19 as prefix to the year.");
			year = "19" + year;
		} else {
			debugPrint("Year validation failed.");
			return "false";
		}
		// Year is valid, Return it
		debugPrint("Year validated successfully.");
		return year;
	}

	private String monthExtracter(String dateNonDelimiter) {

		StringBuilder sbMonth = new StringBuilder();
		for (int s = 0; s < dateNonDelimiter.length(); s++) {
			if ((int) dateNonDelimiter.charAt(s) > 96 && (int) dateNonDelimiter.charAt(s) < 123) {
				sbMonth.append(dateNonDelimiter.charAt(s));
			} else if ((int) dateNonDelimiter.charAt(s) > 64 && (int) dateNonDelimiter.charAt(s) < 91) {
				sbMonth.append(dateNonDelimiter.charAt(s));
			}
		}
		return sbMonth.toString();
	}

	private FormatDetails formatProcessor(String inputDateFormat) {

		StringBuilder sbDateFormatNonDelimiter = new StringBuilder();
		StringBuilder sbFormatDelimiter = new StringBuilder();

		// Make the object to return
		FormatDetails fd = new FormatDetails();

		// Get input format and delimiters
		for (int i = 0; i < inputDateFormat.length(); i++) {
			if (!DATE_CHECK.containsKey(inputDateFormat.toUpperCase().charAt(i))) {
				sbFormatDelimiter.append(inputDateFormat.charAt(i));
			} else {
				sbDateFormatNonDelimiter.append(inputDateFormat.charAt(i));
			}
		}
		fd.setFormat(sbDateFormatNonDelimiter.toString().toUpperCase());
		fd.setFormatDelimiter(sbFormatDelimiter.toString().toUpperCase());

		// Extract the parts, index and validate
		StringBuilder[] sbFormatParts = new StringBuilder[3];
		int j = 0;

		for (int i = 0; i < fd.getFormat().length(); i++) {
			if (i == 0) {
				sbFormatParts[j] = new StringBuilder();
				sbFormatParts[j].append(fd.getFormat().charAt(i));
			} else if (fd.getFormat().charAt(i) == fd.getFormat().charAt(i - 1)) {
				sbFormatParts[j].append(fd.getFormat().charAt(i));
			} else {
				j++;
				sbFormatParts[j] = new StringBuilder();
				sbFormatParts[j].append(fd.getFormat().charAt(i));
			}
		}

		// Identify and validate first part
		if (sbFormatParts[0] != null) {
			if (DAY_FORMATS.containsKey(sbFormatParts[0].toString())) {
				fd.setDayFormat(sbFormatParts[0].toString());
				fd.setDayIndex('1');
				debugPrint("Day format: " + fd.getDayFormat() + " found at index: " + fd.getDayIndex());
			} else if (MONTH_FORMATS.containsKey(sbFormatParts[0].toString())) {
				fd.setMonthFormat(sbFormatParts[0].toString());
				fd.setMonthIndex('1');
				debugPrint("Month format: " + fd.getMonthFormat() + " found at index: " + fd.getMonthIndex());
			} else if (YEAR_FORMATS.containsKey(sbFormatParts[0].toString())) {
				fd.setYearFormat(sbFormatParts[0].toString());
				fd.setYearIndex('1');
				debugPrint("Year format: " + fd.getYearFormat() + " found at index: " + fd.getYearIndex());
			} else {
				fd.setMessage("ERROR: Invalid date format.");
				return fd;
			}
		} else {
			fd.setMessage("ERROR: Invalid date format.");
			return fd;
		}

		// Identify and validate second part
		if (sbFormatParts[1] != null) {
			if (DAY_FORMATS.containsKey(sbFormatParts[1].toString())) {
				fd.setDayFormat(sbFormatParts[1].toString());
				fd.setDayIndex('2');
				debugPrint("Day format: " + fd.getDayFormat() + " found at index: " + fd.getDayIndex());
			} else if (MONTH_FORMATS.containsKey(sbFormatParts[1].toString())) {
				fd.setMonthFormat(sbFormatParts[1].toString());
				fd.setMonthIndex('2');
				debugPrint("Month format: " + fd.getMonthFormat() + " found at index: " + fd.getMonthIndex());
			} else if (YEAR_FORMATS.containsKey(sbFormatParts[1].toString())) {
				fd.setYearFormat(sbFormatParts[1].toString());
				fd.setYearIndex('2');
				debugPrint("Year format: " + fd.getYearFormat() + " found at index: " + fd.getYearIndex());
			} else {
				fd.setMessage("ERROR: Invalid date format.");
				return fd;
			}
		} else {
			fd.setMessage("ERROR: Invalid date format.");
			return fd;
		}

		// Identify and validate third part
		if (sbFormatParts[2] != null) {
			if (DAY_FORMATS.containsKey(sbFormatParts[2].toString())) {
				fd.setDayFormat(sbFormatParts[2].toString());
				fd.setDayIndex('3');
				debugPrint("Day format: " + fd.getDayFormat() + " found at index: " + fd.getDayIndex());
			} else if (MONTH_FORMATS.containsKey(sbFormatParts[2].toString())) {
				fd.setMonthFormat(sbFormatParts[2].toString());
				fd.setMonthIndex('3');
				debugPrint("Month format: " + fd.getMonthFormat() + " found at index: " + fd.getMonthIndex());
			} else if (YEAR_FORMATS.containsKey(sbFormatParts[2].toString())) {
				fd.setYearFormat(sbFormatParts[2].toString());
				fd.setYearIndex('3');
				debugPrint("Year format: " + fd.getYearFormat() + " found at index: " + fd.getYearIndex());
			} else {
				fd.setMessage("ERROR: Invalid date format.");
				return fd;
			}
		} else {
			fd.setMessage("ERROR: Invalid date format.");
			return fd;
		}
		// Everything valid, set validity = true and return the object
		fd.setFormatValid(true);
		return fd;
	}

	private void debugPrint(String message) {
		if (DEBUG_MODE == '1') {
			System.out.println(message);
		}
	}

	private int monthType(String month) {
		// Check in MONTH_31
		for (int i = 0; i < MONTH_31.length; i++) {
			if (month.equals(MONTH_31[i])) {
				debugPrint("Month type is: 31");
				return 31;
			}
		}
		// Check in MONTH_30
		for (int i = 0; i < MONTH_30.length; i++) {
			if (month.equals(MONTH_30[i])) {
				debugPrint("Month type is: 30");
				return 30;
			}
		}
		// Check for Feburary
		if (month.equals(MONTH_02)) {
			debugPrint("Month type is: 2");
			return 2;
		} else {
			debugPrint("Invalid month");
			return 0;
		}
	}
}
