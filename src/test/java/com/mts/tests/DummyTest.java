package com.mts.tests;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class DummyTest {

	
	@Test
	public void dummyTest1(){
		System.out.println("dummyTest1");
	}
	
	@Test(groups={"smoke"})
	public void dummyTest2(){
		System.out.println("dummyTest2");
		
	}
}
