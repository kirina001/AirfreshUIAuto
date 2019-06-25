package com.testing.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.testing.auto.AutoLogger;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;

public class FirstDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//通过cmd启动appium的服务
		try {
			Runtime.getRuntime().exec("cmd /c start appium -a 127.0.0.1 -p 12345 --log F:\\appium.txt --local-timezone");
			Thread.sleep(10*1000);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		AndroidDriver driver=null;
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//必要参数
		capabilities.setCapability("deviceName", "127.0.0.1:7555");
		capabilities.setCapability("platformVersion", "6.0.1");
		capabilities.setCapability("appPackage", "com.tencent.mm");
		capabilities.setCapability("appActivity", ".ui.LauncherUI");
		capabilities.setCapability("platformName", "Android");
		//可选参数
		capabilities.setCapability("noSign", true);
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		
		//电脑连接了多个设备的时候，指定设备。
		capabilities.setCapability("udid", "127.0.0.1:7555");
		
		try {
			driver=new AndroidDriver(new URL("http://127.0.0.1:12345/wd/hub"), capabilities);
			AutoLogger.log.info("APP正在启动中……");
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
