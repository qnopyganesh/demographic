package com.example.demo.print;

import java.util.Iterator;

public class convertNumberToWord {
	private static final String[] units = { "", " one", " two", " three", " four", " five", " six", " seven", " eight",
			" nine" };
	private static final String[] twoDigits = { " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen",
			" sixteen", " seventeen", " eighteen", " nineteen" };
	private static final String[] tenMultiples = { "", "", " twenty", " thirty", " forty", " fifty", " sixty",
			" seventy", " eighty", " ninety" };
	private static final String[] placeValues = { "", " thousand", " lakh", " crore", " arab", " kharab" };

	public static String firstLetterCap(String str) {
		String ret = "";
		String strL[] = str.split(" ");
		for (int i = 0; i < strL.length; i++) {
			if (strL[i].length() > 1)
				ret += strL[i].substring(0, 1).toUpperCase() + strL[i].substring(1).toLowerCase() + " ";
			else
				ret += strL[i] + " ";
		}
		ret = ret.trim();
		return ret;
	}

	public static String convertNumber(long number) {
		String word = "";
		int index = 0;
		boolean firstIteration = true;
		int divisor;
		do {
			divisor = firstIteration ? 1000 : 100;
			// take 3 or 2 digits based on iteration
			int num = (int) (number % divisor);
			if (num != 0) {
				String str = ConversionForUptoThreeDigits(num);
				word = str + placeValues[index] + word;
			}
			index++;
			// next batch of digits
			number = number / divisor;
			firstIteration = false;
		} while (number > 0);
		return firstLetterCap(word);
	}

	private static String ConversionForUptoThreeDigits(int number) {
		String word = "";
		int num = number % 100;
		if (num < 10) {
			word = word + units[num];
		} else if (num < 20) {
			word = word + twoDigits[num % 10];
		} else {
			word = tenMultiples[num / 10] + units[num % 10];
		}

		word = (number / 100 > 0) ? units[number / 100] + " hundred" + word : word;
		return word;
	}

	public static void main(String[] args) {
		System.out.println("1234123456789- " + convertNumber(1234123456789L));
		System.out.println("326776756- " + convertNumber(326776756));
		System.out.println("37565820- " + convertNumber(37565820));
		System.out.println("9341947- " + convertNumber(9341947));
		System.out.println("37000- " + convertNumber(37000));
		System.out.println("1387- " + convertNumber(1387));
		System.out.println("10- " + convertNumber(10));
		System.out.println("5- " + convertNumber(5));
	}

}
