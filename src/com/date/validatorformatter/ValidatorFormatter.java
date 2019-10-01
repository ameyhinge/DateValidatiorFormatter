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

	private static Map<Character, Character> NUM_CHECK = new HashMap<Character, Character>();
	static {
		NUM_CHECK.put('0', '0');
		NUM_CHECK.put('1', '0');
		NUM_CHECK.put('2', '0');
		NUM_CHECK.put('3', '0');
		NUM_CHECK.put('4', '0');
		NUM_CHECK.put('5', '0');
		NUM_CHECK.put('6', '0');
		NUM_CHECK.put('7', '0');
		NUM_CHECK.put('8', '0');
		NUM_CHECK.put('9', '0');
	}

	private static Map<String, Character> MONTH_FORMATS = new HashMap<String, Character>();
	static {
		MONTH_FORMATS.put("MM", '0');
		MONTH_FORMATS.put("MMM", '0');
		MONTH_FORMATS.put("MMMM", '0');
	}

	private static Map<String, Character> YEAR_FORMATS = new HashMap<String, Character>();
	static {
		YEAR_FORMATS.put("YY", '0');
		YEAR_FORMATS.put("YYYY", '0');
	}

	static char DEBUG_MODE = '1';

	@Override
	public String formatDate(String inputDateFormat, String inputDate, String outputDateFormat) {

		// Validate input date format and input date
		ValidatorResult vr = new ValidatorResult();
		vr = newValidateDate(inputDateFormat, inputDate);
		if (vr.getIsValidDate() == true && vr.getIsValidFormat() == true) {
			if (DEBUG_MODE == '1') {
				System.out.println(vr.getMessage());
			}
		}
		vr = new ValidatorResult();

		vr = newValidateDate(outputDateFormat, "");
		if (vr.getIsValidFormat() == true) {
			if (DEBUG_MODE == '1') {
				System.out.println(vr.getMessage());
			}
		}

		// Pick day month and year form output format

		return null;
	}

	@Override
	public ValidatorResult newValidateDate(String inputDateFormat, String inputDate) {

		Map<Character, Character> dateCheck = new HashMap<Character, Character>();
		dateCheck.put('D', '0');
		dateCheck.put('M', '0');
		dateCheck.put('Y', '0');

		Map<Character, Character> delimiterCheck = new HashMap<Character, Character>();
		delimiterCheck.put('/', '0');
		delimiterCheck.put('.', '0');
		delimiterCheck.put(' ', '0');

		StringBuilder sbDateFormatNonDelimiter = new StringBuilder();
		StringBuilder sbFormatDelimiter = new StringBuilder();

		// Get input format and delimiters
		for (int i = 0; i < inputDateFormat.length(); i++) {
			if (!dateCheck.containsKey(inputDateFormat.toUpperCase().charAt(i))) {
				sbFormatDelimiter.append(inputDateFormat.charAt(i));
			} else {
				sbDateFormatNonDelimiter.append(inputDateFormat.charAt(i));
			}
		}

		String dateFormatNonDelimiter = sbDateFormatNonDelimiter.toString();

		if (DEBUG_MODE == '1') {
			System.out.println("Seperator in format: " + sbFormatDelimiter);
		}

		// String of stringbuilders to hold the different parts of format
		StringBuilder[] sbFormatParts = new StringBuilder[3];
		int j = 0;

		for (int i = 0; i < dateFormatNonDelimiter.length(); i++) {
			if (i == 0) {
				sbFormatParts[j] = new StringBuilder();
				sbFormatParts[j].append(dateFormatNonDelimiter.charAt(i));
			} else if (dateFormatNonDelimiter.charAt(i) == dateFormatNonDelimiter.charAt(i - 1)) {
				sbFormatParts[j].append(dateFormatNonDelimiter.charAt(i));
			} else {
				j++;
				sbFormatParts[j] = new StringBuilder();
				sbFormatParts[j].append(dateFormatNonDelimiter.charAt(i));
			}
		}

		String inputDayFormat = new String();
		String inputMonthFormat = new String();
		String inputYearFormat = new String();
		char inputDayIndex = '0';
		char inputMonthIndex = '0';
		char inputYearIndex = '0';

		// Create object to return
		ValidatorResult vr = new ValidatorResult();

		// Identify first part
		if (sbFormatParts[0] != null) {
			if (sbFormatParts[0].toString().toUpperCase().charAt(0) == 'D') {
				inputDayFormat = sbFormatParts[0].toString().toUpperCase();
				inputDayIndex = '1';
				if (DEBUG_MODE == '1') {
					System.out.println("Day format: " + inputDayFormat + " found at index: " + inputDayIndex);
				}
			} else if (sbFormatParts[0].toString().toUpperCase().charAt(0) == 'M') {
				inputMonthFormat = sbFormatParts[0].toString().toUpperCase();
				inputMonthIndex = '1';
				if (DEBUG_MODE == '1') {
					System.out.println("Month format: " + inputMonthFormat + " found at index: " + inputMonthIndex);
				}
			} else {
				inputYearFormat = sbFormatParts[0].toString().toUpperCase();
				inputYearIndex = '1';
				if (DEBUG_MODE == '1') {
					System.out.println("Year format: " + inputYearFormat + " found at index: " + inputYearIndex);
				}
			}
		} else {
			vr.setMessage("ERROR: Invalid date format.");
			return vr;
		}

		// Identify second part
		if (sbFormatParts[1] != null) {
			if (sbFormatParts[1].toString().toUpperCase().charAt(0) == 'D') {
				inputDayFormat = sbFormatParts[1].toString().toUpperCase();
				inputDayIndex = '2';
				if (DEBUG_MODE == '1') {
					System.out.println("Day format: " + inputDayFormat + " found at index: " + inputDayIndex);
				}
			} else if (sbFormatParts[1].toString().toUpperCase().charAt(0) == 'M') {
				inputMonthFormat = sbFormatParts[1].toString().toUpperCase();
				inputMonthIndex = '2';
				if (DEBUG_MODE == '1') {
					System.out.println("Month format: " + inputMonthFormat + " found at index: " + inputMonthIndex);
				}
			} else {
				inputYearFormat = sbFormatParts[1].toString().toUpperCase();
				inputYearIndex = '2';
				if (DEBUG_MODE == '1') {
					System.out.println("Year format: " + inputYearFormat + " found at index: " + inputYearIndex);
				}
			}
		} else {
			vr.setMessage("ERROR: Invalid date format.");
			return vr;
		}

		// Identify third part
		if (sbFormatParts[2] != null) {
			if (sbFormatParts[2].toString().toUpperCase().charAt(0) == 'D') {
				inputDayFormat = sbFormatParts[2].toString().toUpperCase();
				inputDayIndex = '3';
				if (DEBUG_MODE == '1') {
					System.out.println("Day format: " + inputDayFormat + " found at index: " + inputDayIndex);
				}
			} else if (sbFormatParts[2].toString().toUpperCase().charAt(0) == 'M') {
				inputMonthFormat = sbFormatParts[2].toString().toUpperCase();
				inputMonthIndex = '3';
				if (DEBUG_MODE == '1') {
					System.out.println("Month format: " + inputMonthFormat + " found at index: " + inputMonthIndex);
				}
			} else {
				inputYearFormat = sbFormatParts[2].toString().toUpperCase();
				inputYearIndex = '3';
				if (DEBUG_MODE == '1') {
					System.out.println("Year format: " + inputYearFormat + " found at index: " + inputYearIndex);
				}
			}
		} else {
			vr.setMessage("ERROR: Invalid date format.");
			return vr;
		}

		// Day Month Year Format validations
		if (!MONTH_FORMATS.containsKey(inputMonthFormat)) {
			vr.setMessage("ERROR: Invalid month format.");
			return vr;
		}
		if (!YEAR_FORMATS.containsKey(inputYearFormat)) {
			vr.setMessage("ERROR: Invalid year format.");
			return vr;
		}
		if (!inputDayFormat.equals("DD")) {
			vr.setMessage("ERROR: Invalid day format.");
			return vr;
		}

		// Format validations done so setting format valid as true
		vr.setIsValidFormat(true);

		StringBuilder sbDateDelimiter = new StringBuilder();
		StringBuilder sbDateNonDelimiter = new StringBuilder();

		// Get input date and delimiters
		for (int i = 0; i < inputDate.length(); i++) {
			if (delimiterCheck.containsKey(inputDate.charAt(i))) {
				sbDateDelimiter.append(inputDate.charAt(i));
			} else {
				sbDateNonDelimiter.append(inputDate.charAt(i));
			}
		}

		String dateNonDelimiter = sbDateNonDelimiter.toString();

		if (DEBUG_MODE == '1') {
			System.out.println("Seperator in date: " + sbDateDelimiter);
		}

		// Input date length validation
		if (dateNonDelimiter.length() < 5) {
			vr.setMessage("ERROR: Invalid length for the input date");
			return vr;
		}

		// Format validations and Date validation selector

		String monthReturned = new String();
		String yearReturned = new String();
		String dayReturned = new String();

		// Extract and validate month
		if (MONTH_FORMATS.containsKey(inputMonthFormat)) {
			if (inputMonthFormat.length() == 2) {
				if (inputMonthIndex == '1') {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking MM at index 1");
					}
					monthReturned = monthValidator(dateNonDelimiter.substring(0, 2), dateFormatNonDelimiter);
				} else if (inputMonthIndex == '2' && inputDayIndex == '1') {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking MM at index 2");
					}
					monthReturned = monthValidator(dateNonDelimiter.substring(2, 4), dateFormatNonDelimiter);
				} else if (inputMonthIndex == '2' && inputYearIndex == '1' && inputYearFormat.length() == 2) {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking MM at index 2");
					}
					monthReturned = monthValidator(dateNonDelimiter.substring(2, 4), dateFormatNonDelimiter);
				} else if (inputMonthIndex == '2' && inputYearIndex == '1' && inputYearFormat.length() == 4) {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking MM at index 2");
					}
					monthReturned = monthValidator(dateNonDelimiter.substring(4, 6), dateFormatNonDelimiter);
				} else if (inputMonthIndex == '3' && inputYearFormat.length() == 2) {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking MM at index 3");
					}
					monthReturned = monthValidator(dateNonDelimiter.substring(4, 6), dateFormatNonDelimiter);
				} else if (inputMonthIndex == '3' && inputYearFormat.length() == 4) {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking MM at index 3");
					}
					monthReturned = monthValidator(dateNonDelimiter.substring(6, 8), dateFormatNonDelimiter);
				}
			} else if (inputMonthFormat.length() == 3) {
				if (inputMonthIndex == '1') {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking MMM at index 1");
					}
					monthReturned = monthValidator(dateNonDelimiter.substring(0, 3), dateFormatNonDelimiter);
				} else if (inputMonthIndex == '2' && inputDayIndex == '1') {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking MMM at index 2");
					}
					monthReturned = monthValidator(dateNonDelimiter.substring(2, 5), dateFormatNonDelimiter);
				} else if (inputMonthIndex == '2' && inputYearIndex == '1' && inputYearFormat.length() == 2) {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking MMM at index 2");
					}
					monthReturned = monthValidator(dateNonDelimiter.substring(2, 5), dateFormatNonDelimiter);
				} else if (inputMonthIndex == '2' && inputYearIndex == '1' && inputYearFormat.length() == 4) {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking MMM at index 2");
					}
					monthReturned = monthValidator(dateNonDelimiter.substring(4, 7), dateFormatNonDelimiter);
				} else if (inputMonthIndex == '3' && inputYearFormat.length() == 2) {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking MMM at index 3");
					}
					monthReturned = monthValidator(dateNonDelimiter.substring(4, 7), dateFormatNonDelimiter);
				} else if (inputMonthIndex == '3' && inputYearFormat.length() == 4) {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking MMM at index 3");
					}
					monthReturned = monthValidator(dateNonDelimiter.substring(6, 9), dateFormatNonDelimiter);
				}
			} else if (inputMonthFormat.length() == 4) {
				if (DEBUG_MODE == '1') {
					System.out.println("Checking MMMM at all indices");
				}
				String tempMonth = monthExtracter(dateNonDelimiter);
				monthReturned = monthValidator(tempMonth, dateFormatNonDelimiter);
			}
			System.out.println("Month detected: " + monthReturned);
			// Exit if invalid month
			if (monthReturned.equals("false")) {
				vr.setMessage("ERROR: Invalid month value in input date.");
				return vr;
			} else {
				vr.setInputMonth(monthReturned);
			}
		}

		// Extract and validate year
		if (YEAR_FORMATS.containsKey(inputYearFormat)) {
			if (inputYearFormat.length() == 2) {
				if (inputYearIndex == '1') {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking YY at index 1");
					}
					yearReturned = yearValidator(dateNonDelimiter.substring(0, 2), NUM_CHECK);
					System.out.println(yearReturned);
				} else if (inputYearIndex == '2' && inputDayIndex == '1') {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking YY at index 2");
					}
					yearReturned = yearValidator(dateNonDelimiter.substring(2, 4), NUM_CHECK);
				} else if (inputYearIndex == '2' && inputMonthIndex == '1' && inputMonthFormat.length() == 2) {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking YY at index 2");
					}
					yearReturned = yearValidator(dateNonDelimiter.substring(2, 4), NUM_CHECK);
				} else if (inputYearIndex == '2' && inputMonthIndex == '1' && inputMonthFormat.length() == 3) {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking YY at index 2");
					}
					yearReturned = yearValidator(dateNonDelimiter.substring(3, 5), NUM_CHECK);
				} else if (inputYearIndex == '2' && inputMonthIndex == '1' && inputMonthFormat.length() == 4) {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking YY at index 2");
					}
					yearReturned = yearValidator(
							dateNonDelimiter.substring(monthReturned.length(), monthReturned.length() + 2), NUM_CHECK);
				} else if (inputYearIndex == '3' && inputMonthFormat.length() == 2) {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking YY at index 3");
					}
					yearReturned = yearValidator(dateNonDelimiter.substring(4, 6), NUM_CHECK);
				} else if (inputYearIndex == '3' && inputMonthFormat.length() == 3) {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking YY at index 3");
					}
					yearReturned = yearValidator(dateNonDelimiter.substring(5, 7), NUM_CHECK);
				} else if (inputYearIndex == '3' && inputMonthFormat.length() == 4) {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking YY at index 3");
					}
					String tempMonth = monthExtracter(dateNonDelimiter);
					if (!tempMonth.equals("false")) {
						yearReturned = yearValidator(
								dateNonDelimiter.substring(tempMonth.length() + 2, tempMonth.length() + 4), NUM_CHECK);
					}
				}
			} else if (inputYearFormat.length() == 4) {
				if (inputYearIndex == '1') {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking YYYY at index 1");
					}
					yearReturned = yearValidator(dateNonDelimiter.substring(0, 4), NUM_CHECK);
					System.out.println(yearReturned);
				} else if (inputYearIndex == '2' && inputDayIndex == '1') {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking YYYY at index 2");
					}
					yearReturned = yearValidator(dateNonDelimiter.substring(2, 6), NUM_CHECK);
				} else if (inputYearIndex == '2' && inputMonthIndex == '1' && inputMonthFormat.length() == 2) {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking YYYY at index 2");
					}
					yearReturned = yearValidator(dateNonDelimiter.substring(2, 6), NUM_CHECK);
				} else if (inputYearIndex == '2' && inputMonthIndex == '1' && inputMonthFormat.length() == 3) {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking YYYY at index 2");
					}
					yearReturned = yearValidator(dateNonDelimiter.substring(3, 7), NUM_CHECK);
				} else if (inputYearIndex == '2' && inputMonthIndex == '1' && inputMonthFormat.length() == 4) {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking YYYY at index 2");
					}
					String tempMonth = monthExtracter(dateNonDelimiter);
					if (!tempMonth.equals("false")) {
						yearReturned = yearValidator(
								dateNonDelimiter.substring(tempMonth.length(), tempMonth.length() + 4), NUM_CHECK);
					}
				} else if (inputYearIndex == '3' && inputMonthFormat.length() == 2) {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking YYYY at index 3");
					}
					yearReturned = yearValidator(dateNonDelimiter.substring(4, 8), NUM_CHECK);
				} else if (inputYearIndex == '3' && inputMonthFormat.length() == 3) {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking YYYY at index 3");
					}
					yearReturned = yearValidator(dateNonDelimiter.substring(5, 9), NUM_CHECK);
				} else if (inputYearIndex == '3' && inputMonthFormat.length() == 4) {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking YYYY at index 3");
					}
					String tempMonth = monthExtracter(dateNonDelimiter);
					if (!tempMonth.equals("false")) {
						yearReturned = yearValidator(
								dateNonDelimiter.substring(tempMonth.length() + 2, tempMonth.length() + 6), NUM_CHECK);
					}
				}
			}
			System.out.println("Year detected: " + yearReturned);
			// Exit if invalid year
			if (yearReturned.equals("false")) {
				vr.setMessage("ERROR: Invalid year value in input date.");
				return vr;
			} else {
				vr.setInputYear(yearReturned);
			}
		}

		// Extract and validate day
		{
			if (inputDayIndex == '1') {
				if (DEBUG_MODE == '1') {
					System.out.println("Checking DD at index 1");
				}
				dayReturned = dayValidator(dateNonDelimiter.substring(0, 2), MONTH_MAP.get(monthReturned),
						Integer.parseInt(yearReturned), NUM_CHECK);
			}
			// First index is year
			else if (inputDayIndex == '2' && inputYearIndex == '1' && inputYearFormat.length() == 2) {
				if (DEBUG_MODE == '1') {
					System.out.println("Checking DD at index 2");
				}
				dayReturned = dayValidator(dateNonDelimiter.substring(2, 4), MONTH_MAP.get(monthReturned),
						Integer.parseInt(yearReturned), NUM_CHECK);
			} else if (inputDayIndex == '2' && inputYearIndex == '1' && inputYearFormat.length() == 4) {
				if (DEBUG_MODE == '1') {
					System.out.println("Checking DD at index 2");
				}
				dayReturned = dayValidator(dateNonDelimiter.substring(4, 6), MONTH_MAP.get(monthReturned),
						Integer.parseInt(yearReturned), NUM_CHECK);
			}
			// First index is month
			else if (inputDayIndex == '2' && inputMonthIndex == '1' && inputMonthFormat.length() == 2) {
				if (DEBUG_MODE == '1') {
					System.out.println("Checking DD at index 2");
				}
				dayReturned = dayValidator(dateNonDelimiter.substring(2, 4), MONTH_MAP.get(monthReturned),
						Integer.parseInt(yearReturned), NUM_CHECK);
			} else if (inputDayIndex == '2' && inputMonthIndex == '1' && inputMonthFormat.length() == 3) {
				if (DEBUG_MODE == '1') {
					System.out.println("Checking DD at index 2");
				}
				dayReturned = dayValidator(dateNonDelimiter.substring(3, 5), MONTH_MAP.get(monthReturned),
						Integer.parseInt(yearReturned), NUM_CHECK);
			} else if (inputDayIndex == '2' && inputMonthIndex == '1' && inputMonthFormat.length() == 4) {
				if (DEBUG_MODE == '1') {
					System.out.println("Checking DD at index 2");
				}
				dayReturned = dayValidator(
						dateNonDelimiter.substring(monthReturned.length(), monthReturned.length() + 2),
						MONTH_MAP.get(monthReturned), Integer.parseInt(yearReturned), NUM_CHECK);
			}
			// Day is last index and year length = 2
			else if (inputDayIndex == '3' && inputYearFormat.length() == 2 && inputMonthFormat.length() == 2) {
				if (DEBUG_MODE == '1') {
					System.out.println("Checking DD at index 3");
				}
				dayReturned = dayValidator(dateNonDelimiter.substring(4, 6), MONTH_MAP.get(monthReturned),
						Integer.parseInt(yearReturned), NUM_CHECK);
			} else if (inputDayIndex == '3' && inputYearFormat.length() == 2 && inputMonthFormat.length() == 3) {
				if (DEBUG_MODE == '1') {
					System.out.println("Checking DD at index 3");
				}
				dayReturned = dayValidator(dateNonDelimiter.substring(5, 7), MONTH_MAP.get(monthReturned),
						Integer.parseInt(yearReturned), NUM_CHECK);
			} else if (inputDayIndex == '3' && inputYearFormat.length() == 2 && inputMonthFormat.length() == 4) {
				if (DEBUG_MODE == '1') {
					System.out.println("Checking DD at index 3");
				}
				dayReturned = dayValidator(
						dateNonDelimiter.substring(monthReturned.length() + 2, monthReturned.length() + 4),
						MONTH_MAP.get(monthReturned), Integer.parseInt(yearReturned), NUM_CHECK);
			}
			// Day is last index and year length = 4
			else if (inputDayIndex == '3' && inputYearFormat.length() == 4 && inputMonthFormat.length() == 2) {
				if (DEBUG_MODE == '1') {
					System.out.println("Checking DD at index 3");
				}
				dayReturned = dayValidator(dateNonDelimiter.substring(6, 8), MONTH_MAP.get(monthReturned),
						Integer.parseInt(yearReturned), NUM_CHECK);
			} else if (inputDayIndex == '3' && inputYearFormat.length() == 4 && inputMonthFormat.length() == 3) {
				if (DEBUG_MODE == '1') {
					System.out.println("Checking DD at index 3");
				}
				dayReturned = dayValidator(dateNonDelimiter.substring(7, 9), MONTH_MAP.get(monthReturned),
						Integer.parseInt(yearReturned), NUM_CHECK);
			} else if (inputDayIndex == '3' && inputYearFormat.length() == 4 && inputMonthFormat.length() == 4) {
				if (DEBUG_MODE == '1') {
					System.out.println("Checking DD at index 3");
				}
				dayReturned = dayValidator(
						dateNonDelimiter.substring(monthReturned.length() + 4, monthReturned.length() + 6),
						MONTH_MAP.get(monthReturned), Integer.parseInt(yearReturned), NUM_CHECK);
			}

			System.out.println("Day detected: " + dayReturned);
			// Exit if invalid day
			if (dayReturned.equals("false")) {
				vr.setMessage("ERROR: Invalid day value in input date.");
				return vr;
			} else {
				vr.setInputDay(dayReturned);
			}
		}
		// Date validations done so setting date valid as true
		vr.setIsValidDate(true);
		if (sbFormatDelimiter.toString().equals(sbDateDelimiter.toString())) {
			vr.setMessage("INFO: Date validated successfully.");
		} else {
			vr.setMessage("ERROR: Date valid but seperators do not match.");
		}

		return vr;
	}

	private String dayValidator(String day, int month, int year, Map<Character, Character> NUM_CHECK) {

		if (DEBUG_MODE == '1') {
			System.out.println("Day validator called.");
		}
		// Check if day values are numeric
		for (int i = 0; i < day.length(); i++) {
			if (!NUM_CHECK.containsKey(day.charAt(i))) {
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

	private String monthValidator(String month, String dateFormatNonDelimiter) {

		if (DEBUG_MODE == '1') {
			System.out.println("String Month validator called.");
		}
		if (dateFormatNonDelimiter.toUpperCase().contains("MMMM") && MONTH_MAP.containsKey(month.toUpperCase())) {
			if (DEBUG_MODE == '1') {
				System.out.println("Month validated successfully.");
				return month.toUpperCase();
			}
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
		// Check if month values are numeric
		else {
			if (DEBUG_MODE == '1') {
				System.out.println("Numeric Month validator called.");
			}
			for (int i = 0; i < month.length(); i++) {
				if (!NUM_CHECK.containsKey(month.charAt(i))) {
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
			}
		}
		if (DEBUG_MODE == '1') {
			System.out.println("Month validation failed.");
		}
		return "false";
	}

	private String yearValidator(String year, Map<Character, Character> NUM_CHECK) {

		if (DEBUG_MODE == '1') {
			System.out.println("Year validator called.");
		}
		// Check if year values are numeric
		for (int i = 0; i < year.length(); i++) {
			if (!NUM_CHECK.containsKey(year.charAt(i))) {
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

	private String monthExtracter(String dateNonDelimiter) {

		StringBuilder sbMonth = new StringBuilder();
		for (int s = 0; s < dateNonDelimiter.length(); s++) {
			if (!NUM_CHECK.containsKey(dateNonDelimiter.charAt(s))) {
				sbMonth.append(dateNonDelimiter.charAt(s));
			}
		}
		return sbMonth.toString();
	}
}

class ValidatorResult {

	private String inputDay;
	private String inputMonth;
	private String inputYear;
	private boolean isValidDate;
	private boolean isValidFormat;
	private String message;

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
