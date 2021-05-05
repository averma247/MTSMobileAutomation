package com.mts.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mts.pages.CartPage;
import com.mts.pages.GeneralStoreInitialPage;
import com.mts.pages.ProductPage;
import com.mts.pages.WebViewPage;

public class CartPageTest extends BaseTest{
	GeneralStoreInitialPage gsip;
	ProductPage pp;
	CartPage cp;
	
	@BeforeClass(alwaysRun=true)
	public void beforeClassMethod(){
		System.out.println("Inside Before Class method of : "+ this.getClass().getSimpleName());
		gsip = new GeneralStoreInitialPage(getDriver());	
		pp= new ProductPage(getDriver());
		cp= new CartPage(getDriver());
	
	}
	
	
	@Test(enabled = true)
	public void verifyCartSum() throws InterruptedException{		
		gsip.addUserDetails("Ajay","Female", "Argentina");
		Assert.assertTrue(gsip.checkFormFilledSuccessfully());
		System.out.println("Form is filled successfully");
		//pp.checkProduct();		
		pp.addItemsToCart();
		pp.clickOnCart();
		Thread.sleep(4000);
		double sumofproduct=cp.getSumOfProduct();
		double totalofproduct= cp.getTotalPurchaseAmount();
		Assert.assertEquals(sumofproduct, totalofproduct);
		
	}
	
	
	@Test(groups={"smoke"})
	public void verifyTermsAndConditions() throws InterruptedException{		
		gsip.addUserDetails("Ajay","Female", "Argentina");
		Assert.assertTrue(gsip.checkFormFilledSuccessfully());
		System.out.println("Form is filled successfully");	
		pp.addItemsToCart();
		pp.clickOnCart();
		Thread.sleep(4000);
		double sumofproduct=cp.getSumOfProduct();
		double totalofproduct= cp.getTotalPurchaseAmount();
		Assert.assertEquals(sumofproduct, totalofproduct);
		cp.tapOnCheckBox();
		String alertTitle=cp.longPressOnTCandGetTitle();
		Assert.assertEquals(alertTitle, "Terms Of Conditions");
		cp.clickOnProceed();
		
		
	}
	

}
