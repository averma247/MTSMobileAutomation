package com.mts.pages;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class GeneralStoreInitialPage extends BasePage{

	public GeneralStoreInitialPage(AppiumDriver<?> appiumDriver) {
		super(appiumDriver);
	}

	@AndroidFindBy(id = "nameField")
	private AndroidElement nameField;

	@AndroidFindBy(id = "btnLetsShop")
	private AndroidElement btnLetsShop;

	@AndroidFindBy(id = "spinnerCountry")
	private AndroidElement spinnerCountry;

	/**
	 * XPath strings.
	 * */
	final private String genderXpath="//*[@text='%s']";
	final private String productPageTitleXpath="//*[@text='Products']";
	final private String toastMessageXpath="//android.widget.Toast[1]";


	public void addUserDetails(String name,String gender, String countryName){


		nameField.sendKeys(name);
		selectGender(gender);
		scrollToCountryName(countryName);
		clickOnLetShopButton();

	}


	public void selectGender(String gender){
		String genderXpath_ = String.format(genderXpath, gender);
		driver.findElement(By.xpath(genderXpath_)).click();
	}

	public void scrollToCountryName(String countryName){
		spinnerCountry.click();
		//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");

		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + countryName + "\").instance(0))"));  
		driver.findElement(By.xpath("//*[@text='"+countryName+"']")).click();
		//clickOnLetShopButton();	


	}

	public boolean checkFormFilledSuccessfully() throws InterruptedException{		

		return (isElementPresent(By.xpath(productPageTitleXpath)));

	}

	public void clickOnLetShopButton(){
		btnLetsShop.click();
	}

	public String getToastMessageText(){
		String toastMessageText= driver.findElement(By.xpath(toastMessageXpath)).getAttribute("name");
		System.out.println("Toast Message Text: "+ toastMessageText);
		return toastMessageText;

	}

}
