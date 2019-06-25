package com.testing.Test;

import com.testing.appkw.KeywordOfApp;

public class JobTest {

	public static void main(String[] args) {
		
		KeywordOfApp job=new KeywordOfApp();
		job.StartAppium("4723", "15000");
		job.runBrowser("6.0.1", "127.0.0.1:7555", "http://127.0.0.1:4723/wd/hub", "10000");
		job.visitH5("https://m.51job.com/?partner=51job");
		job.printContexts();
		job.switchContext("NATIVE_APP");//使用原生app方式进行定位
		job.wait("2000");
		//appium元素定位方式
//		job.clickByid("com.android.browser:id/share_button");
		job.click("//android.webkit.WebView[@content-desc=\"前程无忧官网_招聘求职_找工作_上51job人才网\"]/android.view.View[2]");
		
//		job.inputByid("请输入关键字", "测试111");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		job.switchContext("NATIVE_APP");
//		job.input("//android.webkit.WebView[@content-desc=\"前程无忧官网_招聘求职_找工作_上51job人才网\"]/android.view.View[3]/android.widget.EditText", "软件测试");
//		job.click("//span[@id=\"tosearch\"]");
//		job.appiumSwipe("300", "300", "300", "600");
//		job.click("//div[@class='items']/a[1]");
//		job.click("//div[@class='apply bb a3']/span[3]");
//		job.driver.quit();
//		job.killAppium();
	}

}
