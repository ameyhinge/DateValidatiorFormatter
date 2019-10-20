package com.date.validatorformatter;

public class ValidatorResult {
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
