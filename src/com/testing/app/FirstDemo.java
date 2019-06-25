package com.testing.app;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.testing.auto.AutoLogger;

import io.appium.java_client.android.AndroidDriver;

public class FirstDemo {

	public static void main(String[] args) {
		

		AndroidDriver driver = null;
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("deviceName", "127.0.0.1:7555");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "6.0.1");
		capabilities.setCapability("appPackage", "com.tencent.mobileqqi");
		capabilities.setCapability("appActivity", "com.tencent.mobileqq.activity.SplashActivity");

		// 可选参数
		capabilities.setCapability("noRest", true);
//		capabilities.setCapability("noSign", true);
//		capabilities.setCapability("unicodeKeyboard", "True");
//		capabilities.setCapability("resetKeyboard", "True");

		// 电脑连接多个设备时指定udid
		capabilities.setCapability("udid", "127.0.0.1:75555");
		
		try {
			driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
			Thread.sleep(5000);
			System.out.println("APP");
			AutoLogger.log.info("启动App");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
