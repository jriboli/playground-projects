package com.promineotech.tdd;

public class TddDemo {

	public String leftTrim(String valueToTest) {
		int pos = 0;
		
		for(; pos < valueToTest.length(); pos++) {
			if(valueToTest.charAt(pos) != '0') {
				break;
			}
		}
		
		String result = valueToTest.substring(pos);
		return result.isEmpty() ? "0" : result;
	}

}
