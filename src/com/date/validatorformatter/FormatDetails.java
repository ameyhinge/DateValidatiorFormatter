package com.date.validatorformatter;

public class FormatDetails {
	private String dayFormat;
	private String monthFormat;
	private String yearFormat;
	private char dayIndex;
	private char monthIndex;
	private char yearIndex;
	private boolean formatValid;
	private String message;
	private String format;
	private String formatDelimiter;

	public String getDayFormat() {
		return dayFormat;
	}

	public void setDayFormat(String dayFormat) {
		this.dayFormat = dayFormat;
	}

	public String getMonthFormat() {
		return monthFormat;
	}

	public void setMonthFormat(String monthFormat) {
		this.monthFormat = monthFormat;
	}

	public String getYearFormat() {
		return yearFormat;
	}

	public void setYearFormat(String yearFormat) {
		this.yearFormat = yearFormat;
	}

	public char getDayIndex() {
		return dayIndex;
	}

	public void setDayIndex(char dayIndex) {
		this.dayIndex = dayIndex;
	}

	public char getMonthIndex() {
		return monthIndex;
	}

	public void setMonthIndex(char monthIndex) {
		this.monthIndex = monthIndex;
	}

	public char getYearIndex() {
		return yearIndex;
	}

	public void setYearIndex(char yearIndex) {
		this.yearIndex = yearIndex;
	}

	public boolean getFormatValid() {
		return formatValid;
	}

	public void setFormatValid(boolean formatValid) {
		this.formatValid = formatValid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getFormatDelimiter() {
		return formatDelimiter;
	}

	public void setFormatDelimiter(String formatDelimiter) {
		this.formatDelimiter = formatDelimiter;
	}
}
