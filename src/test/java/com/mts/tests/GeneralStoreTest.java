package com.mts.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mts.pages.GeneralStoreInitialPage;

public class GeneralStoreTest extends BaseTest {	

	GeneralStoreInitialPage gsip;
	
	@BeforeClass(alwaysRun=true)
	public void beforeClassMethod(){
		System.out.println("Inside Before Class method of : "+ this.getClass().getSimpleName());
		gsip = new GeneralStoreInitialPage(getDriver());
	}



	@Test(groups={"smoke"},enabled = false)
	public void verifyFormFilUp() throws InterruptedException{		
		gsip.addUserDetails("Ajay","Female", "Bhutan");
		Assert.assertTrue(gsip.checkFormFilledSuccessfully());
		System.out.println("Form is filled successfully");


	}

	@Test(enabled = true,dataProvider="getData")
	public void sampleTest1(String name,String gender, String country){		
		gsip.addUserDetails(name,gender, country);

	}

	@Test(enabled = false)
	public void verifyIncompleteFormFillup(){
		gsip.selectGender("Female");
		gsip.scrollToCountryName("Argentina");
		gsip.clickOnLetShopButton();
		String toastMessageText=gsip.getToastMessageText();
		//System.out.println("toastMessageText: "+toastMessageText);
		Assert.assertEquals("Please enter your name", toastMessageText);		

	}
	
	@DataProvider
	public Object[][] getData(){
		Object[][]data= new Object[2][3];
		data[0][0]="Ajay";
		data[0][1]="Male";
		data[0][2]="Australia";
		
		data[1][0]="Anu";
		data[1][1]="Female";
		data[1][2]="Algeria";
		return data;
		
	}
}
