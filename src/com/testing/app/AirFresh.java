package com.testing.app;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testing.auto.AutoLogger;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AirFresh {

	public static void main(String[] args) {

		// 通过cmd启动appium
		try {
			String cmd = "cmd /c start appium -a 127.0.0.1 -p 12345 --log F:\\appium.txt --local-timezone";
			Runtime.getRuntime().exec(cmd);
			Thread.sleep(5000);
			AutoLogger.log.info("appium服务已启动");
		} catch (Exception e1) {

			e1.printStackTrace();
		}

		AndroidDriver driver = null;
		DesiredCapabilities capabilities = new DesiredCapabilities();
//
		capabilities.setCapability("deviceName", "127.0.0.1:7555");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "6.0.1");
		capabilities.setCapability("appPackage", "com.FreshAir");
		capabilities.setCapability("appActivity", ".activity.WelcomeActivity");

//		// 可选参数
//		capabilities.setCapability("noRest", true);
////	capabilities.setCapability("noSign", true);
////	capabilities.setCapability("unicodeKeyboard", "True");
////	capabilities.setCapability("resetKeyboard", "True");
//
//		// 电脑连接多个设备时指定udid
////		capabilities.setCapability("udid", "127.0.0.1:75555");
//
		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:12345/wd/hub"), capabilities);
			
			Thread.sleep(10*1000);
			AutoLogger.log.info("启动App");
		} catch (Exception e) {

			e.printStackTrace();
		}
		MobileElement el1 = (MobileElement) driver.findElementById("com.android.packageinstaller:id/permission_allow_button");
		el1.click();
		MobileElement el2 = (MobileElement) driver.findElementById("com.FreshAir:id/login_by_pwd_code");
		el2.click();
		MobileElement el3 = (MobileElement) driver.findElementById("com.FreshAir:id/login_by_code_phone");
		el3.clear();
		el3.sendKeys("15316039123");
		MobileElement el4 = (MobileElement) driver.findElementById("com.FreshAir:id/login_by_code_number");
		el4.clear();
		el4.sendKeys("8888");
		MobileElement el5 = (MobileElement) driver.findElementById("com.FreshAir:id/login_by_code_login");
		el5.click();

		MobileElement el6 = (MobileElement) driver
				.findElementById("com.android.packageinstaller:id/permission_allow_button");
		// 显式等待
		WebDriverWait explicitwait = new WebDriverWait(driver, 10);
		el6.click();
		AutoLogger.log.info("执行登录完毕");

		// 退出操作
		MobileElement el7 = (MobileElement) driver.findElementById("com.FreshAir:id/main_menu");
		el7.click();
		MobileElement el8 = (MobileElement) driver.findElementById("com.FreshAir:id/menu_left_user_icon");
		el8.click();
//		WebDriverWait explicitwait1 = new WebDriverWait(driver, 10);

		MobileElement el9 = (MobileElement) driver.findElementById("com.FreshAir:id/fm_phone_quite");
		el9.click();

		MobileElement el10 = (MobileElement) driver.findElementById("android:id/button1");
		el10.click();
		AutoLogger.log.info("执行退出完毕");

	}

}
