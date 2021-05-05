package com.mts.tests;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseTest {

	private static AppiumDriver<?> driver;

	@BeforeSuite(alwaysRun=true)
	public void init(){
		System.out.println("Inside Before Suite method :" +this.getClass().getSimpleName());
		//launchOnAndroidChrome();
		//startServer();
		launchOnAndroidHybridApp();

	}
	
	@BeforeMethod(alwaysRun=true)
	public void beforeMethod(){
		System.out.println("Inside Before method: "+ this.getClass().getSimpleName());
		
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void afterMethod(){
		System.out.println("Inside After method: "+ this.getClass().getSimpleName());
		driver.resetApp();
		
	}
	@AfterSuite(alwaysRun=true)
	public void afterSuite(){
		System.out.println("Inside After suite method: "+ this.getClass().getSimpleName());
		driver.quit();
		//stopServer();
		
	}
	
	public void launchOnAndroidChrome(){

		try{

			DesiredCapabilities cap=new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel4XLAj");
			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");
			cap.setCapability(MobileCapabilityType.BROWSER_NAME, "CHROME");
			//cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.chrome");
			//cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.google.android.apps.chrome.Main");
			cap.setCapability("chromedriverExecutable",System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe");
			//cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.bigbasket.mobileapp");
			//cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.bigbasket.mobileapp.activity.account.uiv3.SignupActivity");	
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver=new AndroidDriver<AndroidElement>(url,cap);



		}catch (Exception e) {
			// TODO Auto-generated catch block			
			System.out.println("Cause is : "+ e.getCause());
			System.out.println("Message is : "+ e.getMessage());
			e.printStackTrace();
		}


	}/*-- END OF METHOD ---*/


	public void launchOnAndroidHybridApp(){

		try{

			File appDir= new File("src/test/resources/app/");
			File app = new File(appDir,"General-Store.apk");
			DesiredCapabilities cap=new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel4XLAj");
			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");
			cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver=new AndroidDriver<AndroidElement>(url,cap);
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);


		}catch (Exception e) {
			// TODO Auto-generated catch block			
			System.out.println("Cause is : "+ e.getCause());
			System.out.println("Message is : "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public AppiumDriver<?> getDriver() {
        return driver;
    }
	
	public void startServer() {
	    Runtime runtime = Runtime.getRuntime();
	    try {
	        runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\"");
	        Thread.sleep(10000);
	    } catch (IOException | InterruptedException e) {
	        e.printStackTrace();
	    }
	}

	public void stopServer() {
	    Runtime runtime = Runtime.getRuntime();
	    try {
	        runtime.exec("taskkill /F /IM node.exe");
	        runtime.exec("taskkill /F /IM cmd.exe");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	

}
