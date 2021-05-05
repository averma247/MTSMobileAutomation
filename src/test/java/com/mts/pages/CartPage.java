package com.mts.pages;

import java.util.List;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CartPage extends BasePage{

	public CartPage(AppiumDriver<?> appiumDriver) {
		super(appiumDriver);
		// TODO Auto-generated constructor stub
	}

	final private String idProductName="com.androidsample.generalstore:id/productName";
	final private String checkBoxClassName="android.widget.CheckBox";
	final private String xpathTermsAndConditionLink="//*[@text='Please read our terms of conditions']";

	@AndroidFindAll(value = { @AndroidBy(id = "productPrice")})
	private List<AndroidElement> elementsWithProductPrice; 
	
	@AndroidFindBy(id = "totalAmountLbl")
	private AndroidElement totalAmountLbl;
	
	@AndroidFindBy(className = "android.widget.CheckBox")
	private AndroidElement checkbox;
	
	@AndroidFindBy(id = "android:id/button1")
	private AndroidElement tcpopclosebutton;
	
	@AndroidFindBy(id = "btnProceed")
	private AndroidElement btnProceed;
	
	@AndroidFindBy(id = "alertTitle")
	private AndroidElement alertTitle;
	
	
	public String getProductCartItemName(){

		String productIteamText=getTextAt(By.id(idProductName));
		return productIteamText;
	}



	public static double getAmount(String value){
		value= value.substring(1);
		double amount2value=Double.parseDouble(value);
		return amount2value;
	}
	
	
	
	public double getSumOfProduct(){
		
		int count=elementsWithProductPrice.size();
		double sum=0;
		for(int i=0;i<count;i++){
		String amount1= elementsWithProductPrice.get(i).getText();
		double amount=getAmount(amount1);
		sum=sum+amount;//280.97+116.97
		}
		System.out.println(sum+" sum of products");
		return sum;

	}
	
	public double getTotalPurchaseAmount(){
		String total=totalAmountLbl.getText();
		total= total.substring(1);
		double totalValue=Double.parseDouble(total);
		System.out.println(totalValue+" Total value of products");
		return totalValue;
	}
	
	public void tapOnCheckBox(){
		tapOnElement(By.className(checkBoxClassName));
	}
	
	public String longPressOnTCandGetTitle(){
			longPressOnElement(By.xpath(xpathTermsAndConditionLink));
			String title = getTCAlertTitle();
			tcpopclosebutton.click();
			return title;
	}
	
	public void clickOnProceed(){
		btnProceed.click();
	}
	
	public String getTCAlertTitle(){
		String title=alertTitle.getAttribute("name");
		return title;
	}
	
}
