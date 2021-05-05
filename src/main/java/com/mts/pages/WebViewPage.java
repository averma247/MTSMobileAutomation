package com.mts.pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import  io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;

public class WebViewPage extends BasePage{

	public WebViewPage(AppiumDriver<?> appiumDriver) {
		super(appiumDriver);
		// TODO Auto-generated constructor stub
	}
	
	
	public void switchContext(){
		Set<String> contexts=driver.getContextHandles();
		for(String contextName:contexts){
			
			System.out.println(contextName);
		}
		driver.context("WEBVIEW_com.androidsample.generalstore");
		driver.findElement(By.name("q")).sendKeys("coronavirus");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		((PressesKey) driver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		driver.context("NATIVE_APP");
		
		}

}
