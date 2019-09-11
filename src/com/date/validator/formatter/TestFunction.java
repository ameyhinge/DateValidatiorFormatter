package com.date.validator.formatter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestFunction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter the date to be validated:");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			try {
				String input = br.readLine();
			} finally {
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
