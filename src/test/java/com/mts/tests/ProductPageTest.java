package com.mts.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mts.pages.CartPage;
import com.mts.pages.GeneralStoreInitialPage;
import com.mts.pages.ProductPage;
import com.mts.utils.BaseTest;

public class ProductPageTest extends BaseTest {
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


	@Test(enabled = true,groups={"smoke"})
	public void verifyProductPageAddToCart() throws InterruptedException{		
		gsip.addUserDetails("Ajay","Female", "Argentina");
		Assert.assertTrue(gsip.checkFormFilledSuccessfully());
		System.out.println("Form is filled successfully");
		//pp.checkProduct();		
		pp.scrollToProductAndAddToCart1("Air Jordan 1 Mid SE");
		pp.clickOnCart();
		String addedProductName=cp.getProductCartItemName();
		Assert.assertEquals("Air Jordan 1 Mid SE", addedProductName);//check for item added in the cart.
	}
	
	@Test(enabled = false)
	public void verifyProductPage() throws InterruptedException{		
		gsip.addUserDetails("Ajay","Female", "Argentina");
		Assert.assertTrue(gsip.checkFormFilledSuccessfully());
		System.out.println("Form is filled successfully");
		//pp.checkProduct();		
		pp.scrollToProductAndAddToCart1("Air Jordan 1 Mid SE");
		pp.clickOnCart();
	}

}
