package com.mts.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mts.pages.CartPage;
import com.mts.pages.GeneralStoreInitialPage;
import com.mts.pages.ProductPage;
import com.mts.pages.WebViewPage;

public class WebViewSwitchingTest extends BaseTest {

	GeneralStoreInitialPage gsip;
	ProductPage pp;
	CartPage cp;
	WebViewPage wvp;

	@BeforeClass(alwaysRun=true)
	public void beforeClassMethod(){
		System.out.println("Inside Before Class method of : "+ this.getClass().getSimpleName());
		gsip = new GeneralStoreInitialPage(getDriver());	
		pp= new ProductPage(getDriver());
		cp= new CartPage(getDriver());
		wvp= new WebViewPage(getDriver());		
	}
	
	@Parameters({"name","gender","country"})
	@Test(groups={"sanity"},enabled=false)
	public void verifyContextSwitching(String name,String gender, String country) throws InterruptedException{		
		gsip.addUserDetails(name,gender,country);
		//Assert.assertTrue(gsip.checkFormFilledSuccessfully());
		//System.out.println("Form is filled successfully");	
		pp.addItemsToCart();
		pp.clickOnCart();
		cp.tapOnCheckBox();
		cp.clickOnProceed();		
		Thread.sleep(5000);
		System.out.println("Switching context view");
		wvp.switchContext();
		Thread.sleep(3000);


	}
	
	@Test(groups={"smoke"},timeOut=70000,enabled=true)
	public void averifyContextSwitchingBack() throws InterruptedException{		
		gsip.addUserDetails("Vijay","Male", "Australia");
		//Assert.assertTrue(gsip.checkFormFilledSuccessfully());
		//System.out.println("Form is filled successfully");	
		pp.addItemsToCart();
		pp.clickOnCart();
		cp.tapOnCheckBox();
		cp.clickOnProceed();		
		Thread.sleep(5000);
		System.out.println("Switching context view");
		wvp.switchContext();

	}
	
	@Test(dependsOnMethods="verifyContextSwitching",enabled=false)
	public void dependsverifyContextSwitchingBack() throws InterruptedException{		
		gsip.addUserDetails("Vijay","Male", "Australia");
		//Assert.assertTrue(gsip.checkFormFilledSuccessfully());
		//System.out.println("Form is filled successfully");	
		pp.addItemsToCart();
		pp.clickOnCart();
		cp.tapOnCheckBox();
		cp.clickOnProceed();		
		Thread.sleep(5000);
		System.out.println("Switching context view");
		wvp.switchContext();

	}


}
