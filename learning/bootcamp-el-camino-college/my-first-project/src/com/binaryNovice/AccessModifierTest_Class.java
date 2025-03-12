package com.binaryNovice;

public class AccessModifierTest_Class {
	private int privateInteger = 99;
	public int publicInteger = 0;
	protected int protectedInteger = 45; 
	
	public int getPublicInteger() {
		return publicInteger;
	}
	
	private int getPrivateInteger() {
		return privateInteger;
	}
	
	protected int getProtectedInteger() {
		return protectedInteger;
	}
}