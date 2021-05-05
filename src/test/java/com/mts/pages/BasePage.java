package com.mts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

public class BasePage {

	protected AppiumDriver<?> driver;
	WebDriverWait explicitwait;
	
	public BasePage(AppiumDriver<?> appiumDriver){
		this.driver=appiumDriver;
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
		
	}


	private void waitFor(ExpectedCondition<?> expectedCondition, Integer timeout){
		timeout=timeout!=null?timeout:5;
		explicitwait= new WebDriverWait(driver, timeout);
		explicitwait.until(expectedCondition);
	}

	public boolean isElementPresent(By locator) throws InterruptedException{
		try {

			driver.findElement(locator).isDisplayed();
			return true;
		}
		catch(NoSuchElementException e){
			return false;
		}
	}
	
	public String getTextAt(By locator){
		String getTextValue=null;
		waitFor(ExpectedConditions.visibilityOfElementLocated(locator),15);
		getTextValue= driver.findElement(locator).getText();
		return getTextValue;
	}
	
	
	public void tapOnElement(By locator){
		WebElement checkbox=driver.findElement(locator);
		TouchAction t=new TouchAction(driver);
		t.tap(tapOptions().withElement(element(checkbox))).perform();
	}
	
	public void longPressOnElement(By locator){
		WebElement tc=driver.findElement(locator);
		TouchAction t=new TouchAction(driver);
		t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();
	}

}
