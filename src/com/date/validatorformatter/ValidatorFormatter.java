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

	private static Map<Character, Character> DATE_CHECK = new HashMap<Character, Character>();
	static {
		DATE_CHECK.put('D', '0');
		DATE_CHECK.put('M', '0');
		DATE_CHECK.put('Y', '0');
	}

	static char DEBUG_MODE = '1';

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
			System.out.println("ERROR: Input Date and Date format do not match.");
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
					} else if (fd.getYearIndex() == index && fd.getYearFormat().length() == 2) {
						sbOutputDate.append(vr.getInputYear().substring(2, 4));
					} else if (fd.getYearIndex() == index && fd.getYearFormat().length() == 4) {
						sbOutputDate.append(vr.getInputYear());
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
					} else if (fd.getYearIndex() == index && fd.getYearFormat().length() == 2) {
						sbOutputDate.append(vr.getInputYear().substring(2, 4));
					} else if (fd.getYearIndex() == index && fd.getYearFormat().length() == 4) {
						sbOutputDate.append(vr.getInputYear());
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
					} else if (fd.getYearIndex() == index && fd.getYearFormat().length() == 2) {
						sbOutputDate.append(vr.getInputYear().substring(2, 4));
					} else if (fd.getYearIndex() == index && fd.getYearFormat().length() == 4) {
						sbOutputDate.append(vr.getInputYear());
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
		} else {
			vr.setMessage(fd.getMessage());
			return vr;
		}

		String inputMonthFormat = fd.getMonthFormat();
		String inputYearFormat = fd.getYearFormat();

		char inputMonthIndex = fd.getMonthIndex();
		char inputYearIndex = fd.getYearIndex();
		char inputDayIndex = fd.getDayIndex();

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
		if (dateNonDelimiter.length() < 5) {
			vr.setMessage("ERROR: Invalid length for the input date.");
			return vr;
		}

		char day_length = '2';
		if (dateNonDelimiter.length() == dateFormatNonDelimiter.length() - 1) {
			day_length = '1';
		}

		debugPrint("Day length is: " + day_length);

		// Date validations
		String monthReturned = new String();
		String yearReturned = new String();
		String dayReturned = new String();

		// Extract and validate month
		if (MONTH_FORMATS.containsKey(inputMonthFormat)) {
			if (inputMonthFormat.length() == 2) {
				if (inputMonthIndex == '1') {
					debugPrint("Checking MM at index 1");
					monthReturned = monthValidator(dateNonDelimiter.substring(0, 2), dateFormatNonDelimiter);
				} else if (inputMonthIndex == '2' && inputDayIndex == '1') {
					if (DEBUG_MODE == '1') {
						System.out.println("Checking MM at index 2");
					}
					if (day_length == '1') {
						monthReturned = monthValidator(dateNonDelimiter.substring(1, 3), dateFormatNonDelimiter);
					} else {
						monthReturned = monthValidator(dateNonDelimiter.substring(2, 4), dateFormatNonDelimiter);
					}
				} else if (inputMonthIndex == '2' && inputYearIndex == '1' && inputYearFormat.length() == 2) {
					debugPrint("Checking MM at index 2");
					monthReturned = monthValidator(dateNonDelimiter.substring(2, 4), dateFormatNonDelimiter);
				} else if (inputMonthIndex == '2' && inputYearIndex == '1' && inputYearFormat.length() == 4) {
					debugPrint("Checking MM at index 2");
					monthReturned = monthValidator(dateNonDelimiter.substring(4, 6), dateFormatNonDelimiter);
				} else if (inputMonthIndex == '3' && inputYearFormat.length() == 2) {
					debugPrint("Checking MM at index 3");
					if (day_length == '1') {
						monthReturned = monthValidator(dateNonDelimiter.substring(3, 5), dateFormatNonDelimiter);
					} else {
						monthReturned = monthValidator(dateNonDelimiter.substring(4, 6), dateFormatNonDelimiter);
					}
				} else if (inputMonthIndex == '3' && inputYearFormat.length() == 4) {
					debugPrint("Checking MM at index 3");
					monthReturned = monthValidator(dateNonDelimiter.substring(6, 8), dateFormatNonDelimiter);
				}
			} else if (inputMonthFormat.length() == 3) {
				if (inputMonthIndex == '1') {
					debugPrint("Checking MMM at index 1");
					monthReturned = monthValidator(dateNonDelimiter.substring(0, 3), dateFormatNonDelimiter);
				} else if (inputMonthIndex == '2' && inputDayIndex == '1') {
					debugPrint("Checking MMM at index 2");
					if (day_length == '1') {
						monthReturned = monthValidator(dateNonDelimiter.substring(1, 4), dateFormatNonDelimiter);
					} else {
						monthReturned = monthValidator(dateNonDelimiter.substring(2, 5), dateFormatNonDelimiter);
					}
				} else if (inputMonthIndex == '2' && inputYearIndex == '1' && inputYearFormat.length() == 2) {
					debugPrint("Checking MMM at index 2");
					monthReturned = monthValidator(dateNonDelimiter.substring(2, 5), dateFormatNonDelimiter);
				} else if (inputMonthIndex == '2' && inputYearIndex == '1' && inputYearFormat.length() == 4) {
					debugPrint("Checking MMM at index 2");
					monthReturned = monthValidator(dateNonDelimiter.substring(4, 7), dateFormatNonDelimiter);
				} else if (inputMonthIndex == '3' && inputYearFormat.length() == 2) {
					debugPrint("Checking MMM at index 3");
					if (day_length == '1') {
						monthReturned = monthValidator(dateNonDelimiter.substring(3, 6), dateFormatNonDelimiter);
					} else {
						monthReturned = monthValidator(dateNonDelimiter.substring(4, 7), dateFormatNonDelimiter);
					}
				} else if (inputMonthIndex == '3' && inputYearFormat.length() == 4) {
					debugPrint("Checking MMM at index 3");
					if (day_length == '1') {
						monthReturned = monthValidator(dateNonDelimiter.substring(5, 8), dateFormatNonDelimiter);
					} else {
						monthReturned = monthValidator(dateNonDelimiter.substring(6, 9), dateFormatNonDelimiter);
					}
				}
			} else if (inputMonthFormat.length() == 4) {
				debugPrint("Checking MMMM at all indices");
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
					debugPrint("Checking YY at index 1");
					yearReturned = yearValidator(dateNonDelimiter.substring(0, 2), NUM_CHECK);
					System.out.println(yearReturned);
				} else if (inputYearIndex == '2' && inputDayIndex == '1') {
					debugPrint("Checking YY at index 2");
					if (day_length == '1') {
						yearReturned = yearValidator(dateNonDelimiter.substring(1, 3), NUM_CHECK);
					} else {
						yearReturned = yearValidator(dateNonDelimiter.substring(2, 4), NUM_CHECK);
					}
				} else if (inputYearIndex == '2' && inputMonthIndex == '1' && inputMonthFormat.length() == 2) {
					debugPrint("Checking YY at index 2");
					yearReturned = yearValidator(dateNonDelimiter.substring(2, 4), NUM_CHECK);
				} else if (inputYearIndex == '2' && inputMonthIndex == '1' && inputMonthFormat.length() == 3) {
					debugPrint("Checking YY at index 2");
					yearReturned = yearValidator(dateNonDelimiter.substring(3, 5), NUM_CHECK);
				} else if (inputYearIndex == '2' && inputMonthIndex == '1' && inputMonthFormat.length() == 4) {
					debugPrint("Checking YY at index 2");
					yearReturned = yearValidator(
							dateNonDelimiter.substring(monthReturned.length(), monthReturned.length() + 2), NUM_CHECK);
				} else if (inputYearIndex == '3' && inputMonthFormat.length() == 2) {
					debugPrint("Checking YY at index 3");
					if (day_length == '1') {
						yearReturned = yearValidator(dateNonDelimiter.substring(3, 5), NUM_CHECK);
					} else {
						yearReturned = yearValidator(dateNonDelimiter.substring(4, 6), NUM_CHECK);
					}
				} else if (inputYearIndex == '3' && inputMonthFormat.length() == 3) {
					debugPrint("Checking YY at index 3");
					if (day_length == '1') {
						yearReturned = yearValidator(dateNonDelimiter.substring(4, 6), NUM_CHECK);
					} else {
						yearReturned = yearValidator(dateNonDelimiter.substring(5, 7), NUM_CHECK);
					}
				} else if (inputYearIndex == '3' && inputMonthFormat.length() == 4) {
					debugPrint("Checking YY at index 3");
					String tempMonth = monthExtracter(dateNonDelimiter);
					if (!tempMonth.equals("false")) {
						if (day_length == '1') {
							yearReturned = yearValidator(
									dateNonDelimiter.substring(tempMonth.length() + 1, tempMonth.length() + 3),
									NUM_CHECK);
						} else {
							yearReturned = yearValidator(
									dateNonDelimiter.substring(tempMonth.length() + 2, tempMonth.length() + 4),
									NUM_CHECK);
						}
					}
				}
			} else if (inputYearFormat.length() == 4) {
				if (inputYearIndex == '1') {
					debugPrint("Checking YYYY at index 1");
					yearReturned = yearValidator(dateNonDelimiter.substring(0, 4), NUM_CHECK);
					System.out.println(yearReturned);
				} else if (inputYearIndex == '2' && inputDayIndex == '1') {
					debugPrint("Checking YYYY at index 2");
					if (day_length == '1') {
						yearReturned = yearValidator(dateNonDelimiter.substring(1, 5), NUM_CHECK);
					} else {
						yearReturned = yearValidator(dateNonDelimiter.substring(2, 6), NUM_CHECK);
					}
				} else if (inputYearIndex == '2' && inputMonthIndex == '1' && inputMonthFormat.length() == 2) {
					debugPrint("Checking YYYY at index 2");
					yearReturned = yearValidator(dateNonDelimiter.substring(2, 6), NUM_CHECK);
				} else if (inputYearIndex == '2' && inputMonthIndex == '1' && inputMonthFormat.length() == 3) {
					debugPrint("Checking YYYY at index 2");
					yearReturned = yearValidator(dateNonDelimiter.substring(3, 7), NUM_CHECK);
				} else if (inputYearIndex == '2' && inputMonthIndex == '1' && inputMonthFormat.length() == 4) {
					debugPrint("Checking YYYY at index 2");
					String tempMonth = monthExtracter(dateNonDelimiter);
					if (!tempMonth.equals("false")) {
						yearReturned = yearValidator(
								dateNonDelimiter.substring(tempMonth.length(), tempMonth.length() + 4), NUM_CHECK);
					}
				} else if (inputYearIndex == '3' && inputMonthFormat.length() == 2) {
					debugPrint("Checking YYYY at index 3");
					if (day_length == '1') {
						yearReturned = yearValidator(dateNonDelimiter.substring(3, 7), NUM_CHECK);
					} else {
						yearReturned = yearValidator(dateNonDelimiter.substring(4, 8), NUM_CHECK);
					}
				} else if (inputYearIndex == '3' && inputMonthFormat.length() == 3) {
					debugPrint("Checking YYYY at index 3");
					if (day_length == '1') {
						yearReturned = yearValidator(dateNonDelimiter.substring(4, 8), NUM_CHECK);
					} else {
						yearReturned = yearValidator(dateNonDelimiter.substring(5, 9), NUM_CHECK);
					}
				} else if (inputYearIndex == '3' && inputMonthFormat.length() == 4) {
					debugPrint("Checking YYYY at index 3");
					String tempMonth = monthExtracter(dateNonDelimiter);
					if (!tempMonth.equals("false")) {
						if (day_length == '1') {
							yearReturned = yearValidator(
									dateNonDelimiter.substring(tempMonth.length() + 1, tempMonth.length() + 5),
									NUM_CHECK);
						} else {
							yearReturned = yearValidator(
									dateNonDelimiter.substring(tempMonth.length() + 2, tempMonth.length() + 6),
									NUM_CHECK);
						}
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
				debugPrint("Checking DD at index 1");
				if (day_length == '1') {
					dayReturned = dayValidator("0" + dateNonDelimiter.substring(0, 1), MONTH_MAP.get(monthReturned),
							Integer.parseInt(yearReturned), NUM_CHECK);
				} else {
					dayReturned = dayValidator(dateNonDelimiter.substring(0, 2), MONTH_MAP.get(monthReturned),
							Integer.parseInt(yearReturned), NUM_CHECK);
				}
			}
			// First index is year
			else if (inputDayIndex == '2' && inputYearIndex == '1' && inputYearFormat.length() == 2) {
				debugPrint("Checking DD at index 2");
				if (day_length == '1') {
					dayReturned = dayValidator("0" + dateNonDelimiter.substring(2, 3), MONTH_MAP.get(monthReturned),
							Integer.parseInt(yearReturned), NUM_CHECK);
				} else {
					dayReturned = dayValidator(dateNonDelimiter.substring(2, 4), MONTH_MAP.get(monthReturned),
							Integer.parseInt(yearReturned), NUM_CHECK);
				}
			} else if (inputDayIndex == '2' && inputYearIndex == '1' && inputYearFormat.length() == 4) {
				debugPrint("Checking DD at index 2");
				if (day_length == '1') {
					dayReturned = dayValidator("0" + dateNonDelimiter.substring(4, 5), MONTH_MAP.get(monthReturned),
							Integer.parseInt(yearReturned), NUM_CHECK);
				} else {
					dayReturned = dayValidator(dateNonDelimiter.substring(4, 6), MONTH_MAP.get(monthReturned),
							Integer.parseInt(yearReturned), NUM_CHECK);
				}
			}
			// First index is month
			else if (inputDayIndex == '2' && inputMonthIndex == '1' && inputMonthFormat.length() == 2) {
				debugPrint("Checking DD at index 2");
				if (day_length == '1') {
					dayReturned = dayValidator("0" + dateNonDelimiter.substring(2, 3), MONTH_MAP.get(monthReturned),
							Integer.parseInt(yearReturned), NUM_CHECK);
				} else {
					dayReturned = dayValidator(dateNonDelimiter.substring(2, 4), MONTH_MAP.get(monthReturned),
							Integer.parseInt(yearReturned), NUM_CHECK);
				}
			} else if (inputDayIndex == '2' && inputMonthIndex == '1' && inputMonthFormat.length() == 3) {
				debugPrint("Checking DD at index 2");
				if (day_length == '1') {
					dayReturned = dayValidator("0" + dateNonDelimiter.substring(3, 4), MONTH_MAP.get(monthReturned),
							Integer.parseInt(yearReturned), NUM_CHECK);
				} else {
					dayReturned = dayValidator(dateNonDelimiter.substring(3, 5), MONTH_MAP.get(monthReturned),
							Integer.parseInt(yearReturned), NUM_CHECK);
				}
			} else if (inputDayIndex == '2' && inputMonthIndex == '1' && inputMonthFormat.length() == 4) {
				debugPrint("Checking DD at index 2");
				if (day_length == '1') {
					dayReturned = dayValidator(
							"0" + dateNonDelimiter.substring(monthReturned.length(), monthReturned.length() + 1),
							MONTH_MAP.get(monthReturned), Integer.parseInt(yearReturned), NUM_CHECK);
				} else {
					dayReturned = dayValidator(
							dateNonDelimiter.substring(monthReturned.length(), monthReturned.length() + 2),
							MONTH_MAP.get(monthReturned), Integer.parseInt(yearReturned), NUM_CHECK);
				}
			}
			// Day is last index and year length = 2
			else if (inputDayIndex == '3' && inputYearFormat.length() == 2 && inputMonthFormat.length() == 2) {
				debugPrint("Checking DD at index 3");
				if (day_length == '1') {
					dayReturned = dayValidator("0" + dateNonDelimiter.substring(4, 5), MONTH_MAP.get(monthReturned),
							Integer.parseInt(yearReturned), NUM_CHECK);
				} else {
					dayReturned = dayValidator(dateNonDelimiter.substring(4, 6), MONTH_MAP.get(monthReturned),
							Integer.parseInt(yearReturned), NUM_CHECK);
				}
			} else if (inputDayIndex == '3' && inputYearFormat.length() == 2 && inputMonthFormat.length() == 3) {
				debugPrint("Checking DD at index 3");
				if (day_length == '1') {
					dayReturned = dayValidator("0" + dateNonDelimiter.substring(5, 6), MONTH_MAP.get(monthReturned),
							Integer.parseInt(yearReturned), NUM_CHECK);
				} else {
					dayReturned = dayValidator(dateNonDelimiter.substring(5, 7), MONTH_MAP.get(monthReturned),
							Integer.parseInt(yearReturned), NUM_CHECK);
				}
			} else if (inputDayIndex == '3' && inputYearFormat.length() == 2 && inputMonthFormat.length() == 4) {
				debugPrint("Checking DD at index 3");
				if (day_length == '1') {
					dayReturned = dayValidator(
							"0" + dateNonDelimiter.substring(monthReturned.length() + 2, monthReturned.length() + 3),
							MONTH_MAP.get(monthReturned), Integer.parseInt(yearReturned), NUM_CHECK);
				} else {
					dayReturned = dayValidator(
							dateNonDelimiter.substring(monthReturned.length() + 2, monthReturned.length() + 4),
							MONTH_MAP.get(monthReturned), Integer.parseInt(yearReturned), NUM_CHECK);
				}
			}
			// Day is last index and year length = 4
			else if (inputDayIndex == '3' && inputYearFormat.length() == 4 && inputMonthFormat.length() == 2) {
				debugPrint("Checking DD at index 3");
				if (day_length == '1') {
					dayReturned = dayValidator("0" + dateNonDelimiter.substring(6, 7), MONTH_MAP.get(monthReturned),
							Integer.parseInt(yearReturned), NUM_CHECK);
				} else {
					dayReturned = dayValidator(dateNonDelimiter.substring(6, 8), MONTH_MAP.get(monthReturned),
							Integer.parseInt(yearReturned), NUM_CHECK);
				}
			} else if (inputDayIndex == '3' && inputYearFormat.length() == 4 && inputMonthFormat.length() == 3) {
				debugPrint("Checking DD at index 3");
				if (day_length == '1') {
					dayReturned = dayValidator("0" + dateNonDelimiter.substring(7, 8), MONTH_MAP.get(monthReturned),
							Integer.parseInt(yearReturned), NUM_CHECK);
				} else {
					dayReturned = dayValidator(dateNonDelimiter.substring(7, 9), MONTH_MAP.get(monthReturned),
							Integer.parseInt(yearReturned), NUM_CHECK);
				}
			} else if (inputDayIndex == '3' && inputYearFormat.length() == 4 && inputMonthFormat.length() == 4) {
				debugPrint("Checking DD at index 3");
				if (day_length == '1') {
					dayReturned = dayValidator(
							"0" + dateNonDelimiter.substring(monthReturned.length() + 4, monthReturned.length() + 5),
							MONTH_MAP.get(monthReturned), Integer.parseInt(yearReturned), NUM_CHECK);
				} else {
					dayReturned = dayValidator(
							dateNonDelimiter.substring(monthReturned.length() + 4, monthReturned.length() + 6),
							MONTH_MAP.get(monthReturned), Integer.parseInt(yearReturned), NUM_CHECK);
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

	private String dayValidator(String day, int month, int year, Map<Character, Character> NUM_CHECK) {

		debugPrint("Day validator called.");
		// Check if day values are numeric
		for (int i = 0; i < day.length(); i++) {
			if (!NUM_CHECK.containsKey(day.charAt(i))) {
				debugPrint("Day validation failed.");
				return "false";
			}
		}

		int iDay = Integer.parseInt(day);

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
		return day;
	}

	private String monthValidator(String month, String dateFormatNonDelimiter) {

		debugPrint("String Month validator called.");
		if (dateFormatNonDelimiter.toUpperCase().contains("MMMM") && MONTH_MAP.containsKey(month.toUpperCase())) {
			debugPrint("Month validated successfully.");
			return month.toUpperCase();
		} else if (dateFormatNonDelimiter.toUpperCase().contains("MMM")
				&& !dateFormatNonDelimiter.toUpperCase().contains("MMMM")) {
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
			for (int i = 0; i < month.length(); i++) {
				if (!NUM_CHECK.containsKey(month.charAt(i))) {
					debugPrint("Month validation failed.");
					return "false";
				}
			}
			if (Integer.parseInt(month) < 13 && Integer.parseInt(month) > 0) {
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

	private String yearValidator(String year, Map<Character, Character> NUM_CHECK) {

		debugPrint("Year validator called.");

		// Check if year values are numeric
		for (int i = 0; i < year.length(); i++) {
			if (!NUM_CHECK.containsKey(year.charAt(i))) {
				debugPrint("Year validation failed.");
				return "false";
			}
		}
		if (Integer.parseInt(year) < 9999 && Integer.parseInt(year) > 1000) {
		} else if (year.length() == 2 && Integer.parseInt(year) < 50) {
			debugPrint("Appending 20 as prefix to the year.");
			year = "20" + year;
		} else if (year.length() == 2 && Integer.parseInt(year) > 50) {
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
			if (!NUM_CHECK.containsKey(dateNonDelimiter.charAt(s))) {
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
			if (sbFormatParts[0].toString().equals("DD")) {
				fd.setDayIndex('1');
				debugPrint("Day format: " + "DD " + " found at index: " + fd.getDayIndex());
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
			if (sbFormatParts[1].toString().equals("DD")) {
				fd.setDayIndex('2');
				debugPrint("Day format: " + "DD " + " found at index: " + fd.getDayIndex());
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
			if (sbFormatParts[2].toString().equals("DD")) {
				fd.setDayIndex('3');
				debugPrint("Day format: " + "DD " + " found at index: " + fd.getDayIndex());
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
}
