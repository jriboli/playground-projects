package com.binaryNovice;

public class AccessModifierTest_Package {
	AccessModifierTest_Class testClass = new AccessModifierTest_Class();
	
	// ACCESSING FIELDS 
	int pub = testClass.publicInteger; 
	//int pri = testClass.privateInteger; // ERROR not visible 
	int pro = testClass.protectedInteger; 
	
	// ACCESSING METHODS
	int pubM = testClass.getPublicInteger();
	//int priM = testClass.getPrivateInteger(); // ERROR not visible
	int proM = testClass.getProtectedInteger();
}
