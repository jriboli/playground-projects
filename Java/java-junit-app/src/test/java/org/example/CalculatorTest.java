package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    // @BeforeEach @AfterEach annotations
    // Run before and after every test method in the class
    @BeforeEach
    public void before(){
        System.out.println("Before");
    }

    // @BeforeAll @AfterAll
    // Executed before/after all @Test, @RepeatedTest, @ParameterizedTest, and @TestFactory

    // @Test
    // @DisplayName
    @Test
    @DisplayName("Add two numbers")
    void add() {
        assertEquals(4, Calculator.add(2, 2));
    }

    @Test
    @DisplayName("Multiply two numbers")
    void multiply() {
        assertAll(() -> assertEquals(4, Calculator.multiply(2,2)),
                () -> assertEquals(-4, Calculator.multiply(2,-2)),
                () -> assertEquals(4, Calculator.multiply(-2,-2)),
                () -> assertEquals(0, Calculator.multiply(1,0)));
    }

    // @ParameterizedTest
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "0, 1,  1",
            "1, 2,  3",
            "49,50, 100",
            "1, 100,101"
    })
    void addParameterized(int first, int second, int expectedResult){
        assertEquals(expectedResult, Calculator.add(first, second),
                () -> first + " + " + second + " should equal " + expectedResult);
    }

    // @RepeatedTest

    // @TestFactory

    // @TestTemplate

    // @TestClassOrder

    // @TestMethodOrder

    // @TestIstance

    // @Tag

    // @Disabled

    // @Timeout

    // @ExtendWith

    // @RegisterExtension

    // @TempDir
}