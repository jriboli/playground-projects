package com.promineotech.junit;

import java.util.Random;

import com.promineotect.junit.models.Cat;
import com.promineotect.junit.models.Dog;

public class TestDemo {

	public int addPositive(int num1, int num2) {
		if(num1 < 0 || num2 < 0) {
			throw new IllegalArgumentException("Neither number can be negative"); 
		}
		return num1 + num2;
	}
	
	public Cat askForACat() {
		return null;
	}
	
	public Dog askForADog() {
		return new Dog("Rover");
	}
	
	public int randomNumberSquared() {
		int num = getRandomInt();
		return num * num;
	}
	
	int getRandomInt() {
		Random random = new Random();
		return random.nextInt(10) + 1;
	}
}
