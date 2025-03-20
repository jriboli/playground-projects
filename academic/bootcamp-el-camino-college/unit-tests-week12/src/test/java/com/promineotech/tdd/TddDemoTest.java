package com.promineotech.tdd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Objects;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TddDemoTest {
	
	private TddDemo tddDemo;
	
	

	@BeforeEach
	void setUp() throws Exception {
		tddDemo = new TddDemo();
	}

	@ParameterizedTest
	@MethodSource("com.promineotech.tdd.TddDemoTest#argumentsForZeroTrim")
	void assertThatLeftTrimRemovesZerosOnTheLeft(String stringToTest, String expected, Class<? extends Exception> exceptionClass) {
		// Given : a numeric string to test
		//String given = "05000";
		//String expected = "5000";
		
		if(Objects.isNull(exceptionClass)) {
			// When : the method to remove zeros is called
			String actual = tddDemo.leftTrim(stringToTest);
			
			// Then : the zeros are removed properly
			assertThat(actual).isEqualTo(expected);
		} else {
			assertThatThrownBy(() -> tddDemo.leftTrim(stringToTest)).isInstanceOf(exceptionClass);
		}
		
		
	}
	
	static Stream<Arguments> argumentsForZeroTrim(){
		// @formatter:off
		return Stream.of(
				arguments("05000", "5000", null),
				arguments(null, "", NullPointerException.class)
		);
		// @formatter:on
	}
	
	@Test
	void assertWithStringAllZeros() {
		// Given : a numeric string to test
		String given = "00000";
		String expected = "0";
		
		// When : the method to remove zeros is called
		String actual = tddDemo.leftTrim(given);
		
		// Then : the zeros are removed properly
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	void assertThatLeftTrimRemovesZerosOnTheLeftGivenNoZeros() {
		// Given : a numeric string to test
		String given = "89456";
		String expected = "89456";
		
		// When : the method to remove zeros is called
		String actual = tddDemo.leftTrim(given);
		
		// Then : the zeros are removed properly
		assertThat(actual).isEqualTo(expected);
	}

}
