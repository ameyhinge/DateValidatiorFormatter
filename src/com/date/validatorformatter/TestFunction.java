package com.date.validatorformatter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestFunction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			try {
				System.out.println("Enter 1 for validating");
				System.out.println("Enter 2 for formatting");

				String choice = br.readLine();
				// Only validation performed
				if (choice.equals("1")) {
					System.out.println("Enter input date format:");
					String inputDateFormat = br.readLine();

					System.out.println("Enter the date to be validated:");
					String inputDate = br.readLine();

					ValidatorResult vr = new ValidatorResult();
					ValidateFormat vf = new ValidatorFormatter();
					vr = vf.validateDate(inputDateFormat, inputDate);

					System.out.println("Input day :" + vr.getInputDay());
					System.out.println("Input month :" + vr.getInputMonth());
					System.out.println("Input year: " + vr.getInputYear());
					System.out.println("Is date valid: " + vr.getIsValidDate());
					System.out.println("Is format valid: " + vr.getIsValidFormat());
					System.out.println("Message: " + vr.getMessage());
				}
				// Validation and formatting performed
				else if (choice.equals("2")) {
					System.out.println("Enter input date format:");
					String inputDateFormat = br.readLine();

					System.out.println("Enter the date to be validated:");
					String inputDate = br.readLine();

					System.out.println("Enter output date format:");
					String outputDateFormat = br.readLine();

					ValidateFormat vf = new ValidatorFormatter();
					vf.formatDate(inputDateFormat, inputDate, outputDateFormat);
				} else if (choice.equals("3")) {
					System.out.println("Enter input date format:");
					String inputDateFormat = br.readLine();

					System.out.println("Enter the date to be validated:");
					String inputDate = br.readLine();

					System.out.println("Enter quantity:");
					String quantity = br.readLine();

					System.out.println("Enter unit:");
					String unit = br.readLine();

					ValidateFormat vf = new ValidatorFormatter();
					vf.incrementDate(inputDateFormat, inputDate, quantity, unit);
				} else {
					System.out.println("Invalid choice");
				}

			} finally {
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
