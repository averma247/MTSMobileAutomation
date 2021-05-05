package com.mts.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductPage extends BasePage{

	public ProductPage(AppiumDriver<?> appiumDriver) {
		super(appiumDriver);
		// TODO Auto-generated constructor stub
	}

	//------------- Android elements ---------------//
	@AndroidFindBy(id = "productName")
	private AndroidElement productNameElement;

	@AndroidFindBy(id = "productAddCart")
	private AndroidElement productAddCart;

	@AndroidFindBy(id = "appbar_btn_cart")
	private AndroidElement appbar_btn_cart;

	@AndroidFindBy(id = "productName")
	private List<AndroidElement> elementsWithproductName; 

	@AndroidFindBy(id = "productAddCart")
	private List<AndroidElement> elementsWithproductAddCart; 


	private String idProductName="com.androidsample.generalstore:id/productName";
	private String xpathAddToCartElement="//*[@text='ADD TO CART']";


	public void scrollToProduct(String productName){

		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\""+productName+"\").instance(0))"));

	}

	public void scrollToProductAndAddToCart(String productName){

		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\""+productName+"\").instance(0))"));

		int count=    driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		for(int i=0;i<count;i++)
		{
			String text=driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			if(text.equalsIgnoreCase(productName))
			{
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
				break;
			}
		}

	}

	public void scrollToProductAndAddToCart1(String productName){

		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\""+productName+"\").instance(0))"));

		int count=  elementsWithproductName.size();
		for(int i=0;i<count;i++)
		{
			String text=elementsWithproductName.get(i).getText();
			if(text.equalsIgnoreCase(productName))
			{
				elementsWithproductAddCart.get(i).click();
				break;
			}
		}

	}

	public void clickOnCart(){

		appbar_btn_cart.click();

	}

	public String getProductCartItemName(){

		String productIteamText=getTextAt(By.id(idProductName));
		return productIteamText;
	}


	public void addItemsToCart(){
		int count = driver.findElements(By.xpath(xpathAddToCartElement)).size();
		for(int i=0;i<count;i++){
			driver.findElements(By.xpath(xpathAddToCartElement)).get(0).click();
		}
		
	}

}
