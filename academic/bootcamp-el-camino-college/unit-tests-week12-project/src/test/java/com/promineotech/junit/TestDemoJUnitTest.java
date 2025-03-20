package com.promineotech.junit;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.promineotect.junit.models.Dog;

public class TestDemoJUnitTest {

	TestDemo testDemo;
	
	@BeforeEach
	public void setUp() throws Exception {
		testDemo = new TestDemo();
	}

	@Test
	public void addPositiveHappyPath() {
		int result = testDemo.addPositive(1, 1);
		assertSame(2, result);
	}
	
	// The example given by the project description
	@ParameterizedTest
	@MethodSource("com.promineotech.junit.TestDemoJUnitTest#argumentsForAddPositive")
	public void addPositiveParameterized(int num1, int num2, int expected) {
		assertThat(testDemo.addPositive(num1, num2)).isEqualTo(expected);
	}
	
	// An assert method I found will looking up info about Junit Asserts
	@ParameterizedTest
	@MethodSource("com.promineotech.junit.TestDemoJUnitTest#argumentsForAddPositive")
	public void addPositiveParameterizedV2(int num1, int num2, int expected) {
		int result = testDemo.addPositive(num1, num2);
		assertEquals(expected, result, "Failure - expected and actual do not match");
	}
	
	// How come assertEquals() and assertSame() dont work the same way or return the same results
	// assertSame() verifies that two variables do refer to the same object
	@ParameterizedTest
	@MethodSource("com.promineotech.junit.TestDemoJUnitTest#argumentsForAddPositive")
	public void addPositiveParameterizedV3(int num1, int num2, int expected) {
		int result = testDemo.addPositive(num1, num2);
		assertSame(expected, result);
	}
	
	static Stream<Arguments> argumentsForAddPositive(){
		return Stream.of(
				arguments(1,1,2),
				arguments(2,2,4),
				arguments(99,99,198)
		);
	}
	
	// The way the demo videos showed
	@Test
	public void addPositiveNegativeNumbers() {
		assertThatThrownBy(() -> testDemo.addPositive(1, -1)).isInstanceOf(IllegalArgumentException.class);
	}
	
	// The way to use Junit - saw a stackoverflow, that using Junit over org.AssertJ is better
	@Test
	public void addPositiveNegativeNumbersV2() {
		assertThrows(IllegalArgumentException.class, () -> {
			testDemo.addPositive(-1, 1);
		});
	}
	
	@Test
	public void askForACat() {
		assertNull(testDemo.askForACat(), () -> "The cat should be null");
	}
	
	@Test
	public void askForADog() {
		Dog dog = testDemo.askForADog();
		assertNotNull(dog, () -> "The dog should NOT be null");
		assertThat(dog.getName()).isEqualTo("Rover");
	}
	
	// Using Mockito
	@Test
	public void assertThanNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		
		int fiveSquared = mockDemo.randomNumberSquared();
		assertThat(fiveSquared).isEqualTo(25);
	}
	
	@Test
	public void askForADog_UseMockito_WeWantToFail() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(new Dog("Max")).when(mockDemo).askForADog();
		Dog dog = mockDemo.askForADog();
		assertNotNull(dog, () -> "The dog should NOT be null");
		assertThat(dog.getName()).isEqualTo("Rover");
	}

}
